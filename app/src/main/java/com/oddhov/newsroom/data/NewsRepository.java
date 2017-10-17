package com.oddhov.newsroom.data;

import android.arch.lifecycle.LiveData;

import com.oddhov.newsroom.data.models.ApiResponse;
import com.oddhov.newsroom.data.models.Article;
import com.oddhov.newsroom.data.models.NewsSourceFavourite;

import java.util.List;

/**
 * Created by sammy on 26/09/17.
 */

public interface NewsRepository {
    LiveData<ApiResponse<List<NewsSourceFavourite>>> getNewsSources();

    LiveData<ApiResponse<List<NewsSourceFavourite>>> getNewsSourceFavourites();

    LiveData<ApiResponse<List<Article>>> getArticlesForNewsSource(String newsSource);
}
