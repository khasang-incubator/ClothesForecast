package com.khasang_incubator.clothesforecast.parser;

/**
 * Created by aleksandrlihovidov on 21.04.16.
 */
public class MainInformation {
    private double temp; // Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
    private double pressure; // Atmospheric pressure (on the sea level, if there is no sea_level or grnd_level data), hPa
    private int humidity; // Humidity, %
    private double temp_min; // Minimum temperature at the moment. This is deviation from current temp that is possible for large cities and megalopolises geographically expanded (use these parameter optionally). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
    private double temp_max; // Maximum temperature at the moment. This is deviation from current temp that is possible for large cities and megalopolises geographically expanded (use these parameter optionally). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
    private double sea_level; // Atmospheric pressure on the sea level, hPa
    private double grnd_level; // Atmospheric pressure on the ground level, hPa

    public double getTemp() {
        return temp;
    }

    public double getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public double getSea_level() {
        return sea_level;
    }

    public double getGrnd_level() {
        return grnd_level;
    }
}
