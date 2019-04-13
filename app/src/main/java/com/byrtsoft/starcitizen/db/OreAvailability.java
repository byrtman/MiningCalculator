package com.byrtsoft.starcitizen.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "ore_availability_table")
public class OreAvailability {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "planetoid_id")
    private int planetoidId;

    @ColumnInfo(name = "ore_id")
    private int oreId;

    public OreAvailability(int planetoidId, int oreId) {
        this.planetoidId = planetoidId;
        this.oreId = oreId;
    }

    public int getPlanetoidId() {
        return planetoidId;
    }

    public void setPlanetoidId(int planetoidId) {
        this.planetoidId = planetoidId;
    }

    public int getOreId() {
        return oreId;
    }

    public void setOreId(int oreId) {
        this.oreId = oreId;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(getPlanetoidId()).append(" => ").append(getOreId());
        return result.toString();
    }
}
