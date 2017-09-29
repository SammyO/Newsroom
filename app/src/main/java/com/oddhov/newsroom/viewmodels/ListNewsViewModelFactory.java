package com.oddhov.newsroom.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.oddhov.newsroom.data.NewsRepository;

import javax.inject.Inject;

/**
 * Created by sammy on 28/09/17.
 */

public class ListNewsViewModelFactory implements ViewModelProvider.Factory {

    @Inject
    NewsRepository mNewsRepository;

    @Inject
    public ListNewsViewModelFactory(NewsRepository newsRepository) {
        this.mNewsRepository = newsRepository;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ListNewsViewModel.class)) {
            return (T) new ListNewsViewModel(mNewsRepository); // TODO inject instance instead of creating a new one every time
        }
        throw new IllegalArgumentException("Wrong ViewModel class");
    }
}