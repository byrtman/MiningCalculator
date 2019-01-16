package com.byrtsoft.starcitizen.miningcalculator;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface OreDAO {
    @Insert
    void insert(Ore... ores);

    @Update
    void update(Ore... ores);

    @Delete
    void delete(Ore... ores);

    @Query("SELECT * FROM ore")
    LiveData<List<Ore>> getOres();

    @Query("DELETE FROM ore")
    void deleteAllOres();
}
