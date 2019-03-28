package com.byrtsoft.starcitizen.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "chunk_table")
public class Chunk {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowId")
    private int id;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "mass")
    private double mMass;

    @ColumnInfo(name = "value")
    private double mValue;

    public Chunk(@NonNull double mass) {
        mMass = mass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
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

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("{ Chunk:");
        result.append(" ").append("id: ").append(getId()).append(" ");
        result.append("|").append("name: ").append(getName()).append(" ");
        result.append("| ").append("mass: ").append(getMass()).append(" ");
        result.append("| ").append("value: ").append(getValue()).append(" }");
        return result.toString();
    }
}
