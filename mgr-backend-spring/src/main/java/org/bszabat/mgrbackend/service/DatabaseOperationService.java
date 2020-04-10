package org.bszabat.mgrbackend.service;

import org.bszabat.mgrbackend.algorithms.Utils;
import org.bszabat.mgrbackend.exception.ResourceNotFoundException;
import org.bszabat.mgrbackend.model.DatabaseOperationDto;
import org.bszabat.mgrbackend.repository.DatabaseOperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DatabaseOperationService {

    private static JdbcTemplate jdbcTemplate;
    private DatabaseOperationRepository databaseOperationRepository;
    private Utils utils;

    @Autowired
    public DatabaseOperationService(DatabaseOperationRepository databaseOperationRepository, JdbcTemplate jdbcTemplate, Utils utils) {
        DatabaseOperationService.jdbcTemplate = jdbcTemplate;
        this.databaseOperationRepository = databaseOperationRepository;
        this.utils = utils;
    }

    public List<DatabaseOperationDto> getAllDbOperations() {
        return databaseOperationRepository.findAll();
    }

    public List<Map<String, Object>> getAllDbOperationsWithoutOrm() {
        return jdbcTemplate.queryForList("SELECT * FROM db_operations");
    }

    public DatabaseOperationDto getDbOperation(Long operationId) {
        return databaseOperationRepository.findById(operationId).
                orElseThrow(() -> new ResourceNotFoundException("Resource not found with id" + operationId));
    }

    public DatabaseOperationDto createDbOperation(DatabaseOperationDto databaseOperationDto) {
        return databaseOperationRepository.save(databaseOperationDto);
    }

    public DatabaseOperationDto updateDbOperation(Long operationId, DatabaseOperationDto databaseOperationDtoRequest) {
        return databaseOperationRepository.findById(operationId)
                .map(databaseOperationDto -> {
                    databaseOperationDto.setNumber(databaseOperationDtoRequest.getNumber());
                    databaseOperationDto.setDescription(databaseOperationDtoRequest.getDescription());
                    return databaseOperationRepository.save(databaseOperationDto);
                }).orElseThrow(() -> new ResourceNotFoundException("Resource not found with id" + operationId));
    }

    public ResponseEntity<?> deleteDbOperation(Long operationId) {
        return databaseOperationRepository.findById(operationId)
                .map(databaseOperationDto -> {
                    databaseOperationRepository.delete(databaseOperationDto);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Resource not found with id" + operationId));
    }

    public String getDbTimeConsumingOperation(int seconds) {
        return utils.measureTime(seconds, DatabaseOperationService::queryDatabaseFor);
    }

    private static String queryDatabaseFor(int seconds) {
        jdbcTemplate.queryForObject("SELECT pg_sleep(?)", new Object[]{seconds}, String.class);
        return "Query successful";
    }
}
