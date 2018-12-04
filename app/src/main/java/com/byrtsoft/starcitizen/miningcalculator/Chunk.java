package com.byrtsoft.starcitizen.miningcalculator;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "chunk_table")
public class Chunk {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowId")
    private int id;

    @ColumnInfo(name = "mass")
    private double mMass;

    @ColumnInfo(name = "value")
    private double mValue;

    public Chunk(@NonNull double mass, @NonNull double value) {
        mMass = mass;
        mValue = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMass() {
        return mMass;
    }

    public void setMass(double mass) {
        mMass = mass;
    }

    public double getValue() {
        return mValue;
    }

    public void setValue(double value) {
        mValue = value;
    }
}
