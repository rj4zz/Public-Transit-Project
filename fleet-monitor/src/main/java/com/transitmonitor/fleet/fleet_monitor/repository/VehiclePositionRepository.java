package com.transitmonitor.fleet.fleet_monitor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.transitmonitor.fleet.fleet_monitor.entity.VehiclePositionEntity;

public interface VehiclePositionRepository extends JpaRepository<VehiclePositionEntity, Long> {

    public interface StalledVehicleInfo {
        String getVehicleId();
        long getLatestTimestamp();
    }
    

    @Query("SELECT vp.vehicleId, MAX(vp.timestamp) " +
           "FROM VehiclePositionEntity vp " +
           "GROUP BY vp.vehicleId " +
           "HAVING MAX(vp.timestamp) < :timestampThreshold")
    List<StalledVehicleInfo> findStalledVehicles(@Param("timestampThreshold") long timestampThreshold);
}
