package org.bszabat.mgrbackend.service.helper;

import org.bszabat.mgrbackend.helpers.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DatabaseOperationService {

    private static JdbcTemplate jdbcTemplate;
    private Utils utils;

    @Autowired
    public DatabaseOperationService(JdbcTemplate jdbcTemplate, Utils utils) {
        DatabaseOperationService.jdbcTemplate = jdbcTemplate;
        this.utils = utils;
    }

    public String getDbTimeConsumingOperation(int seconds) {
        return utils.measureTime(seconds, DatabaseOperationService::queryDatabaseFor);
    }

    private static String queryDatabaseFor(int seconds) {
        jdbcTemplate.queryForObject("SELECT pg_sleep(?)", new Object[]{seconds}, String.class);
        return "Query successful";
    }
}
