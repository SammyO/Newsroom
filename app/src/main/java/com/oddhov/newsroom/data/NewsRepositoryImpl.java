package com.oddhov.newsroom.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.oddhov.newsroom.data.models.ApiResponse;
import com.oddhov.newsroom.data.models.Article;
import com.oddhov.newsroom.data.models.NewsSource;
import com.oddhov.newsroom.data.remote.NewsApiService;

import java.util.List;

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
    public LiveData<ApiResponse<List<NewsSource>>> getNewsSources() {
        final MutableLiveData<ApiResponse<List<NewsSource>>> liveData = new MutableLiveData<>();
        mApiService.getNewsSources()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        newsSourcesResponse -> liveData.setValue(
                                ApiResponse.success(newsSourcesResponse.getNewsSources())),
                        t -> liveData.setValue(ApiResponse.error(t.getMessage(), t))
                );
        return liveData;
    }

    @Override
    public LiveData<ApiResponse<List<Article>>> getArticlesForNewsSource(String newsSource) {
        final MutableLiveData<ApiResponse<List<Article>>> liveData = new MutableLiveData<>();
        mApiService.getArticlesForSource(newsSource)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        articlesResponse -> liveData.setValue(
                                ApiResponse.success(articlesResponse.getArticles())),
                        t -> liveData.setValue(ApiResponse.error(t.getMessage(), t))
                );
        return liveData;
    }
}
