package com.byrtsoft.starcitizen.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.time.LocalDateTime;

@Entity(tableName = "run_table")
public class MiningRun {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowId")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "value")
    private String value;

    public MiningRun(@NonNull String name) {
        this.name = new StringBuilder().append(name).append("_").append(LocalDateTime.now()).toString();
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("{ MiningRun:");
        result.append(" ").append("id: ").append(getId()).append(" ");
        result.append("|").append("name: ").append(getName()).append(" ");
        result.append("|").append("value: ").append(getValue()).append(" ");
        return result.toString();
    }
}
