package com.oddhov.newsroom.view.news_sources;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.oddhov.newsroom.data.models.NewsSource;
import com.oddhov.newsroom.databinding.ItemNewsSourceBinding;

/**
 * Created by sammy on 26/09/17.
 */

public class NewsSourceViewHolder extends RecyclerView.ViewHolder {
    private ItemNewsSourceBinding mViewDataBinding;
    private NewsSource mNewsSource;
    private NewsSourceOnClickListener mNewsSourceOnClickListener;

    public NewsSourceViewHolder(ItemNewsSourceBinding viewDataBinding, NewsSourceOnClickListener listener) {
        super(viewDataBinding.getRoot());
        this.mViewDataBinding = viewDataBinding;
        this.mViewDataBinding.executePendingBindings();
        this.mNewsSourceOnClickListener = listener;
    }

    public void bind(NewsSource newsSource) {
        this.mViewDataBinding.setNewsSource(newsSource);
        this.mViewDataBinding.setViewHolder(this);
        this.mNewsSource = newsSource;
    }

    public void onClick() {
        mNewsSourceOnClickListener.onClick(mNewsSource);
    }
}
