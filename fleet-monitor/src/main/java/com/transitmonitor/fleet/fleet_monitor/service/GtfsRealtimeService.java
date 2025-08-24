package com.transitmonitor.fleet.fleet_monitor.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.transit.realtime.GtfsRealtime.FeedEntity;
import com.google.transit.realtime.GtfsRealtime.FeedMessage;
import com.google.transit.realtime.GtfsRealtime.VehiclePosition;
import com.transitmonitor.fleet.fleet_monitor.entity.VehiclePositionEntity;
import com.transitmonitor.fleet.fleet_monitor.repository.VehiclePositionRepository;

@Service
public class GtfsRealtimeService {
    //TODO: Refactor w/ @Value based external configuration later on
    private static final String GTFS_RT_FEED_URL = "http://data.itsfactory.fi/journeys/api/1/gtfs-rt/vehicle-positions";
    private final HttpClient httpClient;
    private final VehiclePositionRepository vehiclePositionRepository;

    //Constructor
    public GtfsRealtimeService(VehiclePositionRepository vehiclePositionRepository) {
        this.httpClient = HttpClient.newBuilder().version(Version.HTTP_1_1).build();
        this.vehiclePositionRepository = vehiclePositionRepository;
    }

    @Scheduled(fixedRate = 30000)
    public void fetchGtfsRealtimeData() {
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(GTFS_RT_FEED_URL))
                                             .header("User-Agent", "Fleet Monitor Project")
                                             .GET()
                                             .build();

            HttpResponse<byte[]> response = httpClient.send(request, BodyHandlers.ofByteArray());


            if (response.statusCode() == 200) {

                byte[] rawData = response.body();

                FeedMessage feed = FeedMessage.parseFrom(rawData);

                for (FeedEntity entity : feed.getEntityList()) {

                    if (entity.hasVehicle()) {
                        //Get the Realtime positioning information for the vehicle.
                        VehiclePosition vehicle = entity.getVehicle();

                        //Initiate a new Row
                        VehiclePositionEntity vehiclePositionEntity = new VehiclePositionEntity();
                        
                        //Populate the Row values
                        vehiclePositionEntity.setVehicleId(vehicle.getVehicle().getId());
                        vehiclePositionEntity.setRouteId(vehicle.getTrip().getRouteId());
                        vehiclePositionEntity.setLatitude(vehicle.getPosition().getLatitude());
                        vehiclePositionEntity.setLongitude(vehicle.getPosition().getLongitude());
                        vehiclePositionEntity.setTimestamp(vehicle.getTimestamp());
                        vehiclePositionRepository.save(vehiclePositionEntity);

                    }
                }
            } else {
                System.err.println("Failed to fetch data. Status Code: " + response.statusCode());
            }
        } catch(URISyntaxException e) {
            System.err.println("Cannot parse URI: " +  e.getMessage());
        } catch (IOException e) {
            System.err.println("Network or I/O error during fetch: " + e.getMessage());
        } catch(InterruptedException e) {
            System.err.println("Operation is interrupted: " + e.getMessage());

            Thread.currentThread().interrupt();
        }     
    }
}
