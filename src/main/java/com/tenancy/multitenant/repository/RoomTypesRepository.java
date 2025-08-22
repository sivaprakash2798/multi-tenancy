package com.tenancy.multitenant.repository;

import com.tenancy.multitenant.model.RoomTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoomTypesRepository extends JpaRepository<RoomTypes,Long> {

    boolean existsByTypeIgnoreCaseAndIsActiveTrue(String type);

}
