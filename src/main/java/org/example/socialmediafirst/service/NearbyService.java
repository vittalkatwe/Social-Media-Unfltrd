package org.example.socialmediafirst.service;

import org.example.socialmediafirst.model.AppUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NearbyService {


    private static final double EARTH_RADIUS = 6371000; // meters

    public double distanceInMeters(double lat1, double lon1, double lat2, double lon2) {
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

    public List<AppUser> findNearbyUsers(double latitude, double longitude,
                                         List<AppUser> candidates, double radiusMeters) {
        return candidates.stream()
                .filter(u -> u.getLatitude() != null && u.getLongitude() != null)
                .filter(u -> distanceInMeters(
                        latitude, longitude,
                        u.getLatitude(), u.getLongitude()
                ) <= radiusMeters)
                .toList();
    }
}
