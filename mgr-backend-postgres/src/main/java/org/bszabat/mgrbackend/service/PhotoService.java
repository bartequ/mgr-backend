package org.bszabat.mgrbackend.service;

import org.bszabat.mgrbackend.exception.ResourceNotFound;
import org.bszabat.mgrbackend.model.Photo;
import org.bszabat.mgrbackend.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {

    private PhotoRepository photoRepository;

    @Autowired
    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    public Photo getPhoto(Long id) {
        return photoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Not found photo with id:" + id));
    }

    public Photo createPhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    public Photo updatePhoto(Long id, Photo photoReq) {
        return photoRepository.findById(id)
                .map(photo -> {
                    photo.setAlbumId(photoReq.getAlbumId());
                    photo.setTitle(photoReq.getTitle());
                    photo.setUrl(photoReq.getUrl());
                    photo.setThumbnailUrl(photoReq.getThumbnailUrl());
                    return photoRepository.save(photo);
                }).orElseThrow(() -> new ResourceNotFound("Not found photo with id:" + id));
    }

    public ResponseEntity<?> deletePhoto(Long id) {
        return photoRepository.findById(id)
                .map(photo -> {
                    photoRepository.delete(photo);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFound("Not found photo with id:" + id));
    }
}
