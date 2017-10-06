package com.oddhov.newsroom.view.news_sources;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.oddhov.newsroom.NewsroomApp;
import com.oddhov.newsroom.R;
import com.oddhov.newsroom.data.models.NewsSource;
import com.oddhov.newsroom.di.component.DaggerNewsSourcesComponent;
import com.oddhov.newsroom.di.module.NewsSourcesViewModelModule;
import com.oddhov.newsroom.utils.Constants;
import com.oddhov.newsroom.utils.ScreenTransition;
import com.oddhov.newsroom.view.articles.ArticlesActivity;
import com.oddhov.newsroom.viewmodels.news_sources.NewsSourcesListAdapter;
import com.oddhov.newsroom.viewmodels.news_sources.NewsSourcesViewModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sammy on 26/09/17.
 */

public class NewsSourcesActivity extends AppCompatActivity implements NewsSourceOnClickListener {
    //region Static Fields
    private static final int POSITION_LIST = 0;
    private static final int POSITION_LOADING = 1;
    private static final int POSITION_EMPTY = 2;
    private static final int POSITION_ERROR = 3;
    //endregion

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.viewAnimator)
    ViewAnimator mViewAnimator;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Inject
    NewsSourcesListAdapter mAdapter;
    @Inject
    NewsSourcesViewModel mListNewsViewModel;

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

    //region Interface NewsSourceHandlers
    @Override
    public void onClick(NewsSource newsSource) {
        Intent intent = new Intent(this, ArticlesActivity.class);
        intent.putExtra(Constants.NEWS_SOURCE_ID, newsSource.getId());
        startActivity(intent);
        overridePendingTransition(
                ScreenTransition.NEXT_SLIDING_SLIDING.getEnter(),
                ScreenTransition.NEXT_SLIDING_SLIDING.getExit());

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


    void showContent() {
        mViewAnimator.setDisplayedChild(POSITION_LIST);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public void showError() {
        mViewAnimator.setDisplayedChild(POSITION_ERROR);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public void showLoading() {
        mViewAnimator.setDisplayedChild(POSITION_LOADING);
    }

    public void showEmpty() {
        mViewAnimator.setDisplayedChild(POSITION_EMPTY);
        mSwipeRefreshLayout.setRefreshing(false);
    }
    // endregion

    // region API methods
    private void handleResponse(List<NewsSource> newsSourceList) {
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


}
