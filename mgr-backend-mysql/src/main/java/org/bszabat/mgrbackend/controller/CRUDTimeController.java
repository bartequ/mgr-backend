package org.bszabat.mgrbackend.controller;

import org.bszabat.mgrbackend.helpers.URLHelper;
import org.bszabat.mgrbackend.service.CRUDTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mysql")
public class CRUDTimeController {

    private CRUDTimeService crudTimeService;

    @Autowired
    public CRUDTimeController(CRUDTimeService crudTimeService) {
        this.crudTimeService = crudTimeService;
    }

    @GetMapping("/create/{quantity}/{method}")
    public String createMultiplePhotosToDb(@PathVariable Integer quantity, @PathVariable String method) {
        return crudTimeService.createMultipleTime(URLHelper.PHOTOS, quantity, method);
    }

    @GetMapping("/read/{method}/{quantity}")
    public String readMultiplePhotos(@PathVariable String method, @PathVariable Integer quantity) {
        return crudTimeService.readMultipleTime(method, quantity);
    }

    @GetMapping("/update/{quantity}")
    public String updateMultiplePhotos(@PathVariable Integer quantity) {
        return crudTimeService.updateMultipleTime(quantity);
    }

    @GetMapping("/delete/{quantity}")
    public String deleteMultiplePhotos(@PathVariable Integer quantity) {
        return crudTimeService.deleteMultipleTime(quantity);
    }
}