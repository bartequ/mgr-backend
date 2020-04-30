package org.bszabat.mgrbackend.service;

import org.bszabat.mgrbackend.helpers.Utils;
import org.bszabat.mgrbackend.exception.BadRequest;
import org.bszabat.mgrbackend.exception.CannotFetchData;
import org.bszabat.mgrbackend.model.PhotoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ObjectSerializationService {

    private RestTemplate restTemplate = new RestTemplate();
    private Utils utils;

    @Autowired
    public ObjectSerializationService(Utils utils) {
        this.utils = utils;
    }

    public String fetchTestPhotosAndSerializeTime(String url, int quantity, String serializeTo) {
        url = url + quantity;
        if (!"5000,10000,40000,120000".contains(String.valueOf(quantity)) || !"string,object".contains(serializeTo)) {
            throw new BadRequest("Parameter quantity should be one of {5000, 10000, 40000, 120000} and/or serializeTo one of {string/object}");
        }
        else if (serializeTo.equals("string")) {
            return utils.measureTime(url, this::fetchTestPhotosToString);
        }

        return utils.measureTime(url, this::fetchTestPhotosToPhotoDto);
    }

    private PhotoDto[] fetchTestPhotosToPhotoDto(String url) {
        ResponseEntity<PhotoDto[]> response = restTemplate.getForEntity(url, PhotoDto[].class);
        if (response.getStatusCode().value() != 200) {
            throw new CannotFetchData("Cannot fetch data from " + url);
        }
        return response.getBody();
    }

    private String fetchTestPhotosToString(String url) {
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        if (response.getStatusCode().value() != 200) {
            throw new CannotFetchData("Cannot fetch data from " + url);
        }
        return response.getBody();
    }
}
