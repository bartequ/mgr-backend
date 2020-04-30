package org.bszabat.mgrbackend.controller;

import org.bszabat.mgrbackend.helpers.URLHelper;
import org.bszabat.mgrbackend.service.ObjectSerializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ObjectSerializationController {

    private ObjectSerializationService objectSerializationService;

    @Autowired
    public ObjectSerializationController(ObjectSerializationService objectSerializationService) {
        this.objectSerializationService = objectSerializationService;
    }

    @GetMapping("/object-serialization/{serializeTo}/{quantity}")
    public String serializeJson(@PathVariable String serializeTo, @PathVariable Integer quantity) {
        return objectSerializationService.fetchTestPhotosAndSerializeTime(URLHelper.PHOTOS, quantity, serializeTo);
    }
}
