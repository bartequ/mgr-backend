package org.bszabat.mgrbackend.controller;

import org.bszabat.mgrbackend.model.Photo;
import org.bszabat.mgrbackend.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//TODO add better responses codes
@RestController
@RequestMapping("/api/postgres")
public class PhotoController {

    private PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/photos")
    public List<Photo> getAllPhotos() {
        return photoService.getAllPhotos();
    }

    @GetMapping("/photos/{id}")
    public Photo getPhoto(@PathVariable Long id) {
        return photoService.getPhoto(id);
    }

    @PostMapping("/photos")
    public Photo createPhoto(@Valid @RequestBody Photo photo) {
        return photoService.createPhoto(photo);
    }

    @PutMapping("/photos/{id}")
    public Photo updatePhoto(@PathVariable Long id, @Valid @RequestBody Photo photo) {
        return photoService.updatePhoto(id, photo);
    }

    @DeleteMapping("/photos/{id}")
    public ResponseEntity<?> deletePhoto(@PathVariable Long id) {
        return photoService.deletePhoto(id);
    }
}
