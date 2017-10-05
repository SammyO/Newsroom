package com.oddhov.newsroom.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.oddhov.newsroom.data.NewsRepository;

import javax.inject.Inject;

/**
 * Created by sammy on 28/09/17.
 */

public class ListNewsSourcesViewModelFactory implements ViewModelProvider.Factory {

    @Inject
    NewsRepository mNewsRepository;

    @Inject
    public ListNewsSourcesViewModelFactory(NewsRepository newsRepository) {
        this.mNewsRepository = newsRepository;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ListNewsSourcesViewModel.class)) {
            return (T) new ListNewsSourcesViewModel(mNewsRepository); // TODO inject instance instead of creating a new one every time
        }
        throw new IllegalArgumentException("Wrong ViewModel class");
    }
}