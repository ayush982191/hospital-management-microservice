package com.pm.patientservice.dto.response;

import com.pm.patientservice.service.MessageService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Data
@Slf4j
public class ApiResponse<T> {
    private String message;
    private LocalDateTime timestamp;
    private int statusCode;
    private T data ;
    public ApiResponse(String message, int statusCode, T data) {
        this.message = message;
        this.statusCode = statusCode;
        this.data = data;
        this.timestamp = LocalDateTime.now();

    }


}
