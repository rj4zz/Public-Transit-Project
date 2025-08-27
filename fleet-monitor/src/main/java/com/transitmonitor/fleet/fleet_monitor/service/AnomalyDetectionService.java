package com.transitmonitor.fleet.fleet_monitor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.transitmonitor.fleet.fleet_monitor.repository.AnomalyRepository;

@Service
public class AnomalyDetectionService {
    private static final Logger logger = LoggerFactory.getLogger(AnomalyDetectionService.class);
    private final AnomalyRepository anomalyRepository;

    public AnomalyDetectionService(AnomalyRepository anomalyRepository) {
        this.anomalyRepository = anomalyRepository;
    }

    @Scheduled(fixedRate = 60000)
    public void detectAnomalies() {
        logger.info("Running anomaly detection");
    }
}
