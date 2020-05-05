package org.bszabat.mgrbackend.controller;

import org.bszabat.mgrbackend.helpers.URLHelper;
import org.bszabat.mgrbackend.service.CRUDTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/postgres")
public class CRUDTimeController {

    private CRUDTimeService crudTimeService;

    @Autowired
    public CRUDTimeController(CRUDTimeService crudTimeService) {
        this.crudTimeService = crudTimeService;
    }

    @GetMapping("/save/{quantity}")
    public String saveTablePhotosToDb(@PathVariable Integer quantity) {
        return crudTimeService.createMultipleByOneTime(URLHelper.PHOTOS, quantity);
    }

    @GetMapping("/read/{quantity}")
    public String readMultiplePhotos(@PathVariable Long quantity) {
        return crudTimeService.readMultipleTime(quantity);
    }

    @GetMapping("/update/{quantity}")
    public String updateMultiplePhotos(@PathVariable Long quantity) {
        return crudTimeService.updateMultipleTime(quantity);
    }

    @GetMapping("/delete/{quantity}")
    public String deleteMultiplePhotos(@PathVariable Long quantity) {
        return crudTimeService.deleteMultipleTime(quantity);
    }
}