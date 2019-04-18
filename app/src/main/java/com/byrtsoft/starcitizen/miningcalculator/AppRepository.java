package com.byrtsoft.starcitizen.miningcalculator;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.byrtsoft.starcitizen.db.AppDatabase;
import com.byrtsoft.starcitizen.db.Chunk;
import com.byrtsoft.starcitizen.db.ChunkDAO;
import com.byrtsoft.starcitizen.db.MiningRun;
import com.byrtsoft.starcitizen.db.MiningRunDAO;
import com.byrtsoft.starcitizen.db.Ore;
import com.byrtsoft.starcitizen.db.OreAlloc;
import com.byrtsoft.starcitizen.db.OreAllocDAO;
import com.byrtsoft.starcitizen.db.OreDAO;
import com.byrtsoft.starcitizen.db.Planetoid;
import com.byrtsoft.starcitizen.db.PlanetoidDAO;

import java.util.List;

public class AppRepository {
    private static String TAG = "REPOSITORY";
    private ChunkDAO chunkDAO;
    private OreDAO oreDAO;
    private OreAllocDAO allocDAO;
    private MiningRunDAO runDAO;
    private PlanetoidDAO planetoidDAO;

    private LiveData<List<Chunk>> allChunks;
    private LiveData<List<Ore>> allOresList; // List of all existing Ores in the game.
    private LiveData<List<OreAlloc>> allocationsByChunkId;
    private LiveData<List<MiningRun>> allRuns;
    private LiveData<List<Planetoid>> allPlanetoids;

    private LiveData<Chunk> currentChunk;
    private static long lastInsertedChunkId;

    AppRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        chunkDAO = db.getChunkDAO();
        oreDAO = db.getOreDAO();
        allocDAO = db.getAllocDAO();
        runDAO = db.getMiningRunDAO();
        planetoidDAO = db.getPlanetoidDAO();

        allChunks = chunkDAO.getAllChunks();
        allOresList = oreDAO.getOres();

    }

    // LIVEDATA

    LiveData<List<Chunk>> getAllChunks() {
        return allChunks;
    }
    LiveData<List<OreAlloc>> getAllAllocs(int chunkId) {
        allocationsByChunkId = allocDAO.getOreAllocs(chunkId);
        return allocationsByChunkId;
    }

    LiveData<Chunk> getLastInsertedChunk() {
        currentChunk = chunkDAO.getChunk((int) lastInsertedChunkId);
        return currentChunk;
    }

    LiveData<List<MiningRun>> getAllRuns() { return allRuns; }
    LiveData<List<Planetoid>> getAllPlanetoids() { return allPlanetoids; }


    // PUBLIC API //

    // Chunk
    public void insertChunk (Chunk chunk) { new insertChunkAsyncTask(chunkDAO).execute(chunk); }
    public void updateChunk (Chunk chunk) { new updateChunkAsyncTask(chunkDAO).execute(chunk); }
    public void deleteAllChunks () { new deleteAllChunkAsyncTask(chunkDAO).execute(); }

    // OreAlloc
    public void insertOreAlloc (OreAlloc alloc) {
        new insertOreAsyncTask(allocDAO).execute(alloc);
    }
    public void deleteAllOreAllocs() {
        new deleteAllOresAsyncTask(allocDAO).execute();
    }



    // IMPLEMENTATIONS //

    
    // Chunk 

    private static class insertChunkAsyncTask extends AsyncTask<Chunk, Void, Void> {
        private ChunkDAO asyncTaskDao;

        insertChunkAsyncTask(ChunkDAO dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Chunk... params) {
            lastInsertedChunkId = asyncTaskDao.insert(params[0]);
            return null;
        }

    }

    private static class updateChunkAsyncTask extends AsyncTask<Chunk, Void, Void> {
        private ChunkDAO asyncTaskDao;

        updateChunkAsyncTask(ChunkDAO dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Chunk... params) {
            asyncTaskDao.update(params[0]);
            return null;
        }
    }
    
    private static class deleteAllChunkAsyncTask extends AsyncTask<Void, Void, Void> {
        private ChunkDAO asyncTaskDao;

        deleteAllChunkAsyncTask(ChunkDAO dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            asyncTaskDao.deleteAll();
            return null;
        }
    }
    
    // OreAlloc

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
