package com.pm.patientservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Properties;

@Slf4j
@Service
public class MessageService {
    private final Properties messages;

    public MessageService(@Value("classpath:static/messages/success-message") Resource resource) {
        this.messages = new Properties();
        try {
            messages.load(resource.getInputStream());
            log.info("Messages loaded successfully");
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load messages", e);
        }
    }

    public String getMessage(int code) {
        return messages.getProperty(String.valueOf(code), "Operation completed");
    }
}
