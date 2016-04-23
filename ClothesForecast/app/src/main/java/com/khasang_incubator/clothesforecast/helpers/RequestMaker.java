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

    private final static String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private final static String WEATHER = "weather";
    private final static String FORECAST = "forecast";
    private static final String UNITS = "metric";

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
                .appendQueryParameter("units", UNITS)
                .appendQueryParameter("lang", Locale.getDefault().getLanguage())
                .build().toString();

        Logger.d(request);

        return request;
    }
}
