package com.oddhov.newsroom.view.favourites;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.oddhov.newsroom.NewsroomApp;
import com.oddhov.newsroom.R;
import com.oddhov.newsroom.data.models.NewsSourceFavourite;
import com.oddhov.newsroom.di.component.DaggerFavouritesComponent;
import com.oddhov.newsroom.di.module.FavouritesViewModelModule;
import com.oddhov.newsroom.view.BaseActivity;
import com.oddhov.newsroom.viewmodels.favourites.FavouritesListAdapter;
import com.oddhov.newsroom.viewmodels.favourites.FavouritesViewModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by sammy on 15/10/2017.
 */

public class FavouritesActivity extends BaseActivity implements FavouriteOnClickListener {
    @Inject
    FavouritesListAdapter mAdapter;
    @Inject
    FavouritesViewModel mFavouritesViewModel;

    // region Lifecycle Methods
    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerFavouritesComponent.builder()
                .applicationComponent(((NewsroomApp) getApplication()).getApplicationComponent())
                .favouritesViewModelModule(new FavouritesViewModelModule(this))
                .build()
                .inject(this);

        setContentView(R.layout.activity_favourites);
        ButterKnife.bind(this);

        setupView();
        setupData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    // endregion

    //region Interface NewsSourceHandlers
    @Override
    public void onClick(NewsSourceFavourite newsSource) {
        mFavouritesViewModel.favouriteClicked(newsSource);
    }
    //endregion

    // region UI methods
    private void setupView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false)
        );
        mRecyclerView.hasFixedSize();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnClickListener(this);
        showLoading();
    }

    private void setupData() {
        mFavouritesViewModel.getAllNewsSourceFavourites().observe(this, apiResponse -> {
            if (apiResponse == null) {
                showEmpty();
            }
            else if (apiResponse.isError()) {
                handleError(apiResponse.getError());
            } else if (apiResponse.isSuccess()) {
                handleResponse(apiResponse.getData());
            }
        });
    }
    // endregion

    // region API methods
    private void handleResponse(List<NewsSourceFavourite> newsSourceFavourites) {
        if (newsSourceFavourites.size() > 0) {
            showContent();
            Toast.makeText(this,
                    "NewsSources found: " + newsSourceFavourites.size(),
                    Toast.LENGTH_SHORT).show();

            mAdapter.setData(newsSourceFavourites);
        } else {
            showError();
        }
    }

    private void handleError(Throwable error) {
        showError();
        error.printStackTrace();
        Toast.makeText(this,
                "Oops! Some error occured.",
                Toast.LENGTH_SHORT).show();
    }
    // endregion

}
