package org.bszabat.mgrbackend.controller;

import org.bszabat.mgrbackend.model.DatabaseOperationDto;
import org.bszabat.mgrbackend.service.DatabaseOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class DatabaseOperationController {

    private DatabaseOperationService databaseOperationService;

    @Autowired
    public DatabaseOperationController(DatabaseOperationService databaseOperationService) {
        this.databaseOperationService = databaseOperationService;
    }

    @GetMapping("/database-operations")
    public Page<DatabaseOperationDto> getDbOperations(Pageable pageable) {
        return databaseOperationService.getDbOperations(pageable);
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
