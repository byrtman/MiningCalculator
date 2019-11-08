package com.byrtsoft.starcitizen.db;

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

    @Query("SELECT * FROM ore_table WHERE mName=:name")
    Ore getOreByName(String name);

    @Query("SELECT * FROM ore_table WHERE rowId=:id")
    Ore getOreById(int id);

    @Query("SELECT * FROM ore_table")
    LiveData<List<Ore>> getOres();

    @Query("SELECT * FROM ore_table")
    List<Ore> getOresList();

    @Query("DELETE FROM ore_table")
    void deleteAllOres();
}
