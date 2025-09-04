package com.transitmonitor.fleet.fleet_monitor.util;

public class GeoUtils {
    private static final double R = 6371;
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double deltaLatitude = Math.toRadians(lat2 - lat1);
        double deltaLongitude = Math.toRadians(lon2 - lon1);
        double intermediateValue = Math.pow(Math.sin(deltaLatitude / 2), 2) 
                                    + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) 
                                    * Math.pow(Math.sin(deltaLongitude / 2), 2);
        double centralAngle = 2 * Math.atan2(Math.sqrt(intermediateValue), Math.sqrt(1- intermediateValue));
        double distance = R * centralAngle;

        return distance;
    }
}
