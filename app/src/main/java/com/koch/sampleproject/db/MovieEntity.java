package com.koch.sampleproject.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MovieEntity {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "title")
    public String title;
}
