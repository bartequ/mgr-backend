package org.bszabat.mgrbackend.controller;

import org.bszabat.mgrbackend.service.SyncApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sync-api")
public class SyncApiController {

    private SyncApiService syncApiService;

    @Autowired
    public SyncApiController(SyncApiService syncApiService) {
        this.syncApiService = syncApiService;
    }

    @GetMapping("/photos/{quantity}")
    public String getAllPhotos(@PathVariable Integer quantity) {
        return syncApiService.getAllPhotosTime(quantity);
    }
}

