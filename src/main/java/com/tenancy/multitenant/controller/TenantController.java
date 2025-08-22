package com.tenancy.multitenant.controller;
import com.tenancy.multitenant.config.TenantProvisioningService;
import org.springframework.web.bind.annotation.*;

/**REST API to create a new tenant dynamically.
 **/
@RestController
@RequestMapping("/tenants")
public class TenantController {

    private final TenantProvisioningService tenantService;

    public TenantController(TenantProvisioningService tenantService) {
        this.tenantService = tenantService;
    }

    @PostMapping("/create")
    public String createTenant(@RequestParam String tenName) throws Exception {
        String tenId = tenantService.createTenant(tenName);
        return "Tenant " + tenId + " created!";
    }
}
