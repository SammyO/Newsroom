package com.oddhov.newsroom.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.oddhov.newsroom.data.models.ApiResponse;
import com.oddhov.newsroom.data.remote.NewsApiService;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sammy on 26/09/17.
 */

public class NewsRepositoryImpl implements NewsRepository {

    private NewsApiService mApiService;

    @Inject
    public NewsRepositoryImpl(NewsApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public LiveData<ApiResponse> getArticles() {
        final MutableLiveData<ApiResponse> liveData = new MutableLiveData<>();
        mApiService.getArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(articleSource -> liveData.setValue(new ApiResponse(articleSource)),
                        t -> liveData.setValue(new ApiResponse(t))
                );
        return liveData;
    }
}
