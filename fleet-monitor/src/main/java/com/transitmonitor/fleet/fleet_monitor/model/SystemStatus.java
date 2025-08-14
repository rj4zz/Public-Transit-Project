package com.transitmonitor.fleet.fleet_monitor.model;

import java.time.Instant;

public class SystemStatus {
    private final String message;
    private final boolean operational;
    private final Instant timestamp;

    public SystemStatus(final String message, final boolean operational, final Instant timestamp) {
        this.message = message;
        this.operational = operational;
        this.timestamp = timestamp;
    }

    /* Getters */
    public String getMessage() {
        return message;
    }
    public boolean isOperational() {
        return operational;
    }
    public java.time.Instant getTimestamp() {
        return timestamp;
    }
}
