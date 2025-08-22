package com.tenancy.multitenant.controller;

import com.tenancy.multitenant.dto.TestDTO;
import com.tenancy.multitenant.dto.roomtypes.RoomTypesRequest;
import com.tenancy.multitenant.response.ApiResponse;
import com.tenancy.multitenant.service.TestTenantService;
import com.tenancy.multitenant.util.CommonUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TenantTest {
    private final TestTenantService testTenantService;

    public TenantTest(TestTenantService testTenantService) {
        this.testTenantService = testTenantService;
    }


    @PostMapping
    private ResponseEntity<ApiResponse> createTest(@RequestHeader("X-Tenant-ID") String tenantId,
                                                   @RequestBody TestDTO testDTO) {
        return testTenantService.createTest(testDTO);
    }

    @PostMapping("/room-type")
    public ResponseEntity<ApiResponse> saveRoomTypes(@RequestBody RoomTypesRequest roomTypesRequest) {
        testTenantService.saveRoomTypes(roomTypesRequest);
        return CommonUtil.getOkResponse("Room type created successfully ", "");
    }
}
