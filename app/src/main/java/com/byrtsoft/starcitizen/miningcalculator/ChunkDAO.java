package com.byrtsoft.starcitizen.miningcalculator;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ChunkDAO {

    @Insert
    long insert(Chunk chunk);

    @Update
    void update(Chunk... chunks);

    @Query("DELETE FROM chunk_table")
    void deleteAll();

    @Query("SELECT * FROM chunk_table WHERE `rowId`=:chunkId")
    LiveData<Chunk> getChunk(int chunkId);

    @Query("SELECT * FROM chunk_table ORDER BY 'rowId' ASC")
    LiveData<List<Chunk>> getAllChunks();

}
