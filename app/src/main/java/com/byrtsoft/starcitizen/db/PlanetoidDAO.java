package com.byrtsoft.starcitizen.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface PlanetoidDAO {

    @Insert
    long insert(Planetoid planetoid);

    @Update
    void update(Planetoid... planetoids);

    @Query("DELETE FROM planetoid_table")
    void deleteAll();

    @Query("SELECT * FROM planetoid_table WHERE `rowId`=:planetoidId")
    LiveData<Planetoid> getPlanetoid(int planetoidId);

    @Query("SELECT * FROM planetoid_table ORDER BY 'rowId' ASC")
    LiveData<List<Planetoid>> getAllPlanetoids();

}
