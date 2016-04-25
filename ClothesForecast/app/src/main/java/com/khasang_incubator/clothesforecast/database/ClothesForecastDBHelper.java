package com.khasang_incubator.clothesforecast.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.khasang_incubator.clothesforecast.database.ClothesForecastDBContract.ClothesEntry;

/**
 * Created by aleksandrlihovidov on 25.04.16.
 */
public class ClothesForecastDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "ClothesForecast.db";
    private static final int DB_VERSION = 1;

    public static final String CREATE_TABLE_CLOTHES =
            "create table " + ClothesEntry.TABLE_NAME + " (" +
                    ClothesEntry._ID + " integer primary key, " +
                    ClothesEntry.COLUMN_TITLE + " text, " +
                    ClothesEntry.COLUMN_TYPE + " text, " +
                    ClothesEntry.COLUMN_SEX + " text, " +
                    ClothesEntry.COLUMN_TEMPERATURE_MIN + " integer, " +
                    ClothesEntry.COLUMN_TEMPERATURE_MAX + " integer" +
                    " )";

    public static final String DELETE_TABLE_CLOTHES =
            "drop table if exists " + ClothesEntry.TABLE_NAME;

    public ClothesForecastDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CLOTHES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE_CLOTHES);
        onCreate(db);
    }
}
