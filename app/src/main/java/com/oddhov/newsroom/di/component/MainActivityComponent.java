package com.oddhov.newsroom.di.component;

import com.oddhov.newsroom.di.annotation.ActivityScope;
import com.oddhov.newsroom.di.module.ViewModelModule;
import com.oddhov.newsroom.view.MainActivity;

import dagger.Component;

/**
 * Created by sammy on 27/09/17.
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class,
        modules = {
                ViewModelModule.class
        })
public interface MainActivityComponent {
    void inject(MainActivity activity);
}
