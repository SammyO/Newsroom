package com.oddhov.newsroom.di.component;

import com.oddhov.newsroom.di.annotation.ActivityScope;
import com.oddhov.newsroom.di.module.NewsSourcesViewModelModule;
import com.oddhov.newsroom.view.news_sources.NewsSourcesActivity;

import dagger.Component;

/**
 * Created by sammy on 27/09/17.
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class,
        modules = {
                NewsSourcesViewModelModule.class
        })
public interface NewsSourcesComponent {
    void inject(NewsSourcesActivity activity);
}
