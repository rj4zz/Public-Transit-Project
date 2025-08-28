package com.transitmonitor.fleet.fleet_monitor.service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.transitmonitor.fleet.fleet_monitor.entity.AnomalyEntity;
import com.transitmonitor.fleet.fleet_monitor.model.AnomalyType;
import com.transitmonitor.fleet.fleet_monitor.repository.AnomalyRepository;
import com.transitmonitor.fleet.fleet_monitor.repository.VehiclePositionRepository;
import com.transitmonitor.fleet.fleet_monitor.repository.VehiclePositionRepository.StalledVehicleInfo;

@Service
public class AnomalyDetectionService {
    private static final Logger logger = LoggerFactory.getLogger(AnomalyDetectionService.class);
    private final AnomalyRepository anomalyRepository;
    private final VehiclePositionRepository vehiclePositionRepository;
    private static final Duration STALLED_VEHICLE_THRESHOLD = Duration.ofMinutes(5);

    public AnomalyDetectionService(AnomalyRepository anomalyRepository, VehiclePositionRepository vehiclePositionRepository) {
        this.anomalyRepository = anomalyRepository;
        this.vehiclePositionRepository = vehiclePositionRepository;
    }

    @Scheduled(fixedRate = 60000)
    public void detectAnomalies() {

        //Treshold
        Instant now = Instant.now();
        Instant timestampThresholdInstant = now.minus(STALLED_VEHICLE_THRESHOLD);
        Long timestampThreshold = timestampThresholdInstant.getEpochSecond();

        //Get Stalled Vehicles

        List<StalledVehicleInfo> StalledVehicles = vehiclePositionRepository.findStalledVehicles(timestampThreshold);

        //Register Anomalies
        for (StalledVehicleInfo stalledVehicle : StalledVehicles) {
            AnomalyEntity vehicleAnomalyEntity = new AnomalyEntity();
            vehicleAnomalyEntity.setVehicleId(stalledVehicle.getVehicleId());
            vehicleAnomalyEntity.setAnomalyType(AnomalyType.STALLED_VEHICLE);
            vehicleAnomalyEntity.setDetectionTimestamp(now);
            anomalyRepository.save(vehicleAnomalyEntity);
            logger.info("An anomaly regarding Vehicle {} was registered", vehicleAnomalyEntity.getVehicleId());

        }

    }
}
