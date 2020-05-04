package org.bszabat.mgrbackend.controller;

import org.bszabat.mgrbackend.model.Photo;
import org.bszabat.mgrbackend.service.MysqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mysql")
public class MysqlController {

    private MysqlService mysqlService;

    @Autowired
    public MysqlController(MysqlService mysqlService) {
        this.mysqlService = mysqlService;
    }

    @GetMapping("/photos")
    public List<Photo> getAllPhotos() {
        return mysqlService.getAllPhotos();
    }
}
