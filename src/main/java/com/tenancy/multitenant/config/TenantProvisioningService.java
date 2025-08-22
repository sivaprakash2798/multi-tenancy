package com.tenancy.multitenant.config;

import com.tenancy.multitenant.model.Tenant;
import com.tenancy.multitenant.repository.TenantRepository;
import com.tenancy.multitenant.service.SqlExecutorService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Value;


@Service
@Getter
@Slf4j
public class TenantProvisioningService {

    @Value("${app.tenancy.master}")
    private String masterDBName;

    public static final String MAIN_DB_URL = "jdbc:mysql://localhost:3306/";
    public static final String TENANT_USERNAME = "root";
    public static final String TENANT_PASSWORD = "root";

    private final TenantRepository tenantRepository;
    private final SqlExecutorService sqlExecutorService;

    private final Map<String, DataSource> tenantDataSources = new ConcurrentHashMap<>();

    public TenantProvisioningService(@Lazy TenantRepository tenantRepository, SqlExecutorService sqlExecutorService) {
        this.tenantRepository = tenantRepository;
        this.sqlExecutorService = sqlExecutorService;
    }

    public String createTenant(String tenantName) throws Exception {
        String tenantId = UUID.randomUUID().toString();
        String dbName = "tenant_" + tenantId.replace("-", "");

        JdbcTemplate adminJdbc = new JdbcTemplate(getMasterAdminDataSource());
        adminJdbc.execute("CREATE DATABASE IF NOT EXISTS " + dbName);

        Tenant tenant = new Tenant();
        tenant.setTenantId(tenantId);
        tenant.setTenantName(tenantName);
        tenant.setDbName(dbName);
        tenant.setJdbcUrl(MAIN_DB_URL + dbName);
        tenant.setUsername(TENANT_USERNAME);
        tenant.setPassword(TENANT_PASSWORD);
        tenant.setIsActive(true);
        tenant.setCreatedAt(LocalDateTime.now());
        tenantRepository.save(tenant);

        // Register tenant DataSource
        DataSource tenantDS = createDataSource(tenant.getJdbcUrl(), tenant.getUsername(), tenant.getPassword());
        tenantDataSources.put(tenantId, tenantDS);
        JdbcTemplate tenantJdbc = new JdbcTemplate(tenantDS);

        sqlExecutorService.executeSqlFile(tenantJdbc, "sqlfile/devguesthstruc.sql");

        log.info("Tenant [{}] created with DB [{}]", tenantId, dbName);

        return tenantId;
    }

    private DataSource createDataSource(String url, String username, String password) {
        return DataSourceBuilder.create()
                .url(url)
                .username(username)
                .password(password)
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }

    private DataSource getMasterAdminDataSource() {
        return createDataSource(MAIN_DB_URL, TENANT_USERNAME, TENANT_PASSWORD);
    }

    public Tenant findTenantById(String tenantId) {
        return tenantRepository.findByTenantId(tenantId)
                .orElseThrow(() -> new RuntimeException("Tenant not found: " + tenantId));
    }

}
