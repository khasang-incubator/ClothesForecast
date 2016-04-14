package com.khasang_incubator.clothesforecast.helpers;

import android.util.Log;

import com.khasang_incubator.clothesforecast.BuildConfig;

/**
 * Служебный класс для вывода логов во время отладки приложения
 */
public class Logger {
    private static boolean isDebug = BuildConfig.DEBUG;

    public static void d(String message) {
        if (isDebug) {
            Log.d("LOG ===> ", message);
        }
    }
}
