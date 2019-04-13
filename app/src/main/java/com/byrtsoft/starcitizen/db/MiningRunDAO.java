package com.byrtsoft.starcitizen.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MiningRunDAO {

    @Insert
    long insert(MiningRun run);

    @Update
    void update(MiningRun... runs);

    @Query("DELETE FROM run_table")
    void deleteAll();

    @Query("SELECT * FROM run_table WHERE `rowId`=:runId")
    LiveData<MiningRun> getMiningRun(int runId);

    @Query("SELECT * FROM chunk_table ORDER BY 'rowId' ASC")
    LiveData<List<MiningRun>> getAllMiningRuns();

}
