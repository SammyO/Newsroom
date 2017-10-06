package com.oddhov.newsroom.di.module;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentActivity;

import com.oddhov.newsroom.data.NewsRepository;
import com.oddhov.newsroom.di.annotation.ActivityScope;
import com.oddhov.newsroom.viewmodels.articles.ArticlesViewModel;
import com.oddhov.newsroom.viewmodels.articles.ArticlesViewModelFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sammy on 28/09/17.
 */

@Module()
public class ArticlesViewModelModule {
    private FragmentActivity mActivity;

    public ArticlesViewModelModule(FragmentActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    ArticlesViewModel provideListNewsViewModel(ViewModelProvider.Factory factory)  {
        return ViewModelProviders.of(mActivity, factory).get(ArticlesViewModel.class);
    }

    @Provides
    @ActivityScope
    ViewModelProvider.Factory provideListIssuesViewModelFactory(NewsRepository newsRepository) {
        return new ArticlesViewModelFactory(newsRepository);
    }

}
