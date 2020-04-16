package org.bszabat.mgrbackend.controller;

import org.bszabat.mgrbackend.model.DatabaseOperationDto;
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

    private DatabaseOperationService databaseOperationService;

    @Autowired
    public DatabaseOperationController(DatabaseOperationService databaseOperationService) {
        this.databaseOperationService = databaseOperationService;
    }

    //TODO Add time measure
    @GetMapping("/database-operations/without-orm")
    public List<Map<String, Object>> getAllDbOperationsWithoutOrm() {
        return databaseOperationService.getAllDbOperationsWithoutOrm();
    }

    @GetMapping("/database-operations/operation-lasts/{seconds}")
    public String getDbTimeConsumingOperation(@PathVariable Integer seconds) {
        return databaseOperationService.getDbTimeConsumingOperation(seconds);
    }

    @GetMapping("/database-operations")
    public List<DatabaseOperationDto> getAllDbOperations() {
        return databaseOperationService.getAllDbOperations();
    }

    @GetMapping("/database-operations/{operationId}")
    public DatabaseOperationDto getDbOperation(@PathVariable Long operationId) {
        return databaseOperationService.getDbOperation(operationId);
    }

    @PostMapping("/database-operations")
    public DatabaseOperationDto createDbOperation(@Valid @RequestBody DatabaseOperationDto databaseOperationDto) {
        return databaseOperationService.createDbOperation(databaseOperationDto);
    }

    @PutMapping("/database-operations/{operationId}")
    public DatabaseOperationDto updateDbOperation(@PathVariable Long operationId,
                                                  @Valid @RequestBody DatabaseOperationDto databaseOperationDtoRequest) {
        return databaseOperationService.updateDbOperation(operationId, databaseOperationDtoRequest);
    }

    @DeleteMapping("/database-operations/{operationId}")
    public ResponseEntity<?> deleteDbOperation(@PathVariable Long operationId) {
        return databaseOperationService.deleteDbOperation(operationId);
    }
}
