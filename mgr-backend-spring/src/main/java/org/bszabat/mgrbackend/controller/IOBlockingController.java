package org.bszabat.mgrbackend.controller;

import org.bszabat.mgrbackend.model.Photo;
import org.bszabat.mgrbackend.service.IOBlockingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ioblocking")
public class IOBlockingController {

    private IOBlockingService ioBlockingService;

    @Autowired
    public IOBlockingController(IOBlockingService ioBlockingService) {
        this.ioBlockingService = ioBlockingService;
    }

    @GetMapping("/photos")
    public List<Photo> getAllPhotos() {
        return ioBlockingService.getAllPhotos();
    }
}

