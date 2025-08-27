package com.transitmonitor.fleet.fleet_monitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transitmonitor.fleet.fleet_monitor.entity.AnomalyEntity;

public interface AnomalyRepository extends JpaRepository<AnomalyEntity, Long> {

}
