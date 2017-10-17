package com.oddhov.newsroom.viewmodels.favourites;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.oddhov.newsroom.data.NewsRepository;

import javax.inject.Inject;

/**
 * Created by sammy on 28/09/17.
 */

public class FavouritesViewModelFactory implements ViewModelProvider.Factory {

    @Inject
    NewsRepository mNewsRepository;

    @Inject
    public FavouritesViewModelFactory(NewsRepository newsRepository) {
        this.mNewsRepository = newsRepository;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(FavouritesViewModel.class)) {
            return (T) new FavouritesViewModel(mNewsRepository); // TODO inject instance instead of creating a new one every time
        }
        throw new IllegalArgumentException("Wrong ViewModel class");
    }
}