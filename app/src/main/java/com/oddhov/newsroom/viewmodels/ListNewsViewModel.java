package com.oddhov.newsroom.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.oddhov.newsroom.data.NewsRepository;
import com.oddhov.newsroom.data.models.ApiResponse;

/**
 * Created by sammy on 26/09/17.
 */

public class ListNewsViewModel extends ViewModel {
    private MediatorLiveData<ApiResponse> mApiResponse;
    private NewsRepository mNewsRepository;

    public ListNewsViewModel(NewsRepository newsRepository) {
        this.mNewsRepository = newsRepository;
        this.mApiResponse = new MediatorLiveData<>();
    }

    @NonNull
    public LiveData<ApiResponse> getApiResponse() {
        return mApiResponse;
    }

    public LiveData<ApiResponse> loadNews() {
        mApiResponse.addSource(
                mNewsRepository.getArticles(),
                apiResponse -> mApiResponse.setValue(apiResponse)
        );
        return mApiResponse;
    }
}
