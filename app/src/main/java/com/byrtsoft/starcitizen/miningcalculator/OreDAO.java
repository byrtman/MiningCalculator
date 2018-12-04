package com.byrtsoft.starcitizen.miningcalculator;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface OreDAO {
    @Insert
    public void insert(Ore... ores);

    @Update
    public void update(Ore... ores);

    @Delete
    public void delete(Ore... ores);

    @Query("SELECT * FROM ore")
    public List<Ore> getOres();

    @Query("DELETE FROM ore")
    public void deleteAllOres();
}
