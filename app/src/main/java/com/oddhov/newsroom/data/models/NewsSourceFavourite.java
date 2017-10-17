package com.oddhov.newsroom.data.models;

/**
 * Created by sammy on 04/10/2017.
 */

public class NewsSourceFavourite extends NewsSource {
    private boolean mIsFavourite;

    public boolean isFavourite() {
        return mIsFavourite;
    }

    public void setFavourite(boolean favourite) {
        mIsFavourite = favourite;
    }

    public NewsSourceFavourite(boolean mIsFavourite) {
        this.mIsFavourite = mIsFavourite;
    }
}