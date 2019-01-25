package com.byrtsoft.starcitizen.miningcalculator;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class Converters {
    @TypeConverter
    public static Ore fromString(String value) {
        Type oreType = new TypeToken<Ore>() {}.getType();
        return new Gson().fromJson(value, oreType);
    }

    @TypeConverter
    public static String fromOre(Ore ore) {
        Gson gson = new Gson();
        String json = gson.toJson(ore);
        return json;
    }
}
