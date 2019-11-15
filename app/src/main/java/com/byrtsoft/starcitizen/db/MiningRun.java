package com.byrtsoft.starcitizen.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.sql.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This table keeps track of the user's mining runs and the data associated with it.
 * This table should be updated throughout the user's session when running the app.
 */

@Entity(tableName = "run_table")
public class MiningRun {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowId")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "value")
    private String value;

    @ColumnInfo(name = "chunkIds")
    private ArrayList<Integer> chunkIds;

    public ArrayList<Integer> getChunkIds() {
        return chunkIds;
    }

    public void setChunkIds(ArrayList<Integer> chunkIds) {
        this.chunkIds = chunkIds;
    }

    public void addChunk(int chunkId) {
        this.chunkIds.add(chunkId);
    }

    public MiningRun(@NonNull String name) {
        this.name = new StringBuilder().append(name).append("_").append(LocalDateTime.now()).toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("{ MiningRun:");
        result.append(" ").append("id: ").append(getId()).append(" ");
        result.append("|").append("name: ").append(getName()).append(" ");
        result.append("|").append("value: ").append(getValue()).append(" ");
        return result.toString();
    }
}
