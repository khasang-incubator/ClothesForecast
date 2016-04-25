package com.khasang_incubator.clothesforecast.database;

import java.util.Arrays;

/**
 * Created by aleksandrlihovidov on 25.04.16.
 */
public class Clothes {
    public enum Type {
        TOP,
        LOWER,
        OVERDRESS,
        SHOES,
        HEADWEAR,
        ACCESSORIES;
    }

    public enum Sex{
        MALE,
        FEMALE,
        UNISEX;
    }

    private Type type;
    private String name;
    private Sex sex;
    private int[] temperatureRange;

    public Clothes(Type type, String name, Sex sex, int[] temperatureRange) {
        this.type = type;
        this.name = name;
        this.sex = sex;
        this.temperatureRange = temperatureRange;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Sex getSex() {
        return sex;
    }

    public int[] getTemperatureRange() {
        return temperatureRange;
    }

    @Override
    public String toString() {
        return "Clothes{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", temperatureRange=" + Arrays.toString(temperatureRange) +
                '}';
    }
}
