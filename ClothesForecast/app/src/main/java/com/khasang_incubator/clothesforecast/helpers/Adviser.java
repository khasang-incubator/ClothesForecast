package com.khasang_incubator.clothesforecast.helpers;

import com.khasang_incubator.clothesforecast.database.Clothes;
import com.khasang_incubator.clothesforecast.database.Wardrobe;

import java.util.List;

/**
 * Created by aleksandrlihovidov on 19.04.16.
 */
public class Adviser {
    private Wardrobe wardrobe;

    public Adviser(Wardrobe wardrobe) {
        this.wardrobe = wardrobe;
    }

    public String getCollection(double effTemp) {
        String result = "\nНабор одежды:";

        List<String> kit = wardrobe.getClothesKitSuits(Clothes.Type.TOP, Clothes.Sex.MALE, (int) effTemp);
        for (String s : kit) {
            result += "\n" + s;
        }

        return result;
    }
}
