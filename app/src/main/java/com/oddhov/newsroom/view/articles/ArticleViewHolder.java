package com.oddhov.newsroom.view.articles;

import android.support.v7.widget.RecyclerView;

import com.oddhov.newsroom.data.models.Article;
import com.oddhov.newsroom.databinding.ItemArticleBinding;

/**
 * Created by sammy on 26/09/17.
 */

public class ArticleViewHolder extends RecyclerView.ViewHolder {
    private ItemArticleBinding mViewDataBinding;

    public ArticleViewHolder(ItemArticleBinding viewDataBinding) {
        super(viewDataBinding.getRoot());
        this.mViewDataBinding = viewDataBinding;
        this.mViewDataBinding.executePendingBindings();
    }

    public void bind(Article article) {
        this.mViewDataBinding.setArticle(article);
    }
}
