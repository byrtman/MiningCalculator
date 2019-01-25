package com.byrtsoft.starcitizen.miningcalculator;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

@Dao
public interface OreAllocDAO {

    @Insert
    void insert(OreAlloc... allocs);

    @Update
    void update(OreAlloc... allocs);

    @Delete
    void delete(OreAlloc... allocs);

    @Query("SELECT * FROM ore_alloc_table WHERE parent_id = :chunkId")
    LiveData<List<OreAlloc>> getOreAllocs(int chunkId);

    @Query("DELETE FROM ore_alloc_table")
    void deleteAllOreAllocs();
}
