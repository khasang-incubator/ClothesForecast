package com.khasang_incubator.clothesforecast.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.khasang_incubator.clothesforecast.database.Clothes.Sex;
import com.khasang_incubator.clothesforecast.database.Clothes.Type;
import com.khasang_incubator.clothesforecast.database.ClothesForecastDBContract.ClothesEntry;

import java.util.ArrayList;

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
        initClothesKit(db);
    }

    private void initClothesKit(SQLiteDatabase db) {
        ArrayList<Clothes> clothesKit = new ArrayList<>();
        clothesKit.add(new Clothes(Type.TOP, "shirt", Sex.MALE, new int[]{-70, 70}));
        clothesKit.add(new Clothes(Type.LOWER, "pants", Sex.MALE, new int[]{-70, 70}));
        clothesKit.add(new Clothes(Type.LOWER, "skirt", Sex.FEMALE, new int[]{-70, 70}));
        clothesKit.add(new Clothes(Type.TOP, "jumper", Sex.UNISEX, new int[]{-70, 15}));
        clothesKit.add(new Clothes(Type.TOP, "t_shirt", Sex.UNISEX, new int[]{30, 70}));

        for (Clothes clothes : clothesKit) {
            ContentValues cv = new ContentValues();

            cv.put(ClothesEntry.COLUMN_TITLE, clothes.getName());
            cv.put(ClothesEntry.COLUMN_TYPE, clothes.getType().toString());
            cv.put(ClothesEntry.COLUMN_SEX, clothes.getSex().toString());
            cv.put(ClothesEntry.COLUMN_TEMPERATURE_MIN, clothes.getTemperatureRange()[0]);
            cv.put(ClothesEntry.COLUMN_TEMPERATURE_MAX, clothes.getTemperatureRange()[1]);

            db.insert(ClothesEntry.TABLE_NAME, null, cv);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE_CLOTHES);
        onCreate(db);
    }
}
