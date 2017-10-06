package com.oddhov.newsroom.viewmodels.news_sources;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.oddhov.newsroom.data.NewsRepository;
import com.oddhov.newsroom.data.models.ApiResponse;
import com.oddhov.newsroom.data.models.NewsSource;

import java.util.List;

/**
 * Created by sammy on 26/09/17.
 */

public class NewsSourcesViewModel extends ViewModel {
    private NewsRepository mNewsRepository;
    private LiveData<ApiResponse<List<NewsSource>>> mApiResponse;

    public NewsSourcesViewModel(NewsRepository newsRepository) {
        this.mNewsRepository = newsRepository;
        this.mApiResponse = mNewsRepository.getNewsSources();
    }

    public LiveData<ApiResponse<List<NewsSource>>> getAllNewsSources() {
        return mApiResponse;
    }
}
