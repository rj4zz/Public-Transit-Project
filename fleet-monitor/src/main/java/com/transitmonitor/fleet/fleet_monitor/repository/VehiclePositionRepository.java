package com.transitmonitor.fleet.fleet_monitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transitmonitor.fleet.fleet_monitor.entity.VehiclePositionEntity;

public interface VehiclePositionRepository extends JpaRepository<VehiclePositionEntity, Long> {

}
