package com.oddhov.newsroom.di.module;

import com.oddhov.newsroom.data.NewsRepository;
import com.oddhov.newsroom.data.NewsRepositoryImpl;
import com.oddhov.newsroom.data.local.FavouriteDao;
import com.oddhov.newsroom.data.local.NewsDatabase;
import com.oddhov.newsroom.data.remote.NewsApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sammy on 07/09/17.
 */

@Module(includes = {ApiServiceModule.class, NewsDatabaseModule.class})
public class NewsRepositoryModule {

    @Singleton
    @Provides
    NewsRepository provideNewsRepository(NewsApiService apiService, NewsDatabase newsDatabase,
                                         FavouriteDao favouriteDao) {
        return new NewsRepositoryImpl(apiService, newsDatabase, favouriteDao);
    }

}
