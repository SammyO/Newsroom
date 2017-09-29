package com.oddhov.newsroom.data.models;

/**
 * Created by sammy on 26/09/17.
 */

public class ApiResponse {
    private ArticleSource mArticleSource;
    private Throwable mError;

    public ApiResponse(ArticleSource articleSource) {
        this.mArticleSource = articleSource;
        this.mError = null;
    }

    public ApiResponse(Throwable error) {
        this.mError = error;
        this.mArticleSource = null;
    }

    public ArticleSource getArticleSource() {
        return mArticleSource;
    }

    public Throwable getError() {
        return mError;
    }
}
