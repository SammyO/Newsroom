package com.oddhov.newsroom.viewmodels.news_sources;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.oddhov.newsroom.R;
import com.oddhov.newsroom.data.models.NewsSource;
import com.oddhov.newsroom.data.models.NewsSourceFavourite;
import com.oddhov.newsroom.databinding.ItemNewsSourceBinding;
import com.oddhov.newsroom.view.news_sources.NewsSourceOnClickListener;
import com.oddhov.newsroom.view.news_sources.NewsSourceViewHolder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by sammy on 26/09/17.
 */

public class NewsSourcesListAdapter extends RecyclerView.Adapter<NewsSourceViewHolder> {

    private List<NewsSource> mNewsSources;
    private NewsSourceOnClickListener mNewsSourceOnClickListener;

    @Inject
    public NewsSourcesListAdapter() {
        this.mNewsSources = new ArrayList<>();
    }

    public void setData(List<NewsSourceFavourite> newsSources) {
        this.mNewsSources.clear();
        this.mNewsSources.addAll(newsSources);

        notifyDataSetChanged();
    }

    @Override
    public NewsSourceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemNewsSourceBinding binding = DataBindingUtil.inflate(LayoutInflater.from(
                parent.getContext()), R.layout.item_news_source, parent, false);

        return new NewsSourceViewHolder(binding, mNewsSourceOnClickListener);
    }

    @Override
    public void onBindViewHolder(NewsSourceViewHolder holder, int position) {
        holder.bind(mNewsSources.get(position));
    }

    @Override
    public int getItemCount() {
        return mNewsSources.size();
    }

    public void setOnClickListener(NewsSourceOnClickListener listener) {
        this.mNewsSourceOnClickListener = listener;
    }
}
