package org.bszabat.mgrbackend.service;

import org.bszabat.mgrbackend.exception.BadRequest;
import org.bszabat.mgrbackend.exception.CannotFetchData;
import org.bszabat.mgrbackend.helpers.Utils;
import org.bszabat.mgrbackend.model.Photo;
import org.bszabat.mgrbackend.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CRUDTimeService {

    private RestTemplate restTemplate = new RestTemplate();
    private PhotoRepository photoRepository;
    private Utils utils;

    @Autowired
    public CRUDTimeService(PhotoRepository photoRepository, Utils utils) {
        this.photoRepository = photoRepository;
        this.utils = utils;
    }

    public String createMultipleByOneTime(String url, int quantity) {
        url = url + quantity;
        if (!"5000,10000,40000,120000".contains(String.valueOf(quantity))) {
            throw new BadRequest("Parameter quantity should be one of {5000, 10000, 40000, 120000} and/or serializeTo one of {string/object}");
        }

        ResponseEntity<Photo[]> response = restTemplate.getForEntity(url, Photo[].class);
        if (response.getStatusCode().value() != 200) {
            throw new CannotFetchData("Cannot fetch data from " + url);
        }
        return utils.measureTime(response.getBody(), this::createMultipleByOne);
    }

    private String createMultipleByOne(Photo[] photos) {

        for (Photo photo : photos) {
            photoRepository.save(photo);
        }
        return "Successful saved all objects";
    }
}
