package com.byrtsoft.starcitizen.miningcalculator;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "ore")
public class Ore {
    private double mInvDensity;
    private double mPrice;

    @PrimaryKey
    @NonNull
    private String mName;

    public String getName() {
        return mName;
    }

    public void setName(String val) {
        mName = val;
    }

    public double getInvDensity() {
        return mInvDensity;
    }

    public void setInvDensity(double val) {
        mInvDensity = val;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double val) {
        mPrice = val;
    }
}
