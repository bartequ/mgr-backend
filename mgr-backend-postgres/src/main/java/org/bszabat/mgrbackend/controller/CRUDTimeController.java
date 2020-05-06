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

    @GetMapping("/save/{quantity}/{method}")
    public String saveTablePhotosToDb(@PathVariable Integer quantity, @PathVariable String method) {
        return crudTimeService.createMultiple(URLHelper.PHOTOS, quantity, method);
    }

    @GetMapping("/read/{method}/{quantity}/{id}")
    public String readMultiplePhotos(@PathVariable String method, @PathVariable Integer quantity, @PathVariable Long id) {
        return crudTimeService.readMultipleTime(method, quantity, id);
    }

    @GetMapping("/update/{quantity}/{startingId}")
    public String updateMultiplePhotos(@PathVariable Integer quantity, @PathVariable Long startingId) {
        return crudTimeService.updateMultipleTime(quantity, startingId);
    }

    @GetMapping("/delete/{quantity}/{startingId}")
    public String deleteMultiplePhotos(@PathVariable Integer quantity, @PathVariable Long startingId) {
        return crudTimeService.deleteMultipleTime(quantity, startingId);
    }
}