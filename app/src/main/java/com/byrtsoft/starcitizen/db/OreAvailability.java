package com.byrtsoft.starcitizen.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "ore_availability_table")
public class OreAvailability {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "location_id")
    private int miningLocationId;

    @ColumnInfo(name = "ore_id")
    private int oreId;

    public OreAvailability(int miningLocationId, int oreId) {
        this.miningLocationId = miningLocationId;
        this.oreId = oreId;
    }

    public int getMiningLocationId() {
        return miningLocationId;
    }

    public void setMiningLocationId(int miningLocationId) {
        this.miningLocationId = miningLocationId;
    }

    public int getOreId() {
        return oreId;
    }

    public void setOreId(int oreId) {
        this.oreId = oreId;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(getMiningLocationId()).append(" => ").append(getOreId());
        return result.toString();
    }

    public static OreAvailability[] TABLE() {
        return new OreAvailability[] {
                new OreAvailability(0, 0),  // Aberdeen, Agricium
                new OreAvailability(0, 2),  // Aberdeen, Tungsten
                new OreAvailability(0, 4),  // Aberdeen, Aluminum
                new OreAvailability(0, 6)  // Aberdeen, Taranite
        };

    }
}
