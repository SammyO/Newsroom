package com.oddhov.newsroom.data;

import android.arch.lifecycle.LiveData;

import com.oddhov.newsroom.data.models.ApiResponse;

/**
 * Created by sammy on 26/09/17.
 */

public interface NewsRepository {
    LiveData<ApiResponse> getNewsSources();
}
