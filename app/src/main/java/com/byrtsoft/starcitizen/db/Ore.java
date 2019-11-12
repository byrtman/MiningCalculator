package com.byrtsoft.starcitizen.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "ore_table")
public class Ore {
    private double mInvDensity;
    private double mPrice;
    private String mName;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowId")
    private int id;

    public  Ore(String name, double invDensity, double price) {
        mName = name;
        mInvDensity = invDensity;
        mPrice = price;
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

    public String toString() {
        return new StringBuilder().append(getName()).append(" | $").append(getPrice()).toString();
    }

    public static Ore[] ORES() {
        return new Ore[] {
                new Ore("Agricium", 2.0, 25.60),
                new Ore("Aluminum", 2.0, 1.22),
                new Ore("Beryl", 2.0, 4.22),
                new Ore("Bexalite", 2.0, 40.51),
                new Ore("Borase", 2.0, 32.61),
                new Ore("Copper", 2.0, 5.80),
                new Ore("Corundum", 2.0, 2.55),
                new Ore("Diamond", 2.0, 6.73),
                new Ore("Gold", 2.0, 6.08),
                new Ore("Hephaestanite", 2.0, 14.67),
                new Ore("Laranite", 2.0, 28.55),
                new Ore("Quartz", 2.0, 1.44),
                new Ore("Taranite", 2.0, 32.68),
                new Ore("Titanium", 2.0, 8.27),
                new Ore("Tungsten", 2.0, 3.89),
                new Ore("Inert", 2.0, 0.02),
        };

    }
}
