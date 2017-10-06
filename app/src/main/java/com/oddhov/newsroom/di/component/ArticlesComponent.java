package com.oddhov.newsroom.di.component;

import com.oddhov.newsroom.di.annotation.ActivityScope;
import com.oddhov.newsroom.di.module.ArticlesViewModelModule;
import com.oddhov.newsroom.view.articles.ArticlesActivity;

import dagger.Component;

/**
 * Created by sammy on 27/09/17.
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class,
        modules = {
                ArticlesViewModelModule.class
        })
public interface ArticlesComponent {
    void inject(ArticlesActivity activity);
}
