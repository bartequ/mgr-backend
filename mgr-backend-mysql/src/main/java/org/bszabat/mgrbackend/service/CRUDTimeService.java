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

    private Photo photoReqMock = new Photo(1, "title", "url", "thumbnailUrl");

    @Autowired
    public CRUDTimeService(PhotoRepository photoRepository, Utils utils) {
        this.photoRepository = photoRepository;
        this.utils = utils;
    }


    //TODO zabezpieczyc przed czytaniem wiecej obiektow niz jest w bazie?
    public String readMultipleTime(String method, int quantity) {
        if (method.equals("single")) {
            return utils.measureTime(quantity, this::readMultipleSingle);
        }
        else if (method.equals("all") && PHOTOS_RANGE.contains(String.valueOf(quantity))) {
            return utils.measureTime(quantity, this::readMultipleAll);
        }
        throw new BadRequest("Method has to be single/all");
    }

    private String readMultipleAll(int quantity) {
        photoRepository.findAll();
        return "Read all successful, quantity: " + quantity;
    }

    private String readMultipleSingle(int quantity) {
        long id = photoRepository.findMinId().get();
        for (int i=0; i<quantity; i++) {
            photoRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Not found photo with id: " + id));
        }

        return "Read all successful, quantity: " + quantity;
    }

    public String createMultipleTime(String url, int quantity, String method) {
        url = url + quantity;
        if (!PHOTOS_RANGE.contains(String.valueOf(quantity)) || !"single,all".contains(method)) {
            throw new BadRequest("Parameter quantity should be one of {" + PHOTOS_RANGE + "} and/or serializeTo one of {string/object}");
        }

        Photo[] photos = fetchAndPrepareData(url);
        if (method.equals("single")) {
            return utils.measureTime(photos, this::createMultipleSingle);
        }

        return utils.measureTime(photos, this::createMultipleAll);
    }

    private Photo[] fetchAndPrepareData(String url) {
        ResponseEntity<Photo[]> response = restTemplate.getForEntity(url, Photo[].class);
        if (response.getStatusCode().value() != 200) {
            throw new CannotFetchData("Cannot fetch data from " + url);
        }
        long id;
        //TODO Test na pustej tabeli
        try {
            id = photoRepository.findMaxId().get() + 1;
        } catch (Exception e) {
            id = 1L;
        }
        Photo[] photos = response.getBody();
        for (Photo photo: photos) {
            photo.setId(id);
            id++;
        }

        return photos;
    }

    private String createMultipleAll(Photo[] photos) {
        photoRepository.saveAll(Arrays.asList(photos));
        return "Successful saved all objects";
    }

    private String createMultipleSingle(Photo[] photos) {

        for (Photo photo : photos) {
            photoRepository.save(photo);
        }
        return "Successful saved all objects";
    }

    public String updateMultipleTime(int quantity) {
        return utils.measureTime(quantity, this::updateMultiple);
    }

    private String updateMultiple(int quantity) {
        long id = photoRepository.findMinId().get();
        for (long i=id; i<id+quantity; i++) {
            photoRepository.findById(i)
                    .map(photo -> {
                        photo.setAlbumId(photoReqMock.getAlbumId());
                        photo.setTitle(photoReqMock.getTitle());
                        photo.setUrl(photoReqMock.getUrl());
                        photo.setThumbnailUrl(photoReqMock.getThumbnailUrl());
                        return photoRepository.save(photo);
                    }).orElseThrow(() -> new ResourceNotFound("Not found photo with given id"));
        }
        return "Successful updated all objects, quantity: " + quantity;
    }

    public String deleteMultipleTime(int quantity) {
        return utils.measureTime(quantity, this::deleteMultiple);
    }

    private String deleteMultiple(int quantity) {
        long id = photoRepository.findMinId().get();
        for (long i=id; i<id+quantity; i++) {
            photoRepository.findById(i)
                    .map(photo -> {
                        photoRepository.delete(photo);
                        return ResponseEntity.ok().build();
                    }).orElseThrow(() -> new ResourceNotFound("Not found photo with given id"));
        }
        return "Successful deleted all objects, quantity: " + quantity;
    }
}
