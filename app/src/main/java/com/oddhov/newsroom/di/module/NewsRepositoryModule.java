package com.oddhov.newsroom.di.module;

import com.oddhov.newsroom.data.NewsRepository;
import com.oddhov.newsroom.data.NewsRepositoryImpl;
import com.oddhov.newsroom.data.remote.NewsApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sammy on 07/09/17.
 */

@Module(includes = {ApiServiceModule.class})
public class NewsRepositoryModule {

    @Singleton
    @Provides
    NewsRepository provideNewsRepository(NewsApiService apiService) {
        return new NewsRepositoryImpl(apiService);
    }

}
