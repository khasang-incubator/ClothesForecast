package com.khasang_incubator.clothesforecast.parser;

import com.khasang_incubator.clothesforecast.parser.Coordinate;
import com.khasang_incubator.clothesforecast.parser.Weather;

import java.util.List;

/**
 * Created by aleksandrlihovidov on 17.04.16.
 */
public class WeatherResponse {
    private Coordinate coord;
    private List<Weather> weather;
    private MainInformation main;
    private Wind wind;
    private Clouds clouds;
    private long dt;
    private int id;
    private String name;
    private int cod;

    public Coordinate getCoord() {
        return coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public MainInformation getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public long getDt() {
        return dt;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCod() {
        return cod;
    }
}
