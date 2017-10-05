package com.oddhov.newsroom.data.models;

import static com.oddhov.newsroom.data.models.Status.ERROR;
import static com.oddhov.newsroom.data.models.Status.LOADING;
import static com.oddhov.newsroom.data.models.Status.SUCCESS;

/**
 * Created by sammy on 26/09/17.
 */

public class ApiResponse<T> {
    private final Status mStatus;
    private final String mMessage;
    private final T mData;
    private final Throwable mError;

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(SUCCESS, data, null);
    }

    public static <T> ApiResponse<T> error(String msg, Throwable error) {
        return new ApiResponse<>(ERROR, error, msg);
    }

    public static <T> ApiResponse<T> loading(T data) {
        return new ApiResponse<>(LOADING, data, null);
    }

    private ApiResponse (Status status, T data, String message) {
        this.mStatus = status;
        this.mData = data;
        this.mMessage = message;
        this.mError = null;
    }

    private ApiResponse (Status status, Throwable error, String message) {
        this.mStatus = status;
        this.mError = error;
        this.mMessage = message;
        this.mData = null;
    }

    public Status getStatus() {
        return mStatus;
    }

    public String getMessage() {
        return mMessage;
    }

    public T getData() {
        return mData;
    }

    public Throwable getError() {
        return mError;
    }

    public boolean isError() {
        return mStatus == Status.ERROR;
    }

    public boolean isSuccess() {
        return mStatus == Status.SUCCESS;
    }

    public boolean isLoading() {
        return mStatus == Status.LOADING;
    }
}
