package com.transitmonitor.fleet.fleet_monitor.entity;

import java.time.Instant;

import org.springframework.data.annotation.Id;

import com.transitmonitor.fleet.fleet_monitor.model.AnomalyType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class AnomalyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vehicleId;

    //"STALLED_VEHICLE", "VEHICLE_BUNCHING"
    @Enumerated(EnumType.STRING)
    private AnomalyType anomalyType;
    //UNIX Timestamp of when the anomaly was found
    private Instant detectionTimestamp;

    public AnomalyEntity() {
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public AnomalyType getAnomalyType() {
        return anomalyType;
    }

    public void setAnomalyType(AnomalyType anomalyType) {
        this.anomalyType = anomalyType;
    }

    public Instant getDetectionTimestamp() {
        return detectionTimestamp;
    }

    public void setDetectionTimestamp(Instant detectionTimestamp) {
        this.detectionTimestamp = detectionTimestamp;
    }

}
