package com.koch.sampleproject.di;

import androidx.room.Room;

import com.koch.sampleproject.MainApplication;
import com.koch.sampleproject.db.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    @Provides
    @Singleton
    public AppDatabase provideDatabase(MainApplication application) {
        return Room.databaseBuilder(application,
                AppDatabase.class, "ca-demo-db").build();
    }
}
