package com.oddhov.newsroom.viewmodels.articles;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.oddhov.newsroom.R;
import com.oddhov.newsroom.data.models.Article;
import com.oddhov.newsroom.databinding.ItemArticleBinding;
import com.oddhov.newsroom.view.articles.ArticleViewHolder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by sammy on 26/09/17.
 */

public class ArticlesListAdapter extends RecyclerView.Adapter<ArticleViewHolder> {

    private List<Article> mArticles;

    @Inject
    public ArticlesListAdapter() {
        this.mArticles = new ArrayList<>();
    }

    public void setData(List<Article> articles) {
        this.mArticles.clear();
        this.mArticles.addAll(articles);

        notifyDataSetChanged();
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemArticleBinding binding = DataBindingUtil.inflate(LayoutInflater.from(
                parent.getContext()), R.layout.item_article, parent, false);

        return new ArticleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        holder.bind(mArticles.get(position));
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }
}
