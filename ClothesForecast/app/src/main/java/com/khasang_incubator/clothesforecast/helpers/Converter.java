package com.khasang_incubator.clothesforecast.helpers;

import com.google.gson.Gson;
import com.khasang_incubator.clothesforecast.parser.Forecast;
import com.khasang_incubator.clothesforecast.parser.ForecastResponse;
import com.khasang_incubator.clothesforecast.parser.WeatherResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by aleksandrlihovidov on 21.04.16.
 */
public class Converter {
    public static final String convertUTC2LocalTime(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        return sdf.format(new Date(time * 1000));
    }
    
    public static final String convertWeatherResponseToString(String response) {
        Gson gson = new Gson();
        WeatherResponse weatherResponse = gson.fromJson(response, WeatherResponse.class);

        String result = "";
        result += Converter.convertUTC2LocalTime(weatherResponse.getDt()) + "\n";
        result += weatherResponse.getName() + "\n";
        result += Integer.toString((int) (weatherResponse.getMain().getTemp() + 0.5)) + "\n";
        result += weatherResponse.getWeather().get(0).getDescription();

        return result;
    }

    public static final String convertForecastResponseToString(String response) {
        Gson gson = new Gson();
        ForecastResponse forecastResponse = gson.fromJson(response, ForecastResponse.class);

        String result = "";
        result += forecastResponse.getCity().getName() + "\n";

        List<Forecast> list = forecastResponse.getList();
        for (Forecast forecast : list) {
            result += Converter.convertUTC2LocalTime(forecast.getDt()) + "\n";
            result += Integer.toString((int) (forecast.getMain().getTemp() + 0.5)) + "\n";
            result += forecast.getWeather().get(0).getDescription() + "\n--------\n";
        }

        return result;
    }
}
