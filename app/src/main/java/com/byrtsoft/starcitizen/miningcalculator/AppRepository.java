package com.byrtsoft.starcitizen.miningcalculator;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class AppRepository {
    private static String TAG = "ORE_DATA";
    private ChunkDAO chunkDAO;
    private OreDAO oreDAO;
    private OreAllocDAO allocDAO;

    private LiveData<List<Ore>> allOresList; // List of all existing Ores in the game.
    private LiveData<List<Chunk>> allChunks;
    private LiveData<List<OreAlloc>> allocationsByChunkId;

    AppRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        oreDAO = db.getOreDAO();
        chunkDAO = db.getChunkDAO();
        allocDAO = db.getAllocDAO();

        allChunks = chunkDAO.getAllChunks();
        allOresList = oreDAO.getOres();

    }

    LiveData<List<Chunk>> getAllChunks() {
        return allChunks;
    }

    public void insertChunk (Chunk chunk) {
        new insertChunkAsyncTask(chunkDAO).execute(chunk);
    }

    private static class insertChunkAsyncTask extends AsyncTask<Chunk, Void, Void> {
        private ChunkDAO asyncTaskDao;

        insertChunkAsyncTask(ChunkDAO dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Chunk... params) {
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }


    LiveData<List<OreAlloc>> getAllAllocs(int chunkId) {
        allocationsByChunkId = allocDAO.getOreAllocs(chunkId);
        return allocationsByChunkId;
    }

    public void insertOreAlloc (OreAlloc alloc) {
        new insertOreAsyncTask(allocDAO).execute(alloc);
    }

    private static class insertOreAsyncTask extends AsyncTask<OreAlloc, Void, Void> {
        private OreAllocDAO asyncTaskDao;

        insertOreAsyncTask(OreAllocDAO dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final OreAlloc... params) {
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void deleteAllOreAllocs() {
        new deleteAllOresAsyncTask(allocDAO).execute();
    }

    private static class deleteAllOresAsyncTask extends AsyncTask<Void, Void, Void> {
        private OreAllocDAO asyncTaskDao;

        deleteAllOresAsyncTask(OreAllocDAO dao) { asyncTaskDao = dao; }

        @Override
        protected Void doInBackground(final Void... params) {
            asyncTaskDao.deleteAllOreAllocs();
            return null;
        }
    }


}
