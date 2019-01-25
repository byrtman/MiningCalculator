package com.byrtsoft.starcitizen.miningcalculator;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "ore_alloc_table")
public class OreAlloc {

    @ColumnInfo(name = "ore")
    private Ore ore;

    @ColumnInfo(name = "alloc")
    private double mAllocation;

    @ColumnInfo(name = "parent_id")
    private int mParentChunkId;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @NonNull
    private int id;

    public OreAlloc(@NonNull Ore ore, @NonNull double mAllocation, @NonNull int mParentChunkId) {
        this.ore= ore;
        this.mAllocation = mAllocation;
        this.mParentChunkId = mParentChunkId;
    }

    public int getParentChunkId() {
        return mParentChunkId;
    }

    public Ore getOre() {
        return ore;
    }

    public double getAllocation() {
        return mAllocation;
    }

    public void setParentChunkId(int mParentChunkId) {
        this.mParentChunkId = mParentChunkId;
    }

    public void setOre(Ore ore) {
        this.ore = ore;
    }

    public void setAllocation(double mAllocation) {
         this.mAllocation = mAllocation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
