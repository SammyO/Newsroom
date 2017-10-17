package com.oddhov.newsroom.data.remote;

import com.oddhov.newsroom.data.models.ArticlesResponse;
import com.oddhov.newsroom.data.models.NewsSourcesResponse;

import io.reactivex.Maybe;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sammy on 26/09/17.
 */

public interface NewsApiService {
    long DEFAULT_TIMEOUT = 10;

    String API_KEY = "1a3a9e1e75634bb7b583e140eacef73e";
    String BASE_URL = "https://newsapi.org/v1/";

    String HEADER_X_API_KEY = "X-Api-Key";
    String SOURCE = "source";

    @GET("sources?language=en")
    Maybe<NewsSourcesResponse> getNewsSources();

    @GET("articles?")
    Maybe<ArticlesResponse> getArticlesForSource(
        @Query(SOURCE) String source
    );
}
