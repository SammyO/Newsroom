package com.oddhov.newsroom.viewmodels.favourites;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.oddhov.newsroom.data.NewsRepository;
import com.oddhov.newsroom.data.models.ApiResponse;
import com.oddhov.newsroom.data.models.NewsSourceFavourite;

import java.util.List;

/**
 * Created by sammy on 26/09/17.
 */

public class FavouritesViewModel extends ViewModel {
    private NewsRepository mNewsRepository;
    private LiveData<ApiResponse<List<NewsSourceFavourite>>> mApiResponse;

    public FavouritesViewModel(NewsRepository newsRepository) {
        this.mNewsRepository = newsRepository;
        this.mApiResponse = mNewsRepository.getNewsSourceFavourites();

    }

    public LiveData<ApiResponse<List<NewsSourceFavourite>>> getAllNewsSourceFavourites() {
        return mApiResponse;
    }

    public void favouriteClicked(NewsSourceFavourite newsSourceFavourite) {

    }
}
