package com.byrtsoft.starcitizen.db;

import android.arch.persistence.room.TypeConverter;

import com.byrtsoft.starcitizen.db.Ore;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

class Converters {
    @TypeConverter
     public static Ore fromString(String value) {
        Type oreType = new TypeToken<Ore>() {}.getType();
        return new Gson().fromJson(value, oreType);
    }

    @TypeConverter
     public static String fromOre(Ore ore) {
        Gson gson = new Gson();
        return gson.toJson(ore);
    }
}
