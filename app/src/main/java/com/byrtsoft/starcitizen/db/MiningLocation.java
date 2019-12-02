package com.byrtsoft.starcitizen.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;

@Entity(tableName = "location_table")
public class MiningLocation {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowId")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "oreIds")
    private ArrayList<Integer> oreIds;

    public MiningLocation(@NonNull String name, ArrayList<Integer> oreIds) {
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

    public ArrayList<Integer> getOreIds() { return oreIds;}

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("{ MiningLocation:");
        result.append(" ").append("id: ").append(getId()).append(" ");
        result.append("| ").append("name: ").append(getName()).append(" ");
        return result.toString();
    }

    public static MiningLocation[] LOCATIONS() {
        return new MiningLocation[] {
                new MiningLocation("Aberdeen", new ArrayList<Integer>(Arrays.asList(2,3,6,7,8,9,13,15))),
                new MiningLocation("Arial", new ArrayList<Integer>(Arrays.asList(3,9,11,15))),
                new MiningLocation("Cellin", new ArrayList<Integer>(Arrays.asList(1,2,3,6,8,9,13,15))),
                new MiningLocation("CRU-L1", new ArrayList<Integer>(Arrays.asList(1,4,5,6,7,8,10,13,14,15))),
                new MiningLocation("CRU-L2", new ArrayList<Integer>(Arrays.asList(1,4,5,6,7,8,10,13,14,15))),
                new MiningLocation("CRU-L3", new ArrayList<Integer>(Arrays.asList(1,4,5,6,7,8,10,13,14,15))),
                new MiningLocation("CRU-L4", new ArrayList<Integer>(Arrays.asList(1,4,5,6,7,8,10,13,14,15))),
                new MiningLocation("CRU-L5", new ArrayList<Integer>(Arrays.asList(1,4,5,6,7,8,10,13,14,15))),
                new MiningLocation("Daymar", new ArrayList<Integer>(Arrays.asList(0,2,5,6,7,8,11,12,13,14,15))),
                new MiningLocation("Delamar asteroid field", new ArrayList<Integer>(Arrays.asList(1,4,5,6,7,8,10,13,14,15))),
                new MiningLocation("HUR-L1", new ArrayList<Integer>(Arrays.asList(1,4,5,6,7,8,10,13,14,15))),
                new MiningLocation("HUR-L2", new ArrayList<Integer>(Arrays.asList(1,4,5,6,7,8,10,13,14,15))),
                new MiningLocation("HUR-L3", new ArrayList<Integer>(Arrays.asList(1,4,5,6,7,8,10,13,14,15))),
                new MiningLocation("HUR-L4", new ArrayList<Integer>(Arrays.asList(1,4,5,6,7,8,10,13,14,15))),
                new MiningLocation("Ita", new ArrayList<Integer>(Arrays.asList(0,1,2,5,7,8,11,13,14,15))),
                new MiningLocation("Lyria", new ArrayList<Integer>(Arrays.asList(0,1,2,3,5,6,7,9,12,14,15))),
                new MiningLocation("Magda", new ArrayList<Integer>(Arrays.asList(0,1,2,7,14,15))),
                new MiningLocation("Wala", new ArrayList<Integer>(Arrays.asList(15))),
                new MiningLocation("Yela", new ArrayList<Integer>(Arrays.asList(0,1,2,6,7,10,14,15))),
                new MiningLocation("Yela Ring", new ArrayList<Integer>(Arrays.asList(15)))
        };

    }
}
