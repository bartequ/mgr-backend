package org.bszabat.mgrbackend.service;

import org.bszabat.mgrbackend.model.Photo;
import org.bszabat.mgrbackend.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MysqlService {

    private PhotoRepository photoRepository;

    @Autowired
    public MysqlService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }
}
