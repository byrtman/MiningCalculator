package com.byrtsoft.starcitizen.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MiningLocationDAO {

    @Insert
    void insert(MiningLocation... miningLocations);

    @Update
    void update(MiningLocation... miningLocations);

    @Query("DELETE FROM location_table")
    void deleteAll();

    @Query("SELECT * FROM location_table WHERE `rowId`=:locationId")
    LiveData<MiningLocation> getMiningLocation(int locationId);

    @Query("SELECT * FROM location_table ORDER BY 'rowId' ASC")
    LiveData<List<MiningLocation>> getAllMiningLocations();

}
