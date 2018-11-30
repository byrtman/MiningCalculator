package com.byrtsoft.starcitizen.miningcalculator;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Ore.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract OreDAO getOreDAO();
}
