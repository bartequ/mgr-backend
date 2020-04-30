package org.bszabat.mgrbackend.controller;

import org.bszabat.mgrbackend.model.Photo;
import org.bszabat.mgrbackend.service.SyncApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sync-api")
public class SyncApiController {

    private SyncApiService syncApiService;

    @Autowired
    public SyncApiController(SyncApiService syncApiService) {
        this.syncApiService = syncApiService;
    }

    @GetMapping("/photos")
    public List<Photo> getAllPhotos() {
        return syncApiService.getAllPhotos();
    }
}

