package com.oddhov.newsroom.di.component;

import com.oddhov.newsroom.di.annotation.ActivityScope;
import com.oddhov.newsroom.di.module.FavouritesViewModelModule;
import com.oddhov.newsroom.view.favourites.FavouritesActivity;

import dagger.Component;

/**
 * Created by sammy on 27/09/17.
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class,
        modules = {
                FavouritesViewModelModule.class
        })
public interface FavouritesComponent {
    void inject(FavouritesActivity activity);
}
