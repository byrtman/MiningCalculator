package com.byrtsoft.starcitizen.miningcalculator;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "ore_alloc")
public class OreAlloc {

    @ColumnInfo(name = "ore")
    private Ore mOre;

    @ColumnInfo(name = "alloc")
    private double mAllocation;

    @ColumnInfo(name = "parent_id")
    private int mParentChunkId;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @NonNull
    private int id;

    public OreAlloc(@NonNull Ore ore, @NonNull double alloc, @NonNull int parent) {
        mOre= ore;
        mAllocation = alloc;
        mParentChunkId = parent;
    }

    public void setAllocation(double mAllocation) {
         this.mAllocation = mAllocation;
    }

    public void setOre(Ore ore) {
        this.mOre = ore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
