package com.tenancy.multitenant.service;

import com.tenancy.multitenant.dto.TestDTO;
import com.tenancy.multitenant.dto.roomtypes.RoomTypesRequest;
import com.tenancy.multitenant.exception.CustomException;
import com.tenancy.multitenant.model.RoomTypes;
import com.tenancy.multitenant.model.TestTenant;
import com.tenancy.multitenant.repository.RoomTypesRepository;
import com.tenancy.multitenant.repository.TestTenantRepository;
import com.tenancy.multitenant.response.ApiResponse;
import com.tenancy.multitenant.util.CommonUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TestTenantService {
    private final TestTenantRepository testTenantRepository;
    private final RoomTypesRepository roomTypesRepository;

    public TestTenantService(TestTenantRepository testTenantRepository, RoomTypesRepository roomTypesRepository) {
        this.testTenantRepository = testTenantRepository;
        this.roomTypesRepository = roomTypesRepository;
    }

    public ResponseEntity<ApiResponse> createTest(TestDTO testDTO) {
        TestTenant testTenant = new TestTenant();
        testTenant.setName(testDTO.getName());
        testTenantRepository.save(testTenant);
        return CommonUtil.getOkResponse("test tenant created", "");
    }

    public void saveRoomTypes(RoomTypesRequest roomTypesRequest) {
        boolean isDuplicate = roomTypesRepository.existsByTypeIgnoreCaseAndIsActiveTrue(roomTypesRequest.getType());
        if (isDuplicate) {
            throw new CustomException("Room type already exists");
        }
        RoomTypes roomTypes = new RoomTypes();
        roomTypes.setType(roomTypesRequest.getType());
        roomTypes.setGuestsCount(roomTypesRequest.getGuestCount());
        roomTypes.setIsActive(true);
        roomTypesRepository.save(roomTypes);
    }
    }
