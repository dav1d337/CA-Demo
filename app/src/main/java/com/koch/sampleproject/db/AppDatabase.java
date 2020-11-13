package com.koch.sampleproject.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MovieEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();
}