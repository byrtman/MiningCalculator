package com.byrtsoft.starcitizen.miningcalculator;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Ore.class, Chunk.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract OreDAO getOreDAO();
    public abstract ChunkDAO getChunkDAO();

    private static volatile AppDatabase INSTANCE;

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
        new RoomDatabase.Callback() {
            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
                new PopulateOreDbAsync(INSTANCE).execute();
                new PopulateChunkDbAsync(INSTANCE).execute();
            }
        };

    private static class PopulateOreDbAsync extends AsyncTask<Void, Void, Void> {
        private final OreDAO dao;

        PopulateOreDbAsync(AppDatabase db) {
            this.dao = db.getOreDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            dao.deleteAllOres();
            for (OreData currentData : initData()) {
                Ore ore = new Ore();
                ore.setName(currentData.name);
                ore.setPrice(currentData.price);
                ore.setInvDensity(currentData.invDensity);
                dao.insert(ore);
            }
            return null;
        }

        private OreData[] initData() {
            return new OreData[]{
                    new OreData("Quartz", 0.04, 2.0),
                    new OreData("Bexalite", 79.09, 2.0),
                    new OreData("Hepha..", 25.52, 2.0),
                    new OreData("Agricium", 17.34, 2.0),
                    new OreData("Laranite", 56.59, 2.0),
                    new OreData("Aluminum", 1.76, 2.0),
                    new OreData("Tungsten", 7.22, 2.0),

            };
        }

        private static class OreData {
            private String name;
            private double price;
            private double invDensity;

            private OreData(String name, double price, double invDensity) {
                this.name = name;
                this.price = price;
                this.invDensity = invDensity;
            }
        }
    }

    private static class PopulateChunkDbAsync extends AsyncTask<Void, Void, Void> {
        private final ChunkDAO dao;

        PopulateChunkDbAsync(AppDatabase db) {
            this.dao = db.getChunkDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            dao.deleteAll();
            Chunk chunk;
            for (int i=0; i<20; i++) {
                chunk = new Chunk(Math.round(Math.random()*300), Math.round(Math.random()*300));
                dao.insert(chunk);
            }
            return null;
        }

    }
}
