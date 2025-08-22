package com.tenancy.multitenant.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TenantContext {

    @Value("${app.tenancy.master}")
    private String masterDBName;

    private static final ThreadLocal<String> currentTenant = new ThreadLocal<>();

    public static void setCurrentTenant(String tenantId) {
        currentTenant.set(tenantId);
    }

    public  String getCurrentTenant() {
        return currentTenant.get() != null ? currentTenant.get() : masterDBName;
    }

    public static void clear() {
        currentTenant.remove();
    }
}
