package com.byrtsoft.starcitizen.miningcalculator;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class DatabaseTest {

    private static String TAG = "TEST";
    private OreDAO mOreDao;
    private AppDatabase mDb;


    @Before
    public void doBefore() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(appContext, AppDatabase.class).build();
        mOreDao = mDb.getOreDAO();
    }

    @After
    public void doAfter() {
        mDb.close();
    }

    @Test
    public void testCRUD() {
        Log.d(TAG, "testing CRUD...");
        Ore ore = new Ore("Test", 2.0, 6.9);
        ore.setName("Testicle");
        mOreDao.insert(ore);
        List<Ore> ores = mOreDao.getOresList();
        for (Ore o : ores) {
            Log.d(TAG, o.getName());
        }
    }
}
