package com.khasang_incubator.clothesforecast.database;

import android.provider.BaseColumns;

/**
 * Created by aleksandrlihovidov on 25.04.16.
 */
public final class ClothesForecastDBContract {
    public ClothesForecastDBContract() {
    }

    public static abstract class ClothesEntry implements BaseColumns {
        public static final String TABLE_NAME = "clothes";

        public static final String COLUMN_TITLE = "name";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_SEX = "sex";
        public static final String COLUMN_TEMPERATURE_MIN = "t_min";
        public static final String COLUMN_TEMPERATURE_MAX = "t_max";
    }

}
