package com.oddhov.newsroom.data.remote;

import com.oddhov.newsroom.data.models.NewsSourcesResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by sammy on 26/09/17.
 */

public interface NewsApiService {
    long DEFAULT_TIMEOUT = 10;

    String API_KEY = "1a3a9e1e75634bb7b583e140eacef73e";
    String BASE_URL = "https://newsapi.org/v1/";

    String HEADER_X_API_KEY = "X-Api-Key";

    @GET("sources?language=en")
    Single<NewsSourcesResponse> getNewsSources();
}
