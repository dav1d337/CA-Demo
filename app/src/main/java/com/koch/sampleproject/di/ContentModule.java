package com.koch.sampleproject.di;

import com.koch.sampleproject.adapter.changes.TestApiController;
import com.koch.sampleproject.adapter.changes.ServerChangeListProvider;
import com.koch.sampleproject.adapter.movies.MovieApiController;
import com.koch.sampleproject.adapter.movies.ServerMovieListProvider;

import dagger.Binds;
import dagger.Module;

@Module
public interface ContentModule {

    @Binds
    ServerChangeListProvider provideServerChangeListProvider(TestApiController testApiController);

    @Binds
    ServerMovieListProvider provideServerMovieListProvider(MovieApiController movieApiController);
}
