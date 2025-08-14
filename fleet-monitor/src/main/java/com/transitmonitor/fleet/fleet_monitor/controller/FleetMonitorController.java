package com.transitmonitor.fleet.fleet_monitor.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class FleetMonitorController {
    @GetMapping("/api/status")
    public String getSystemStatus() {
        return "System is Running";
    }
}
