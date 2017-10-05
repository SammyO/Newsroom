package com.oddhov.newsroom.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
/**
 * Created by sammy on 05/10/2017.
 */

public class NewsSourcesResponse {

    @SerializedName("status")
    @Expose
    private String mStatus;
    @SerializedName("sources")
    @Expose
    private List<NewsSource> mNewsSources = null;

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        this.mStatus = status;
    }

    public List<NewsSource> getNewsSources() {
        return mNewsSources;
    }

    public void setNewsSources(List<NewsSource> sources) {
        this.mNewsSources = sources;
    }
}
