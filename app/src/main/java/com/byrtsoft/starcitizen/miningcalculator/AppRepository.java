package com.byrtsoft.starcitizen.miningcalculator;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class AppRepository {
    private ChunkDAO chunkDAO;
    private OreDAO oreDAO;

    private LiveData<List<Chunk>> allChunks;

    AppRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        oreDAO = db.getOreDAO();
        chunkDAO = db.getChunkDAO();
        allChunks = chunkDAO.getAllChunks();
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

    public void insertOre (Ore ore) {
        new insertOreAsyncTask(oreDAO).execute(ore);
    }

    private static class insertOreAsyncTask extends AsyncTask<Ore, Void, Void> {
        private OreDAO asyncTaskDao;

        insertOreAsyncTask(OreDAO dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Ore... params) {
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }


}
