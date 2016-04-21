package com.khasang_incubator.clothesforecast.helpers;

import android.net.Uri;

import com.khasang_incubator.clothesforecast.secure.AppVariables;

import java.util.Locale;

/**
 * Класс, в котором формируются запросы
 */
public class RequestMaker {
    public static final int ERROR = -1;
    public static final int TYPE_WEATHER = 1;
    public static final int TYPE_FORECAST = 2;

    private final static String BASE_URL = "http://api.openweathermap.org/data/2.5";
    private final static String WEATHER = "/weather";
    private final static String FORECAST = "/forecast";
    private final static String MODE = "json";

    public static final String getWeatherFor(String cityName) {
        return getRequestFor(cityName, WEATHER);
    }

    public static final String getForecastFor(String cityName) {
        return getRequestFor(cityName, FORECAST);
    }

    private static final String getRequestFor(String cityName, String what) {
        String request = Uri.parse(BASE_URL + what).buildUpon()
                .appendQueryParameter("q", cityName)
                .appendQueryParameter("mode", MODE)
//                .appendQueryParameter("cnt", Integer.toString(3))
                .appendQueryParameter("lang", Locale.getDefault().toString())
                .appendQueryParameter("APPID", AppVariables.API_KEY)
                .build().toString();
        Logger.d(request);
        return request;
    }

    public static String getRequestStringFor(int type, String cityName) {
        String requestType = WEATHER;
        switch (type) {
            case TYPE_FORECAST:
                requestType = FORECAST;
                break;
            default:
                break;
        }

        String request = Uri.parse(BASE_URL + requestType).buildUpon()
                .appendQueryParameter("q", cityName)
                .appendQueryParameter("APPID", AppVariables.API_KEY)
                .appendQueryParameter("lang", Locale.getDefault().getLanguage())
                .build().toString();

        Logger.d(request);

        return request;
    }
}
