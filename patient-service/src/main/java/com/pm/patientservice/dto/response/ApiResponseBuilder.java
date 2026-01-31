package com.pm.patientservice.dto.response;

import com.pm.patientservice.service.MessageService;
import org.springframework.stereotype.Component;

@Component
public class ApiResponseBuilder<T> {
    private final MessageService messageService;

    public ApiResponseBuilder(MessageService messageService) {
        this.messageService = messageService;
    }

    public ApiResponse<T> success(int code, T data) {
        return new ApiResponse<>(messageService.getMessage(code), code, data);
    }
}

