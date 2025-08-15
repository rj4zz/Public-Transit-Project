package com.transitmonitor.fleet.fleet_monitor.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class GtfsRealtimeService {
    //TODO: Refactor w/ @Value based external configuration later on
    private static final String GTFS_RT_FEED_URL = "http://data.itsfactory.fi/journeys/api/1/gtfs-rt/vehicle-positions";
    private final HttpClient httpClient;
    public GtfsRealtimeService() {
        this.httpClient = HttpClient.newHttpClient();
    }

    @Scheduled(fixedRate = 30000)
    public void fetchGtfsRealtimeData() {
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(GTFS_RT_FEED_URL)).GET().build();

            HttpResponse<byte[]> response = httpClient.send(request, BodyHandlers.ofByteArray());

            if (response.statusCode() == 200) {
                byte[] rawData = response.body();
                System.out.println("Fetched Data Size: " + rawData.length + " bytes.");
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
