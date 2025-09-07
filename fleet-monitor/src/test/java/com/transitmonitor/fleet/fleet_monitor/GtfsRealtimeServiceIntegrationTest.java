package com.transitmonitor.fleet.fleet_monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.transitmonitor.fleet.fleet_monitor.repository.VehiclePositionRepository;
import com.transitmonitor.fleet.fleet_monitor.service.GtfsRealtimeService;

@SpringBootTest
@Testcontainers
public class GtfsRealtimeServiceIntegrationTest {
    @Container
    @ServiceConnection
    private static PostgreSQLContainer<?> dbContainer = new PostgreSQLContainer<>("postgis/postgis:latest");
    
    @Autowired
    private GtfsRealtimeService gtfsRealtimeService;

    @Autowired
    private VehiclePositionRepository vehiclePositionRepository;

}
