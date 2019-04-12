package com.byrtsoft.starcitizen.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "planetoid_table")
public class Planetoid {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowId")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    public Planetoid(@NonNull String name) {
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
        result.append("{ Planetoid:");
        result.append(" ").append("id: ").append(getId()).append(" ");
        result.append("|").append("name: ").append(getName()).append(" ");
        return result.toString();
    }
}
