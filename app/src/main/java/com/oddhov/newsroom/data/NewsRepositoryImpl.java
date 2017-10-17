package com.oddhov.newsroom.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.oddhov.newsroom.data.local.FavouriteDao;
import com.oddhov.newsroom.data.local.NewsDatabase;
import com.oddhov.newsroom.data.models.ApiResponse;
import com.oddhov.newsroom.data.models.Article;
import com.oddhov.newsroom.data.models.NewsSourceFavourite;
import com.oddhov.newsroom.data.remote.NewsApiService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sammy on 26/09/17.
 */

public class NewsRepositoryImpl implements NewsRepository {

    private NewsApiService mApiService;
    private NewsDatabase mNewsDatabase;
    private FavouriteDao mFavouriteDao;

    @Inject
    public NewsRepositoryImpl(NewsApiService apiService, NewsDatabase newsDatabase, FavouriteDao favouriteDao) {
        this.mApiService = apiService;
        this.mNewsDatabase = newsDatabase;
        this.mFavouriteDao = favouriteDao;
    }

    @Override
    public LiveData<ApiResponse<List<NewsSourceFavourite>>> getNewsSources() {
        final MutableLiveData<ApiResponse<List<NewsSourceFavourite>>> liveData = new MutableLiveData<>();
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
    public LiveData<ApiResponse<List<NewsSourceFavourite>>> getNewsSourceFavourites() {
        final MutableLiveData<ApiResponse<List<NewsSourceFavourite>>> liveData = new MutableLiveData<>();
        mApiService.getNewsSources()
                .subscribeOn(Schedulers.io())
                .flatMapObservable(newsSourcesResponse -> Observable.fromIterable(newsSourcesResponse.getNewsSources()))
                .flatMap(newsSource -> {
                    if (mFavouriteDao.getFavouriteById(newsSource.getId()) != null) {
                        newsSource.setFavourite(true);
                    } else {
                        newsSource.setFavourite(false);
                    }
                    return Observable.just(newsSource);
                })
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        newSourceFavourites -> liveData.setValue(
                                ApiResponse.success(newSourceFavourites)),
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
