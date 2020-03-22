package org.bszabat.mgrbackend.service;

import org.bszabat.mgrbackend.exception.ResourceNotFoundException;
import org.bszabat.mgrbackend.model.DatabaseOperationDto;
import org.bszabat.mgrbackend.repository.DatabaseOperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DatabaseOperationService {

    private DatabaseOperationRepository databaseOperationRepository;

    @Autowired
    public DatabaseOperationService(DatabaseOperationRepository databaseOperationRepository) {
        this.databaseOperationRepository = databaseOperationRepository;
    }

    public Page<DatabaseOperationDto> getDbOperations(Pageable pageable) {
        return databaseOperationRepository.findAll(pageable);
    }

    public DatabaseOperationDto createDbOperation(DatabaseOperationDto databaseOperationDto) {
        return databaseOperationRepository.save(databaseOperationDto);
    }

    public DatabaseOperationDto updateDbOperation(Long operationId, DatabaseOperationDto databaseOperationDtoRequest) {
        return databaseOperationRepository.findById(operationId)
                .map(databaseOperationDto -> {
                    databaseOperationDto.setTitle(databaseOperationDtoRequest.getTitle());
                    databaseOperationDto.setDescription(databaseOperationDtoRequest.getDescription());
                    return databaseOperationRepository.save(databaseOperationDto);
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + operationId));
    }

    public ResponseEntity<?> deleteDbOperation(Long operationId) {
        return databaseOperationRepository.findById(operationId)
                .map(databaseOperationDto -> {
                    databaseOperationRepository.delete(databaseOperationDto);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + operationId));
    }
}
