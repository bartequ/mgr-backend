package org.bszabat.mgrbackend.controller.helper;

import org.bszabat.mgrbackend.service.helper.DatabaseOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DatabaseOperationController {

    private DatabaseOperationService databaseOperationService;

    @Autowired
    public DatabaseOperationController(DatabaseOperationService databaseOperationService) {
        this.databaseOperationService = databaseOperationService;
    }

    @GetMapping("/database-operations/operation-lasts/{seconds}")
    public String getDbTimeConsumingOperation(@PathVariable Integer seconds) {
        return databaseOperationService.getDbTimeConsumingOperation(seconds);
    }
}
