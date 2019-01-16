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

//                new PopulateOreDbAsync(INSTANCE).execute();
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
            return null;
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
            return null;
        }

    }
}
