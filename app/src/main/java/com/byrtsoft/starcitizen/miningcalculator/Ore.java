package com.byrtsoft.starcitizen.miningcalculator;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.support.annotation.NonNull;

@Entity(tableName = "ore_table")
public class Ore {
    private double mInvDensity;
    private double mPrice;

    @PrimaryKey
    @NonNull
    private String mName;

    public  Ore(String name, double invDensity, double price) {
        mName = name;
        mInvDensity = invDensity;
        mPrice = price;
    }

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

    public static Ore[] ORES() {
        return new Ore[] {
                new Ore("Agricium", 2.0, 5.1),
                new Ore("Diamond", 2.0, 5.1),
                new Ore("Tungsten", 2.0, 5.1),
                new Ore("Copper", 2.0, 5.1),
                new Ore("Aluminum", 2.0, 5.1),
                new Ore("Laranite", 2.0, 5.1),
                new Ore("Taranite", 2.0, 5.1),
                new Ore("Borase", 2.0, 5.1),
                new Ore("Titanium", 2.0, 5.1),
                new Ore("Quartz", 2.0, 5.1),
                new Ore("Beryl", 2.0, 5.1),
                new Ore("Gold", 2.0, 5.1),
                new Ore("Bexalite", 2.0, 5.1),
                new Ore("Hephaestanite", 2.0, 5.1),
                new Ore("Corundum", 2.0, 5.1),
        };

    }
}
