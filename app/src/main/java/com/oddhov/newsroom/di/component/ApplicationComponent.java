package com.oddhov.newsroom.di.component;
import com.oddhov.newsroom.NewsroomApp;
import com.oddhov.newsroom.data.NewsRepository;
import com.oddhov.newsroom.di.module.ApplicationModule;
import com.oddhov.newsroom.di.module.NewsRepositoryModule;
import com.oddhov.newsroom.utils.UtilsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by sammy on 07/09/17.
 */

@Singleton
@Component(modules = {ApplicationModule.class, NewsRepositoryModule.class, UtilsModule.class})
public interface ApplicationComponent {
    NewsRepository getNewsRepository();

    void inject(NewsroomApp newsroomApp);
}
