package com.oddhov.newsroom.viewmodels;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.oddhov.newsroom.R;
import com.oddhov.newsroom.data.models.Article;
import com.oddhov.newsroom.databinding.ItemNewsArticleBinding;
import com.oddhov.newsroom.view.NewsArticleViewHolder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by sammy on 26/09/17.
 */

public class DataAdapter extends RecyclerView.Adapter<NewsArticleViewHolder> {

    private List<Article> mArticles;

    @Inject
    public DataAdapter() {
        this.mArticles = new ArrayList<>();
    }

    public void setData(List<Article> articles) {
        this.mArticles.clear();
        this.mArticles.addAll(articles);

        notifyDataSetChanged();
    }

    @Override
    public NewsArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemNewsArticleBinding binding = DataBindingUtil.inflate(LayoutInflater.from(
                parent.getContext()), R.layout.item_news_article, parent, false);

        return new NewsArticleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(NewsArticleViewHolder holder, int position) {
        holder.bind(mArticles.get(position));
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }
}
