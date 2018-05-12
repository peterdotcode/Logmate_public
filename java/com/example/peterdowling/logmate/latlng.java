package com.example.peterdowling.logmate;

/**
 * Created by peterdowling on 22/04/2018.
 * This class is currently not used in production
 */
public class latlng {
    private static double staticLongitude;
    private double Longitude;
    private double Latitude;


    /**
     * Instantiates a new Latlng.
     *
     * @param longitude the longitude
     * @param latitude  the latitude
     */
    public latlng(double longitude, double latitude) {
        Longitude = longitude;
        Latitude = latitude;
    }

    /**
     * Gets longitude.
     *
     * @return the longitude
     */
    public static double getLongitude() {
        return staticLongitude;
    }

    /**
     * Sets longitude.
     *
     * @param longitude the longitude
     */
    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    /**
     * Gets latitude.
     *
     * @return the latitude
     */
    public double getLatitude() {
        return Latitude;
    }

    /**
     * Sets latitude.
     *
     * @param latitude the latitude
     */
    public void setLatitude(double latitude) {
        Latitude = latitude;
    }
}