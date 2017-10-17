package com.oddhov.newsroom.di.module;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentActivity;

import com.oddhov.newsroom.data.NewsRepository;
import com.oddhov.newsroom.di.annotation.ActivityScope;
import com.oddhov.newsroom.viewmodels.news_sources.NewsSourcesViewModel;
import com.oddhov.newsroom.viewmodels.news_sources.NewsSourcesViewModelFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sammy on 28/09/17.
 */

@Module()
public class NewsSourcesViewModelModule {
    private FragmentActivity mActivity;

    public NewsSourcesViewModelModule(FragmentActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    NewsSourcesViewModel provideNewsViewModel(ViewModelProvider.Factory factory)  {
        return ViewModelProviders.of(mActivity, factory).get(NewsSourcesViewModel.class);
    }

    @Provides
    @ActivityScope
    ViewModelProvider.Factory provideViewModelFactory(NewsRepository newsRepository) {
        return new NewsSourcesViewModelFactory(newsRepository);
    }

}
