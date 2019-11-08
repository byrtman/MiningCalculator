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

    @ColumnInfo(name = "oreIds")
    private int[] oreIds;

    public MiningLocation(@NonNull String name, int[] oreIds) {
        this.name = name;
        this.oreIds = oreIds;
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

    public int[] getOreIds() { return oreIds;}

    public void setOreIds(int[] oreIds) { this.oreIds = oreIds; }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("{ MiningLocation:");
        result.append(" ").append("id: ").append(getId()).append(" ");
        result.append("| ").append("name: ").append(getName()).append(" ");
        return result.toString();
    }

    public static MiningLocation[] LOCATIONS() {
        return new MiningLocation[] {
                new MiningLocation("Aberdeen", new int[]{2,4,6}),
                new MiningLocation("Arial", new int[]{}),
                new MiningLocation("Cellin", new int[]{}),
                new MiningLocation("CRU-L1", new int[]{}),
                new MiningLocation("CRU-L2", new int[]{}),
                new MiningLocation("CRU-L3", new int[]{}),
                new MiningLocation("CRU-L4", new int[]{}),
                new MiningLocation("CRU-L5", new int[]{}),
                new MiningLocation("Daymar", new int[]{}),
                new MiningLocation("Delamar asteroid field", new int[]{}),
                new MiningLocation("HUR-L1", new int[]{}),
                new MiningLocation("HUR-L2", new int[]{}),
                new MiningLocation("HUR-L3", new int[]{}),
                new MiningLocation("HUR-L4", new int[]{}),
                new MiningLocation("Ita", new int[]{}),
                new MiningLocation("Lyria", new int[]{}),
                new MiningLocation("Magda", new int[]{}),
                new MiningLocation("Wala", new int[]{}),
                new MiningLocation("Yela", new int[]{}),
                new MiningLocation("Yela Ring", new int[]{}),
        };

    }
}
