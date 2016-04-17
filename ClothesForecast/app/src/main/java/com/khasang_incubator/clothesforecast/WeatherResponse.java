package com.khasang_incubator.clothesforecast;

import java.util.List;

/**
 * Created by aleksandrlihovidov on 17.04.16.
 */
public class WeatherResponse {
    private int cod;
    private String name;
    private int id;
    private long dt;
    private Coordinate coord;
    private List<Weather> weather;

    public int getCod() {
        return cod;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public long getDt() {
        return dt;
    }

    public Coordinate getCoord() {
        return coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }
}
