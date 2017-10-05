package com.oddhov.newsroom.view;

import android.support.v7.widget.RecyclerView;

import com.oddhov.newsroom.data.models.NewsSource;
import com.oddhov.newsroom.databinding.ItemNewsSourceBinding;

/**
 * Created by sammy on 26/09/17.
 */

public class NewsSourcesViewHolder extends RecyclerView.ViewHolder {
    private ItemNewsSourceBinding mViewDataBinding;

    public NewsSourcesViewHolder(ItemNewsSourceBinding viewDataBinding) {
        super(viewDataBinding.getRoot());
        this.mViewDataBinding = viewDataBinding;
        this.mViewDataBinding.executePendingBindings();
    }

    public void bind(NewsSource newsSource) {
        this.mViewDataBinding.setNewssource(newsSource);
    }
}
