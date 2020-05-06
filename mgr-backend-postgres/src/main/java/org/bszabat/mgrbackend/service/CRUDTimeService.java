package org.bszabat.mgrbackend.service;

import org.bszabat.mgrbackend.exception.BadRequest;
import org.bszabat.mgrbackend.exception.CannotFetchData;
import org.bszabat.mgrbackend.exception.ResourceNotFound;
import org.bszabat.mgrbackend.helpers.Utils;
import org.bszabat.mgrbackend.model.Photo;
import org.bszabat.mgrbackend.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class CRUDTimeService {

    private final static String PHOTOS_RANGE = "5000,10000,40000,120000";
    private RestTemplate restTemplate = new RestTemplate();
    private PhotoRepository photoRepository;
    private Utils utils;

    private Photo photoReq = new Photo(1L, 1, "title", "url", "thumbnailUrl");

    @Autowired
    public CRUDTimeService(PhotoRepository photoRepository, Utils utils) {
        this.photoRepository = photoRepository;
        this.utils = utils;
    }

    public String readMultipleTime(String method, int quantity, long id) {
        if (method.equals("single")) {
            return utils.measureTime(quantity + "/" + id, this::readMultiple);
        }
        else if (method.equals("all") && PHOTOS_RANGE.contains(String.valueOf(quantity))) {
            return utils.measureTime(quantity, this::readMultipleAll);
        }
        throw new BadRequest("Method has to be single/all");
    }

    private String readMultipleAll(int quantity) {
        photoRepository.findAll();
        return "Read all successful";
    }

    private String readMultiple(String params) {
        int quantity = Integer.parseInt(params.split("/")[0]);
        long id = Long.parseLong(params.split("/")[1]);
        for (int i=0; i<quantity; i++) {
            photoRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFound("Not found photo with id: " + id));
        }

        return "Read all successful, quantity: " + quantity;
    }

    public String createMultiple(String url, int quantity, String method) {
        url = url + quantity;
        if (!PHOTOS_RANGE.contains(String.valueOf(quantity)) || !"single,all".contains(method)) {
            throw new BadRequest("Parameter quantity should be one of {" + PHOTOS_RANGE + "} and/or serializeTo one of {string/object}");
        }

        ResponseEntity<Photo[]> response = restTemplate.getForEntity(url, Photo[].class);
        if (response.getStatusCode().value() != 200) {
            throw new CannotFetchData("Cannot fetch data from " + url);
        }

        if (method.equals("single")) {
            return utils.measureTime(response.getBody(), this::createMultiple);
        }
        else {
            return utils.measureTime(response.getBody(), this::createMultipleAll);
        }
    }

    private String createMultipleAll(Photo[] photos) {
        photoRepository.saveAll(Arrays.asList(photos));
        return "Successful saved all objects";
    }

    private String createMultiple(Photo[] photos) {

        for (Photo photo : photos) {
            photoRepository.save(photo);
        }
        return "Successful saved all objects";
    }

    public String updateMultipleTime(int quantity, long id) {
        return utils.measureTime(quantity + "/" + id, this::updateMultiple);
    }

    private String updateMultiple(String params) {
        int quantity = Integer.parseInt(params.split("/")[0]);
        long id = Long.parseLong(params.split("/")[1]);
        for (long i=id; i<quantity+1; i++) {
            photoRepository.findById(id)
                    .map(photo -> {
                        photo.setAlbumId(photoReq.getAlbumId());
                        photo.setTitle(photoReq.getTitle());
                        photo.setUrl(photoReq.getUrl());
                        photo.setThumbnailUrl(photoReq.getThumbnailUrl());
                        return photoRepository.save(photo);
                    }).orElseThrow(() -> new ResourceNotFound("Not found photo with id: " + id));
        }
        return "Successful updated all objects, quantity: " + quantity;
    }

    public String deleteMultipleTime(int quantity, long id) {
        return utils.measureTime(quantity + "/" + id, this::deleteMultiple);
    }

    private String deleteMultiple(String params) {
        int quantity = Integer.parseInt(params.split("/")[0]);
        long id = Long.parseLong(params.split("/")[1]);
        for (long i=id; i<quantity+1; i++) {
            photoRepository.findById(id)
                    .map(photo -> {
                        photoRepository.delete(photo);
                        return ResponseEntity.ok().build();
                    }).orElseThrow(() -> new ResourceNotFound("Not found photo with id: " + id));
        }
        return "Successful deleted all objects, quantity: " + quantity;
    }
}
