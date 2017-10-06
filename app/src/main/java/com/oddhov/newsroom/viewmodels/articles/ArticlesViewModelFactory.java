package com.oddhov.newsroom.viewmodels.articles;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.oddhov.newsroom.data.NewsRepository;
import com.oddhov.newsroom.viewmodels.news_sources.NewsSourcesViewModel;

import javax.inject.Inject;

/**
 * Created by sammy on 28/09/17.
 */

public class ArticlesViewModelFactory implements ViewModelProvider.Factory {

    @Inject
    NewsRepository mNewsRepository;

    @Inject
    public ArticlesViewModelFactory(NewsRepository newsRepository) {
        this.mNewsRepository = newsRepository;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ArticlesViewModel.class)) {
            return (T) new ArticlesViewModel(mNewsRepository); // TODO inject instance instead of creating a new one every time
        }
        throw new IllegalArgumentException("Wrong ViewModel class");
    }
}