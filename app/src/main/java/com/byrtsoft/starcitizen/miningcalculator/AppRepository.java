package com.byrtsoft.starcitizen.miningcalculator;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.res.XmlResourceParser;
import android.os.AsyncTask;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

public class AppRepository {
    private static String TAG = "ORE_DATA";
    private ChunkDAO chunkDAO;
    private OreDAO oreDAO;

    private LiveData<List<Chunk>> allChunks;
    private LiveData<List<Ore>> allOresList; // List of all existing Ores in the game.

    AppRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        oreDAO = db.getOreDAO();
        chunkDAO = db.getChunkDAO();
        allChunks = chunkDAO.getAllChunks();
        allOresList = oreDAO.getOres();


        deleteAllOres();
        XmlResourceParser oreXpp = application.getResources().getXml(R.xml.ore);
        try {
            oreXpp.next();
            int eventType = oreXpp.getEventType();
            Ore ore = null;
            String tag = "";
            while (eventType != XmlPullParser.END_DOCUMENT)
            {
                switch (eventType)
                {
                    case XmlPullParser.START_DOCUMENT:
                        Log.i(TAG, "Start parsing ORE data...");
                        break;
                    case XmlPullParser.START_TAG:
                        tag = oreXpp.getName();
                        if (tag != null && tag.equals("ore")) {
                            ore = new Ore();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (oreXpp.getName().equals("ore") && ore != null) {
                            insertOre(ore);
                        }
                        break;
                    case XmlPullParser.TEXT:
                        if (tag.equals("name")) {
                            ore.setName(oreXpp.getText());
                            tag = "";
                        } else if (tag.equals("price")) {
                            ore.setPrice(Double.parseDouble(oreXpp.getText()));
                            tag = "";
                        } else if (tag.equals("invdensity")) {
                            ore.setInvDensity(Double.parseDouble(oreXpp.getText()));
                            tag = "";
                        }
                        break;
                }
                eventType = oreXpp.next();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
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

    public void deleteAllOres() {
        new deleteAllOresAsyncTask(oreDAO).execute();
    }


    private static class deleteAllOresAsyncTask extends AsyncTask<Void, Void, Void> {
        private OreDAO asyncTaskDao;

        deleteAllOresAsyncTask(OreDAO dao) { asyncTaskDao = dao; }

        @Override
        protected Void doInBackground(final Void... params) {
            asyncTaskDao.deleteAllOres();
            return null;
        }
    }


}
