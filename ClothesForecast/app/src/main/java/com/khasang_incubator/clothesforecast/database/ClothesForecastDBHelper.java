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
//        clothesKit.add(new Clothes(Type.TOP, "shirt", Sex.MALE, new int[]{-70, 70}));
//        clothesKit.add(new Clothes(Type.LOWER, "pants", Sex.MALE, new int[]{-70, 70}));
//        clothesKit.add(new Clothes(Type.LOWER, "skirt", Sex.FEMALE, new int[]{-70, 70}));
//        clothesKit.add(new Clothes(Type.TOP, "jumper", Sex.UNISEX, new int[]{-70, 15}));
//        clothesKit.add(new Clothes(Type.TOP, "t_shirt", Sex.UNISEX, new int[]{30, 70}));
        clothesKit.add(new Clothes(Type.TOP, "рубашка", Sex.UNISEX, new int[]{-70, 70}));
        clothesKit.add(new Clothes(Type.TOP, "футболка", Sex.UNISEX, new int[]{18, 70}));
        clothesKit.add(new Clothes(Type.TOP, "блуза", Sex.FEMALE, new int[]{-70, 70}));
        clothesKit.add(new Clothes(Type.TOP, "кофта", Sex.UNISEX, new int[]{-70, 70}));
        clothesKit.add(new Clothes(Type.TOP, "платье", Sex.FEMALE, new int[]{-70, 70}));
        clothesKit.add(new Clothes(Type.TOP, "пиджак", Sex.UNISEX, new int[]{-70, 70}));
        clothesKit.add(new Clothes(Type.LOWER, "брюки", Sex.UNISEX, new int[]{-70, 70}));
        clothesKit.add(new Clothes(Type.LOWER, "джинсы", Sex.UNISEX, new int[]{-70, 70}));
        clothesKit.add(new Clothes(Type.LOWER, "шорты", Sex.UNISEX, new int[]{20, 70}));
        clothesKit.add(new Clothes(Type.LOWER, "юбка", Sex.FEMALE, new int[]{-70, 70}));
        clothesKit.add(new Clothes(Type.OVERDRESS, "плащ", Sex.UNISEX, new int[]{5, 19}));
        clothesKit.add(new Clothes(Type.OVERDRESS, "куртка", Sex.UNISEX, new int[]{-1, 15}));
        clothesKit.add(new Clothes(Type.OVERDRESS, "теплая куртка", Sex.UNISEX, new int[]{-30, -2}));
        clothesKit.add(new Clothes(Type.OVERDRESS, "осеннее пальто", Sex.UNISEX, new int[]{0, 19}));
        clothesKit.add(new Clothes(Type.OVERDRESS, "зимнее пальто", Sex.UNISEX, new int[]{-20, -1}));
        clothesKit.add(new Clothes(Type.OVERDRESS, "шуба", Sex.FEMALE, new int[]{-70, -1}));
        clothesKit.add(new Clothes(Type.SHOES, "сандалии", Sex.UNISEX, new int[]{20, 70}));
        clothesKit.add(new Clothes(Type.SHOES, "шлепки", Sex.UNISEX, new int[]{20, 70}));
        clothesKit.add(new Clothes(Type.SHOES, "босоножки", Sex.FEMALE, new int[]{20, 70}));
        clothesKit.add(new Clothes(Type.SHOES, "туфли", Sex.UNISEX, new int[]{15, 25}));
        clothesKit.add(new Clothes(Type.SHOES, "осенние ботинки", Sex.UNISEX, new int[]{0, 19}));
        clothesKit.add(new Clothes(Type.SHOES, "зимние ботинки", Sex.UNISEX, new int[]{-30, -1}));
        clothesKit.add(new Clothes(Type.SHOES, "осенние сапоги", Sex.UNISEX, new int[]{0, 15}));
        clothesKit.add(new Clothes(Type.SHOES, "зимние сапоги", Sex.UNISEX, new int[]{-30, -1}));
        clothesKit.add(new Clothes(Type.HEADWEAR, "шапка", Sex.UNISEX, new int[]{-70, 10}));
        clothesKit.add(new Clothes(Type.HEADWEAR, "шляпа", Sex.FEMALE, new int[]{25, 70}));
        clothesKit.add(new Clothes(Type.HEADWEAR, "кепка", Sex.UNISEX, new int[]{20, 70}));
        clothesKit.add(new Clothes(Type.ACCESSORIES, "зонт", Sex.UNISEX, new int[]{-70, 70}));
        clothesKit.add(new Clothes(Type.ACCESSORIES, "солнечные очки", Sex.UNISEX, new int[]{15, 70}));

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
