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

@Service
public class CRUDTimeService {

    private RestTemplate restTemplate = new RestTemplate();
    private PhotoRepository photoRepository;
    private Utils utils;

    private Photo photoReq = new Photo(1L, 1, "title", "url", "thumbnailUrl");

    @Autowired
    public CRUDTimeService(PhotoRepository photoRepository, Utils utils) {
        this.photoRepository = photoRepository;
        this.utils = utils;
    }

    public String readMultipleTime(long quantity) {
//        LongStream.range(1, quantity).map(id -> {
//             photoRepository.findById(id)
//                    .orElseThrow(() -> new ResourceNotFound("Not found photo with id:" + id));
//        }).forEach();
        return utils.measureTime(quantity, this::readMultiple);
    }

    private String readMultiple(long quantity) {
        for (long i=1; i<quantity+1; i++) {
            photoRepository.findById(i)
                    .orElseThrow(() -> new ResourceNotFound("Not found photo with id: "));
        }
        return "Read all successful";
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

    public String updateMultipleTime(long quantity) {
        return utils.measureTime(quantity, this::updateMultiple);
    }

    private String updateMultiple(long quantity) {
        for (long i=1; i<quantity+1; i++) {
            photoRepository.findById(i)
                    .map(photo -> {
                        photo.setAlbumId(photoReq.getAlbumId());
                        photo.setTitle(photoReq.getTitle());
                        photo.setUrl(photoReq.getUrl());
                        photo.setThumbnailUrl(photoReq.getThumbnailUrl());
                        return photoRepository.save(photo);
                    }).orElseThrow(() -> new ResourceNotFound("Not found photo"));
        }
        return "Successful updated all objects";
    }

    public String deleteMultipleTime(long quantity) {
        return utils.measureTime(quantity, this::deleteMultiple);
    }

    private String deleteMultiple(long quantity) {
        for (long i=1; i<quantity+1; i++) {
            photoRepository.findById(i)
                    .map(photo -> {
                        photoRepository.delete(photo);
                        return ResponseEntity.ok().build();
                    }).orElseThrow(() -> new ResourceNotFound("Not found photo with id:"));
        }
        return "Successful deleted all objects";
    }
}
