package com.byrtsoft.starcitizen.db;

import android.arch.persistence.room.TypeConverter;

import com.byrtsoft.starcitizen.db.Ore;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

class DataTypeConverters {
    @TypeConverter
     public static Ore toOre(String value) {
        Type oreType = new TypeToken<Ore>() {}.getType();
        return new Gson().fromJson(value, oreType);
    }

    @TypeConverter
     public static String fromOre(Ore ore) {
        Gson gson = new Gson();
        return gson.toJson(ore);
    }

    @TypeConverter
    public static ArrayList<Integer> toIntArray(String value) {
        Type myType = new TypeToken<ArrayList<Integer>>() {}.getType();
        return new Gson().fromJson(value, myType);
    }

    @TypeConverter
    public static String fromIntArray(ArrayList<Integer> oreIds) {
        Gson gson = new Gson();
        return gson.toJson(oreIds);
    }

    @TypeConverter
    public static MiningLocation toMiningLocation(String value) {
        Type oreType = new TypeToken<MiningLocation>() {}.getType();
        return new Gson().fromJson(value, oreType);
    }

    @TypeConverter
    public static String fromMiningLocation(MiningLocation ore) {
        Gson gson = new Gson();
        return gson.toJson(ore);
    }
}
