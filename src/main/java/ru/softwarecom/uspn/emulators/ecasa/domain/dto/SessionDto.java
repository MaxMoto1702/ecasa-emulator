package ru.softwarecom.uspn.emulators.ecasa.domain.dto;

import lombok.Data;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;

@Data
public class SessionDto {
    private String id;
    private Map<String, Object> attributes;
    private Instant creationTime;
    private Instant lastAccessedTime;
    private Duration maxInactiveInterval;
    private boolean expired;
}
