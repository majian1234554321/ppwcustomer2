package com.paipaiwei.personal.db.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CityHistoryModel {


    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "keyword")
    public String keyword;

    @ColumnInfo(name = "date")
    public String date;

    public CityHistoryModel(String keyword, String date) {
        this.keyword = keyword;
        this.date = date;
    }
}


