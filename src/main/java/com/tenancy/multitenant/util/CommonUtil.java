package com.tenancy.multitenant.util;

import com.tenancy.multitenant.response.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CommonUtil {
    private CommonUtil(){
    }

    public static ApiResponse getApiResponse(int status, String message){
        return new ApiResponse(status,message,null);
    }

    public static <T> ResponseEntity<ApiResponse> getOkResponse(String message, T data) {
        return ResponseEntity.ok(getApiResponse(HttpStatus.OK.value(), message,data));
    }

    public static <T> ApiResponse getApiResponse(int status, String message,T data){
        return new ApiResponse(status,message,data);
    }

    public static <T> ApiResponse getApiResponse(int status, String message, Page<T> page) {
        ApiResponse apiResponse = new ApiResponse(status, message, page.getContent());
        apiResponse.setHasNext(page.hasNext());
        apiResponse.setHasPrevious(page.hasPrevious());
        apiResponse.setTotalRecordCount(page.getTotalElements());
        apiResponse.setTotalPageCount(page.getTotalPages());
        return apiResponse;
    }

    public static <T> ResponseEntity<ApiResponse> getOkResponse(String message, Page<T> page){
        return ResponseEntity.ok(getApiResponse(HttpStatus.OK.value(), message, page));
    }
}
