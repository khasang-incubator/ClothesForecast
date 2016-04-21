package com.khasang_incubator.clothesforecast.parser;

import java.util.List;

/**
 * Created by aleksandrlihovidov on 19.04.16.
 */
public class ForecastResponse {
    private City city;
    private int cod;
    private double message;
    private int cnt;
    private List<Forecast> list;

    public City getCity() {
        return city;
    }

    public int getCod() {
        return cod;
    }

    public double getMessage() {
        return message;
    }

    public int getCnt() {
        return cnt;
    }

    public List<Forecast> getList() {
        return list;
    }
}
