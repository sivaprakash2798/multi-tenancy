package com.tenancy.multitenant.repository;

import com.tenancy.multitenant.model.TestTenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestTenantRepository extends JpaRepository<TestTenant, Long> {
}
