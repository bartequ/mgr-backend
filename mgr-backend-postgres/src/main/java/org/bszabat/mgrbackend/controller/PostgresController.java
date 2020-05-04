package org.bszabat.mgrbackend.controller;

import org.bszabat.mgrbackend.model.Photo;
import org.bszabat.mgrbackend.service.PostgresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/postgres")
public class PostgresController {

    private PostgresService postgresService;

    @Autowired
    public PostgresController(PostgresService postgresService) {
        this.postgresService = postgresService;
    }

    @GetMapping("/photos")
    public List<Photo> getAllPhotos() {
        return postgresService.getAllPhotos();
    }
}
