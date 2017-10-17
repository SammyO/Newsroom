package com.oddhov.newsroom.di.module;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentActivity;

import com.oddhov.newsroom.data.NewsRepository;
import com.oddhov.newsroom.di.annotation.ActivityScope;
import com.oddhov.newsroom.viewmodels.favourites.FavouritesViewModel;
import com.oddhov.newsroom.viewmodels.favourites.FavouritesViewModelFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sammy on 28/09/17.
 */

@Module()
public class FavouritesViewModelModule {
    private FragmentActivity mActivity;

    public FavouritesViewModelModule(FragmentActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    FavouritesViewModel provideNewsViewModel(ViewModelProvider.Factory factory)  {
        return ViewModelProviders.of(mActivity, factory).get(FavouritesViewModel.class);
    }

    @Provides
    @ActivityScope
    ViewModelProvider.Factory provideViewModelFactory(NewsRepository newsRepository) {
        return new FavouritesViewModelFactory(newsRepository);
    }

}
