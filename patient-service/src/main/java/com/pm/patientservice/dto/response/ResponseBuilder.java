package com.pm.patientservice.dto.response;

import com.pm.patientservice.service.MessageService;
import org.springframework.stereotype.Component;

@Component
public class ResponseBuilder<T> {
    private final MessageService messageService;

    public ResponseBuilder(MessageService messageService) {
        this.messageService = messageService;
    }

    public ApiResponse<T> success(int code, T data) {
        return new ApiResponse<>(messageService.getMessage(code), code, data);
    }
}

