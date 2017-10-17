package com.oddhov.newsroom.view.news_sources;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.oddhov.newsroom.NewsroomApp;
import com.oddhov.newsroom.R;
import com.oddhov.newsroom.data.models.NewsSource;
import com.oddhov.newsroom.data.models.NewsSourceFavourite;
import com.oddhov.newsroom.di.component.DaggerNewsSourcesComponent;
import com.oddhov.newsroom.di.module.NewsSourcesViewModelModule;
import com.oddhov.newsroom.utils.Constants;
import com.oddhov.newsroom.utils.ScreenTransition;
import com.oddhov.newsroom.view.BaseActivity;
import com.oddhov.newsroom.view.articles.ArticlesActivity;
import com.oddhov.newsroom.view.favourites.FavouritesActivity;
import com.oddhov.newsroom.viewmodels.news_sources.NewsSourcesListAdapter;
import com.oddhov.newsroom.viewmodels.news_sources.NewsSourcesViewModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by sammy on 26/09/17.
 */

public class NewsSourcesActivity extends BaseActivity implements NewsSourceOnClickListener {
    @Inject
    NewsSourcesListAdapter mAdapter;
    @Inject
    NewsSourcesViewModel mListNewsViewModel;

    //region Fields
    private MenuItem mFavourites;
    //endregion

    // region Lifecycle Methods
    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerNewsSourcesComponent.builder()
                .applicationComponent(((NewsroomApp) getApplication()).getApplicationComponent())
                .newsSourcesViewModelModule(new NewsSourcesViewModelModule(this))
                .build()
                .inject(this);

        setContentView(R.layout.activity_news_sources);
        ButterKnife.bind(this);

        setupView();

        mListNewsViewModel.getAllNewsSources().observe(this, apiResponse -> {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    // endregion

    //region Activity Methods
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_main, menu);

        mFavourites = menu.findItem(R.id.action_favourites);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_favourites) {
            openFavouritesActivity();
            return true;
        }
        return false;
    }
    //endregion

    //region Interface NewsSourceHandlers
    @Override
    public void onClick(NewsSource newsSource) {
        openArticlesActivity(newsSource);
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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        showLoading();
    }
    // endregion

    // region API methods
    private void handleResponse(List<NewsSourceFavourite> newsSourceList) {
        if (newsSourceList.size() > 0) {
            showContent();
            Toast.makeText(this,
                    "NewsSources found: " + newsSourceList.size(),
                    Toast.LENGTH_SHORT).show();

            mAdapter.setData(newsSourceList);
        } else {
            showError();
        }
    }

    private void handleError(Throwable error) {
        showError();
        Toast.makeText(this,
                "Oops! Some error occured.",
                Toast.LENGTH_SHORT).show();
    }
    // endregion

    //region Navigation methods
    private void openArticlesActivity(NewsSource newsSource) {
        Intent intent = new Intent(this, ArticlesActivity.class);
        intent.putExtra(Constants.NEWS_SOURCE_ID, newsSource.getId());
        startActivity(intent);
        overridePendingTransition(
                ScreenTransition.NEXT_SLIDING_SLIDING.getEnter(),
                ScreenTransition.NEXT_SLIDING_SLIDING.getExit());
    }

    private void openFavouritesActivity() {
        Intent intent = new Intent(this, FavouritesActivity.class);
        startActivity(intent);
        overridePendingTransition(
                ScreenTransition.FADE_IN_OUT.getEnter(),
                ScreenTransition.FADE_IN_OUT.getExit());
    }
    //endregion


}
