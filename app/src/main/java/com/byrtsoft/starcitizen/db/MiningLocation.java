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
                new MiningLocation("Aberdeen", new int[]{2,3,6,7,8,9,13}),
                new MiningLocation("Arial", new int[]{3,9,11}),
                new MiningLocation("Cellin", new int[]{1,2,3,6,8,9,13}),
                new MiningLocation("CRU-L1", new int[]{1,4,5,6,7,8,10,13,14}),
                new MiningLocation("CRU-L2", new int[]{1,4,5,6,7,8,10,13,14}),
                new MiningLocation("CRU-L3", new int[]{1,4,5,6,7,8,10,13,14}),
                new MiningLocation("CRU-L4", new int[]{1,4,5,6,7,8,10,13,14}),
                new MiningLocation("CRU-L5", new int[]{1,4,5,6,7,8,10,13,14}),
                new MiningLocation("Daymar", new int[]{0,2,5,6,7,8,11,12,13,14}),
                new MiningLocation("Delamar asteroid field", new int[]{1,4,5,6,7,8,10,13,14}),
                new MiningLocation("HUR-L1", new int[]{1,4,5,6,7,8,10,13,14}),
                new MiningLocation("HUR-L2", new int[]{1,4,5,6,7,8,10,13,14}),
                new MiningLocation("HUR-L3", new int[]{1,4,5,6,7,8,10,13,14}),
                new MiningLocation("HUR-L4", new int[]{1,4,5,6,7,8,10,13,14}),
                new MiningLocation("Ita", new int[]{0,1,2,5,7,8,11,13,14}),
                new MiningLocation("Lyria", new int[]{}),
                new MiningLocation("Magda", new int[]{0,1,2,7,14}),
                new MiningLocation("Wala", new int[]{}),
                new MiningLocation("Yela", new int[]{0,1,2,6,7,10,14}),
                new MiningLocation("Yela Ring", new int[]{}),
        };

    }
}
