package com.oddhov.newsroom.utils.interceptors;

import com.oddhov.newsroom.data.remote.NewsApiService;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by sammy on 20/09/17.
 */

public class AuthorizationInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();

        requestBuilder.addHeader(
                NewsApiService.HEADER_X_API_KEY,
                NewsApiService.API_KEY
        );

        return chain.proceed(requestBuilder.build());
    }
}
