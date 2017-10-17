package com.oddhov.newsroom.view.favourites;

import android.support.v7.widget.RecyclerView;

import com.oddhov.newsroom.data.models.NewsSourceFavourite;
import com.oddhov.newsroom.databinding.ItemFavouriteBinding;

/**
 * Created by sammy on 26/09/17.
 */

public class FavouriteViewHolder extends RecyclerView.ViewHolder {
    private ItemFavouriteBinding mViewDataBinding;
    private NewsSourceFavourite mNewsSourceFavourite;
    private FavouriteOnClickListener mOnClickListener;

    public FavouriteViewHolder(ItemFavouriteBinding viewDataBinding,
                               FavouriteOnClickListener listener) {
        super(viewDataBinding.getRoot());
        this.mViewDataBinding = viewDataBinding;
        this.mViewDataBinding.executePendingBindings();
        this.mOnClickListener = listener;
    }

    public void bind(NewsSourceFavourite newsSourceFavourite) {
        this.mViewDataBinding.setNewsSource(newsSourceFavourite);
        this.mViewDataBinding.setViewHolder(this);
        this.mNewsSourceFavourite = newsSourceFavourite;
    }

    public void onClick() {
        mOnClickListener.onClick(mNewsSourceFavourite);
    }
}
