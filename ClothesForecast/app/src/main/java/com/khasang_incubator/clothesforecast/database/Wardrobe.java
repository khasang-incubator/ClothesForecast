package com.khasang_incubator.clothesforecast.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.khasang_incubator.clothesforecast.database.ClothesForecastDBContract.ClothesEntry;

import java.util.ArrayList;

import static com.khasang_incubator.clothesforecast.database.Clothes.*;

/**
 * Created by aleksandrlihovidov on 25.04.16.
 */
public class Wardrobe {
    private ClothesForecastDBHelper helper;

    public Wardrobe(Context context) {
        this.helper = new ClothesForecastDBHelper(context.getApplicationContext());
    }

    public ArrayList<String> getClothesKitSuits(Type type, Sex sex, int temperature) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] projection = {ClothesEntry.COLUMN_TITLE};
        String selection =
                ClothesEntry.COLUMN_TYPE + " = ? and (" +
                ClothesEntry.COLUMN_SEX + " = ? or " +
                ClothesEntry.COLUMN_SEX + " = ?) and " +
                ClothesEntry.COLUMN_TEMPERATURE_MIN + " < ? and " +
                ClothesEntry.COLUMN_TEMPERATURE_MAX + " > ?";
        String[] selectionArgs = {
                type.toString(),
                Sex.UNISEX.toString(),
                sex.toString(),
                Integer.toString(temperature),
                Integer.toString(temperature)
        };

        Cursor c = db.query(
                ClothesEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        ArrayList<String> resultKit = new ArrayList<>();
        try {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                resultKit.add(c.getString(c.getColumnIndex(ClothesEntry.COLUMN_TITLE)));
                c.moveToNext();
            }
        } finally {
            c.close();
            db.close();
        }

        return resultKit;
    }

    public long add(Clothes clothes) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(ClothesEntry.COLUMN_TITLE, clothes.getName());
        cv.put(ClothesEntry.COLUMN_TYPE, clothes.getType().toString());
        cv.put(ClothesEntry.COLUMN_SEX, clothes.getSex().toString());
        cv.put(ClothesEntry.COLUMN_TEMPERATURE_MIN, clothes.getTemperatureRange()[0]);
        cv.put(ClothesEntry.COLUMN_TEMPERATURE_MAX, clothes.getTemperatureRange()[1]);

        long newRowId;
        newRowId = db.insert(ClothesEntry.TABLE_NAME, null, cv);

        db.close();

        return newRowId;
    }
}
