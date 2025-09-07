package com.transitmonitor.fleet.fleet_monitor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.transitmonitor.fleet.fleet_monitor.repository.VehiclePositionRepository;
import com.transitmonitor.fleet.fleet_monitor.service.GtfsRealtimeService;

@SpringBootTest
@Testcontainers
public class GtfsRealtimeServiceIntegrationTest {
    @Container
    @ServiceConnection
    private static PostgreSQLContainer<?> dbContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgis/postgis:latest").asCompatibleSubstituteFor("postgres"));
    
    @Autowired
    private GtfsRealtimeService gtfsRealtimeService;

    @Autowired
    private VehiclePositionRepository vehiclePositionRepository;

    @Test
    public void testFetchAndSaveGtfsRealtimeData() throws InterruptedException {
        gtfsRealtimeService.fetchGtfsRealtimeData();
        Thread.sleep(50000);
        int count = (int) vehiclePositionRepository.count();
        Assertions.assertTrue(count > 0);
    }

}
