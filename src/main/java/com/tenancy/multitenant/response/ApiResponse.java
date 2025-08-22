package com.tenancy.multitenant.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {

    private int status;
    private String message;
    private Object data;

    public ApiResponse(int status, String message, Object data){
        this.status=status;
        this.message=message;
        this.data=data;
    }

    public ApiResponse(){
    }
}
