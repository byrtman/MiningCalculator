package com.byrtsoft.starcitizen.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {
        Chunk.class,
        Ore.class,
        OreAlloc.class,
        OreAvailability.class,
        MiningRun.class,
        MiningLocation.class }, version = 4, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract ChunkDAO getChunkDAO();
    public abstract OreDAO getOreDAO();
    public abstract OreAllocDAO getAllocDAO();
    public abstract OreAvailabilityDAO getAvailabilityDAO();
    public abstract MiningRunDAO getMiningRunDAO();
    public abstract MiningLocationDAO getMiningLocationDAO();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
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

                new PopulateChunkDbAsync(INSTANCE).execute();
                new PopulateOreDbAsync(INSTANCE).execute();
                new PopulateOreAllocDbAsync(INSTANCE).execute();
                new PopulateMiningRunDbAsync(INSTANCE).execute();
                new PopulateMiningLocationsDbAsync(INSTANCE).execute();
                new PopulateOreAvailabilityDbAsync(INSTANCE).execute();
            }
        };


    // ASYNC static methods for database initialization

    private static class PopulateChunkDbAsync extends AsyncTask<Void, Void, Void> {
        private final ChunkDAO dao;

        PopulateChunkDbAsync(AppDatabase db) {
            this.dao = db.getChunkDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            dao.getAllChunks();
            return null;
        }
    }

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

    private static class PopulateOreAllocDbAsync extends AsyncTask<Void, Void, Void> {
        private final OreAllocDAO dao;

        PopulateOreAllocDbAsync(AppDatabase db) {
            this.dao = db.getAllocDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            return null;
        }
    }

    private static class PopulateOreAvailabilityDbAsync extends AsyncTask<Void, Void, Void> {
        private final OreAvailabilityDAO dao;

        PopulateOreAvailabilityDbAsync(AppDatabase db) { this.dao = db.getAvailabilityDAO(); }

        @Override
        protected Void doInBackground(final Void... params) {
            dao.insert(OreAvailability.TABLE());
            return null;
        }
    }

    private static class PopulateMiningRunDbAsync extends AsyncTask<Void, Void, Void> {
        private final MiningRunDAO dao;

        PopulateMiningRunDbAsync(AppDatabase db) {
            this.dao = db.getMiningRunDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            return null;
        }
    }

    private static class PopulateMiningLocationsDbAsync extends AsyncTask<Void, Void, Void> {
        private final MiningLocationDAO dao;

        PopulateMiningLocationsDbAsync(AppDatabase db) {
            this.dao = db.getMiningLocationDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            dao.deleteAll();
            dao.insert(MiningLocation.LOCATIONS());
            return null;
        }
    }
}
