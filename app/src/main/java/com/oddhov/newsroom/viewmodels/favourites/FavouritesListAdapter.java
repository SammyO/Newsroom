package com.oddhov.newsroom.viewmodels.favourites;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.oddhov.newsroom.R;
import com.oddhov.newsroom.data.models.NewsSourceFavourite;
import com.oddhov.newsroom.databinding.ItemFavouriteBinding;
import com.oddhov.newsroom.databinding.ItemNotFavouriteBinding;
import com.oddhov.newsroom.view.favourites.FavouriteOnClickListener;
import com.oddhov.newsroom.view.favourites.FavouriteViewHolder;
import com.oddhov.newsroom.view.favourites.NotFavouriteViewHolder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by sammy on 26/09/17.
 */

public class FavouritesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    // region Static fields
    private static final int ITEM_FAVOURITE = 0;
    private static final int ITEM_NOT_FAVOURITE = 1;
    // endregion

    // region Fields
    private List<NewsSourceFavourite> mFavourites;
    private FavouriteOnClickListener mOnClickListener;
    // endregion

    @Inject
    public FavouritesListAdapter() {
        this.mFavourites = new ArrayList<>();
    }

    public void setData(List<NewsSourceFavourite> newsSourcesFavourites) {
        this.mFavourites.clear();
        this.mFavourites.addAll(newsSourcesFavourites);

        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_FAVOURITE:
                ItemFavouriteBinding favouriteBinding = DataBindingUtil.inflate(LayoutInflater.from(
                        parent.getContext()), R.layout.item_favourite, parent, false);

                return new FavouriteViewHolder(favouriteBinding, mOnClickListener);
            case ITEM_NOT_FAVOURITE:
                ItemNotFavouriteBinding notFavouriteBinding = DataBindingUtil.inflate(LayoutInflater.from(
                        parent.getContext()), R.layout.item_not_favourite, parent, false);

                return new NotFavouriteViewHolder(notFavouriteBinding, mOnClickListener);
            default:
                throw new RuntimeException("ViewType unknown");
        }
    }

    @Override
    public int getItemViewType(int position) {
        NewsSourceFavourite item = mFavourites.get(position);
        if (item.isFavourite()) {
            return ITEM_FAVOURITE;
        }
        return ITEM_NOT_FAVOURITE;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FavouriteViewHolder) {
            ((FavouriteViewHolder) holder).bind(mFavourites.get(position));
        } else if (holder instanceof NotFavouriteViewHolder) {
            ((NotFavouriteViewHolder) holder).bind(mFavourites.get(position));
        } else {
            throw new RuntimeException("ViewHolder of unknown type");
        }
    }

    @Override
    public int getItemCount() {
        return mFavourites.size();
    }

    public void setOnClickListener(FavouriteOnClickListener listener) {
        this.mOnClickListener = listener;
    }
}
