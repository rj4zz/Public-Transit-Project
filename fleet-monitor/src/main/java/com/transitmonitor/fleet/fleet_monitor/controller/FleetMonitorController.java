package com.transitmonitor.fleet.fleet_monitor.controller;

import java.time.Instant;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transitmonitor.fleet.fleet_monitor.entity.AnomalyEntity;
import com.transitmonitor.fleet.fleet_monitor.model.SystemStatus;
import com.transitmonitor.fleet.fleet_monitor.repository.AnomalyRepository;


@RestController
public class FleetMonitorController {
    private final AnomalyRepository anomalyRepository;

    public FleetMonitorController(AnomalyRepository anomalyRepository) {
        this.anomalyRepository = anomalyRepository;
    }

    @GetMapping("/api/status")
    public SystemStatus getSystemStatus() {
        Instant now = Instant.now();
        return new SystemStatus("System is running", true, now);
    }

    @GetMapping("/api/anomalies")
    public List<AnomalyEntity> getAllAnomalies() {
        return anomalyRepository.findAll();
    }
}