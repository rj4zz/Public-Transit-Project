package com.transitmonitor.fleet.fleet_monitor.controller;

import java.time.Instant;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transitmonitor.fleet.fleet_monitor.model.SystemStatus;


@RestController
public class FleetMonitorController {
    @GetMapping("/api/status")
    public SystemStatus getSystemStatus() {
        Instant now = Instant.now();
        return new SystemStatus("System is running", true, now);
    }
}