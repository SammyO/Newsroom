package com.oddhov.newsroom.data;

import android.arch.lifecycle.LiveData;

import com.oddhov.newsroom.data.models.ApiResponse;
import com.oddhov.newsroom.data.models.Article;
import com.oddhov.newsroom.data.models.NewsSource;

import java.util.List;

/**
 * Created by sammy on 26/09/17.
 */

public interface NewsRepository {
    LiveData<ApiResponse<List<NewsSource>>> getNewsSources();

    LiveData<ApiResponse<List<Article>>> getArticlesForNewsSource(String newsSource);
}
