package com.byrtsoft.starcitizen.db

import android.arch.persistence.room.Room
import android.content.Context
import android.util.Log

class AppDatabaseTest extends groovy.util.GroovyTestCase {

    private static String TAG = "TEST"
    private OreDAO mOreDao
    private AppDatabase mDb

    void setUp() {
        Context appContext = InstrumentationRegistry.getTargetContext()
        mDb = Room.inMemoryDatabaseBuilder(appContext, AppDatabase.class).build()
        mOreDao = mDb.getOreDAO()
        super.setUp()
    }

    void tearDown() {
        mDb.close()
    }

    @Test
     void testCRUD() {
        Log.d(TAG, "testing CRUD...")
        Ore ore = new Ore("Test", 2.0, 6.9)
        ore.setName("Testicle")
        mOreDao.insert(ore)
        List<Ore> ores = mOreDao.getOresList()
        for (Ore o : ores) {
            Log.d(TAG, o.getName())
        }
    }

    void testGetOreDAO() {
    }

    void testGetChunkDAO() {
    }

    void testGetAllocDAO() {
    }

    void testGetDatabase() {
    }
}
