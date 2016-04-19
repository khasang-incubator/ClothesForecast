package com.khasang_incubator.clothesforecast;

/**
 * Created by aleksandrlihovidov on 19.04.16.
 */
public class City {
    private int id;
    private String name;
    private Coordinate coord;
    private String country;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinate getCoord() {
        return coord;
    }

    public String getCountry() {
        return country;
    }
}
