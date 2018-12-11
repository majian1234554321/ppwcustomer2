package com.yjhh.ppwcustomer.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity
public class HistoricalModel {
    @PrimaryKey(autoGenerate=true)
    public int uid;

    @ColumnInfo(name = "keyword")
    public String keyword;

    @ColumnInfo(name = "date")
    public String date;

    public HistoricalModel(String keyword, String date) {
        this.keyword = keyword;
        this.date = date;
    }
}