package com.tenancy.multitenant.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Service
public class SqlExecutorService {
    public void executeSqlFile(JdbcTemplate jdbcTemplate, String classpathLocation) throws Exception {
        ClassPathResource resource = new ClassPathResource(classpathLocation);
        String sql = new BufferedReader(new InputStreamReader(resource.getInputStream()))
                .lines()
                .collect(Collectors.joining("\n"));

        for (String statement : sql.split(";")) {
            if (!statement.trim().isEmpty()) {
                jdbcTemplate.execute(statement);
            }
        }
    }
}

