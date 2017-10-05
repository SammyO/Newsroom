package com.oddhov.newsroom.viewmodels;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.oddhov.newsroom.R;
import com.oddhov.newsroom.data.models.NewsSource;
import com.oddhov.newsroom.databinding.ItemNewsSourceBinding;
import com.oddhov.newsroom.view.NewsSourcesViewHolder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by sammy on 26/09/17.
 */

public class NewsSourcesListAdapter extends RecyclerView.Adapter<NewsSourcesViewHolder> {

    private List<NewsSource> mNewsSources;

    @Inject
    public NewsSourcesListAdapter() {
        this.mNewsSources = new ArrayList<>();
    }

    public void setData(List<NewsSource> newsSources) {
        this.mNewsSources.clear();
        this.mNewsSources.addAll(newsSources);

        notifyDataSetChanged();
    }

    @Override
    public NewsSourcesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemNewsSourceBinding binding = DataBindingUtil.inflate(LayoutInflater.from(
                parent.getContext()), R.layout.item_news_source, parent, false);

        return new NewsSourcesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(NewsSourcesViewHolder holder, int position) {
        holder.bind(mNewsSources.get(position));
    }

    @Override
    public int getItemCount() {
        return mNewsSources.size();
    }
}
