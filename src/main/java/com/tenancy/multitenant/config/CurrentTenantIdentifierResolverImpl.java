package com.tenancy.multitenant.config;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {
    private final TenantContext tenantContext;

    public CurrentTenantIdentifierResolverImpl(TenantContext tenantContext) {
        this.tenantContext = tenantContext;
    }

    @Override
    public String resolveCurrentTenantIdentifier() {
        return tenantContext.getCurrentTenant(); //  fallback to "master"
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
