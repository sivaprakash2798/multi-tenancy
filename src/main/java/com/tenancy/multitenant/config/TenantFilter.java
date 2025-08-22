package com.tenancy.multitenant.config;

import com.tenancy.multitenant.exception.CustomException;
import com.tenancy.multitenant.model.Tenant;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TenantFilter extends OncePerRequestFilter {

    @Value("${app.tenancy.master}")
    private String masterDBName;

    private final TenantProvisioningService provisioningService;

    public TenantFilter(TenantProvisioningService provisioningService) {
        this.provisioningService = provisioningService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpRequest, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String path = httpRequest.getRequestURI();

        try {
            if (path.startsWith("/tenants/create")) {
                TenantContext.setCurrentTenant(masterDBName);
                chain.doFilter(httpRequest, response);
                return;
            }
            String tenantId = httpRequest.getHeader("X-Tenant-ID");
            if (tenantId == null) {
                tenantId = httpRequest.getParameter("tenantId");
            }

            if (tenantId == null) {
                throw new CustomException("Missing X-Tenant-ID header or parameter");
            }
            Tenant tenant = provisioningService.findTenantById(tenantId);
            TenantContext.setCurrentTenant(tenant.getDbName());

            chain.doFilter(httpRequest, response);
        } finally {
            TenantContext.clear();
        }
    }

}
