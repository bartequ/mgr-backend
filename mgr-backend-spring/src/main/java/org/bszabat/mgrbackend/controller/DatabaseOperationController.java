package org.bszabat.mgrbackend.controller;

import org.bszabat.mgrbackend.model.DatabaseOperation;
import org.bszabat.mgrbackend.service.DatabaseOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DatabaseOperationController {

    //TODO Check statuses when deleting and updating
    private DatabaseOperationService databaseOperationService;

    @Autowired
    public DatabaseOperationController(DatabaseOperationService databaseOperationService) {
        this.databaseOperationService = databaseOperationService;
    }

    //TODO Add time measure - test comparison time db operations Postgres/Mongo
    @GetMapping("/database-operations/without-orm")
    public List<Map<String, Object>> getAllDbOperationsWithoutOrm() {
        return databaseOperationService.getAllDbOperationsWithoutOrm();
    }

    @GetMapping("/database-operations/operation-lasts/{seconds}")
    public String getDbTimeConsumingOperation(@PathVariable Integer seconds) {
        return databaseOperationService.getDbTimeConsumingOperation(seconds);
    }

    @GetMapping("/database-operations")
    public List<DatabaseOperation> getAllDbOperations() {
        return databaseOperationService.getAllDbOperations();
    }

    @GetMapping("/database-operations/{operationId}")
    public DatabaseOperation getDbOperation(@PathVariable Long operationId) {
        return databaseOperationService.getDbOperation(operationId);
    }

    //TODO Should return status 201
    @PostMapping("/database-operations")
    public DatabaseOperation createDbOperation(@Valid @RequestBody DatabaseOperation databaseOperation) {
        return databaseOperationService.createDbOperation(databaseOperation);
    }

    @PutMapping("/database-operations/{operationId}")
    public DatabaseOperation updateDbOperation(@PathVariable Long operationId,
                                               @Valid @RequestBody DatabaseOperation databaseOperationRequest) {
        return databaseOperationService.updateDbOperation(operationId, databaseOperationRequest);
    }

    @DeleteMapping("/database-operations/{operationId}")
    public ResponseEntity<?> deleteDbOperation(@PathVariable Long operationId) {
        return databaseOperationService.deleteDbOperation(operationId);
    }
}
