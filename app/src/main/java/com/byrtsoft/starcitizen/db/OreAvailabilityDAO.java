package com.byrtsoft.starcitizen.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface OreAvailabilityDAO {

    @Insert
    long insert(OreAvailability availability);

    @Update
    void update(OreAvailability... avails);

    @Query("DELETE FROM ore_availability_table")
    void deleteAll();

    @Query("SELECT * FROM ore_availability_table WHERE 'planetoid_id'=:planetoidId ORDER BY 'ore_id'")
    LiveData<List<OreAvailability>> getOres(int planetoidId);

//    @Query("SELECT * FROM ore_availability_table ORDER BY 'rowId' ASC")
//    LiveData<List<OreAvailability>> getAllOreAvailabilitys();

}
