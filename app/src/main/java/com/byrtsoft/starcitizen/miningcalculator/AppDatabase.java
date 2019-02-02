package com.byrtsoft.starcitizen.miningcalculator;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Ore.class, Chunk.class, OreAlloc.class}, version = 2, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract OreDAO getOreDAO();
    public abstract ChunkDAO getChunkDAO();
    public abstract OreAllocDAO getAllocDAO();

    private static volatile AppDatabase INSTANCE;

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app_database")
                            .fallbackToDestructiveMigration()
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
                new PopulateOreAllocDbAsync(INSTANCE).execute();
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
            dao.insert(Ore.ORES());
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


    private static class PopulateOreAllocDbAsync extends AsyncTask<Void, Void, Void> {
        private final OreAllocDAO dao;

        PopulateOreAllocDbAsync(AppDatabase db) {
            this.dao = db.getAllocDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            dao.deleteAllOreAllocs();
            return null;
        }
    }
}
