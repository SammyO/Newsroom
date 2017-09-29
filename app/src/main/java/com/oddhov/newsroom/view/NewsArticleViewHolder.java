package com.oddhov.newsroom.view;

import android.support.v7.widget.RecyclerView;

import com.oddhov.newsroom.data.models.Article;
import com.oddhov.newsroom.databinding.ItemNewsArticleBinding;

/**
 * Created by sammy on 26/09/17.
 */

public class NewsArticleViewHolder extends RecyclerView.ViewHolder {
    private ItemNewsArticleBinding mViewDataBinding;

    public NewsArticleViewHolder(ItemNewsArticleBinding viewDataBinding) {
        super(viewDataBinding.getRoot());
        this.mViewDataBinding = viewDataBinding;
        this.mViewDataBinding.executePendingBindings();
    }

    public void bind(Article article) {
        this.mViewDataBinding.setArticle(article);
    }
}
