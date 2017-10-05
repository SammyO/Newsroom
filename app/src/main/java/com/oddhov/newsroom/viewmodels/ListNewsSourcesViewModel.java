package com.oddhov.newsroom.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.oddhov.newsroom.data.NewsRepository;
import com.oddhov.newsroom.data.models.ApiResponse;

/**
 * Created by sammy on 26/09/17.
 */

public class ListNewsSourcesViewModel extends ViewModel {
    private NewsRepository mNewsRepository;
    private LiveData<ApiResponse> mApiResponse;

    public ListNewsSourcesViewModel(NewsRepository newsRepository) {
        this.mNewsRepository = newsRepository;
        this.mApiResponse = mNewsRepository.getNewsSources();
    }

    public LiveData<ApiResponse> getAllNewsSources() {
        return mApiResponse;
    }
}
