package com.byrtsoft.starcitizen.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "location_table")
public class MiningLocation {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowId")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    public MiningLocation(@NonNull String name) {
        this.name = name;
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

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("{ MiningLocation:");
        result.append(" ").append("id: ").append(getId()).append(" ");
        result.append("|").append("name: ").append(getName()).append(" ");
        return result.toString();
    }

    public static MiningLocation[] LOCATIONS() {
        return new MiningLocation[] {
                new MiningLocation("Aberdeen"),
                new MiningLocation("Arial"),
                new MiningLocation("Cellin"),
                new MiningLocation("CRU-L1"),
                new MiningLocation("CRU-L2"),
                new MiningLocation("CRU-L3"),
                new MiningLocation("CRU-L4"),
                new MiningLocation("CRU-L5"),
                new MiningLocation("Daymar"),
                new MiningLocation("Delamar asteroid field"),
                new MiningLocation("HUR-L1"),
                new MiningLocation("HUR-L2"),
                new MiningLocation("HUR-L3"),
                new MiningLocation("HUR-L4"),
                new MiningLocation("Ita"),
                new MiningLocation("Lyria"),
                new MiningLocation("Magda"),
                new MiningLocation("Wala"),
                new MiningLocation("Yela"),
                new MiningLocation("Yela Ring"),
        };

    }
}
