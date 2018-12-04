package com.byrtsoft.starcitizen.miningcalculator;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ChunkDAO {

    @Insert
    void insert(Chunk chunk);

    @Query("DELETE FROM chunk_table")
    void deleteAll();

    @Query("SELECT * FROM chunk_table ORDER BY 'rowId' ASC")
    LiveData<List<Chunk>> getAllChunks();

}
