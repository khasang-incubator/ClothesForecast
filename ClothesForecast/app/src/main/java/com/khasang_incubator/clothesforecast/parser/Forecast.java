package com.khasang_incubator.clothesforecast.parser;

import java.util.List;

/**
 * Created by aleksandrlihovidov on 21.04.16.
 */
public class Forecast {
    private long dt;
    private MainInformation main;
    private List<Weather> weather;
    private Clouds clouds;
    private Wind wind;
    private String dt_txt;

    public long getDt() {
        return dt;
    }

    public MainInformation getMain() {
        return main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public String getDt_txt() {
        return dt_txt;
    }
}
