package com.transitmonitor.fleet.fleet_monitor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.transitmonitor.fleet.fleet_monitor.util.GeoUtils;

public class GeoUtilsTest {

    @Test
    public void testCalculateDistance_SamePointsShouldReturnZero() {
        double actualDistance = GeoUtils.calculateDistance(0.0, 0.0, 0.0, 0.0);
        assertEquals(0.0, actualDistance, 0.0001);
    }

    @Test
    public void testCalculateDistance_BetweenTwoKnownPoints() {
        //Eiffel Tower Coordinates
        double lat1 = 48.8584, lon1 = 2.2945;

        //Louvre Coordinates
        double lat2 = 48.8606, lon2 = 2.3376;
        double expectedDistance = 3.21; //in KM
        double actualDistance = GeoUtils.calculateDistance(lat1, lon1, lat2, lon2);
        assertEquals(expectedDistance, actualDistance, 0.5);
    }

}
