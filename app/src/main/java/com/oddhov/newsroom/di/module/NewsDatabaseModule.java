package com.oddhov.newsroom.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.oddhov.newsroom.data.local.FavouriteDao;
import com.oddhov.newsroom.data.local.NewsDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sammy on 16/10/2017.
 */

@Module
public class NewsDatabaseModule {
    @Provides
    @Singleton
    NewsDatabase provideDatabase(Application app) {
        return Room.databaseBuilder(app, NewsDatabase.class, "newsDatabase.db").build();
    }

    @Provides
    @Singleton
    FavouriteDao provideFavouiteDao(NewsDatabase database) {
        return database.favouriteDao();
    }
}
