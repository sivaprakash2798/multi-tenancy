package com.tenancy.multitenant.exception;

import com.tenancy.multitenant.util.CommonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(Exception ex){
        String message = ex.getMessage();
        return ResponseEntity.internalServerError().body(CommonUtil.getApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message));
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> customValidationException(CustomException ex){
        return ResponseEntity.badRequest().body(CommonUtil.getApiResponse(HttpStatus.BAD_REQUEST.value(),ex.getMessage()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex){
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(CommonUtil.getApiResponse(HttpStatus.FORBIDDEN.value(),
                        "Access denied"));
    }
}
