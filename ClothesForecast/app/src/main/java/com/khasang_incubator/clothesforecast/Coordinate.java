package com.khasang_incubator.clothesforecast;

/**
 * Created by aleksandrlihovidov on 17.04.16.
 */
public class Coordinate {
    private double lon;
    private double lat;

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    @Override
    public String toString() {
        return String.format("(%f; %f)", lon, lat);
    }
}
