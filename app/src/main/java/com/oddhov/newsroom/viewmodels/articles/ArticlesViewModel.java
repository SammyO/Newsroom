package com.oddhov.newsroom.viewmodels.articles;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.oddhov.newsroom.data.NewsRepository;
import com.oddhov.newsroom.data.models.ApiResponse;
import com.oddhov.newsroom.data.models.Article;
import com.oddhov.newsroom.utils.AbsentLiveData;

import java.util.List;
import java.util.Objects;

/**
 * Created by sammy on 26/09/17.
 */

public class ArticlesViewModel extends ViewModel {
    private NewsRepository mNewsRepository;
    private LiveData<ApiResponse<List<Article>>> mApiResponse;

    private MutableLiveData<String> mNewsSourceId = new MutableLiveData<>();

    public ArticlesViewModel(NewsRepository newsRepository) {
        this.mNewsRepository = newsRepository;
        this.mApiResponse = Transformations.switchMap(mNewsSourceId, newsSourceId -> {
            if (newsSourceId == null || newsSourceId.trim().length() == 0) {
                return AbsentLiveData.create();
            } else {
                return mNewsRepository.getArticlesForNewsSource(newsSourceId);
            }
        });
    }

    public LiveData<ApiResponse<List<Article>>> getAllArticles() {
        return mApiResponse;
    }

    public void setNewsSourceName(String newsSourceName) {
        if (Objects.equals(this.mNewsSourceId.getValue(), newsSourceName)) {
            return;
        }
        this.mNewsSourceId.setValue(newsSourceName);
    }
}
