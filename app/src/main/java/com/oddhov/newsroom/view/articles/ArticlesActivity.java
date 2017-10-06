package com.oddhov.newsroom.view.articles;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.oddhov.newsroom.NewsroomApp;
import com.oddhov.newsroom.R;
import com.oddhov.newsroom.data.models.Article;
import com.oddhov.newsroom.di.component.DaggerArticlesComponent;
import com.oddhov.newsroom.di.module.ArticlesViewModelModule;
import com.oddhov.newsroom.utils.Constants;
import com.oddhov.newsroom.viewmodels.articles.ArticlesListAdapter;
import com.oddhov.newsroom.viewmodels.articles.ArticlesViewModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sammy on 05/10/17.
 */

public class ArticlesActivity extends AppCompatActivity {
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
    ArticlesListAdapter mAdapter;
    @Inject
    ArticlesViewModel mNewsViewModel;

    // region Lifecycle Methods
    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerArticlesComponent.builder()
                .applicationComponent(((NewsroomApp) getApplication()).getApplicationComponent())
                .articlesViewModelModule(new ArticlesViewModelModule(this))
                .build()
                .inject(this);

        setContentView(R.layout.activity_articles);
        ButterKnife.bind(this);

        setupView();
        setupData();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    // endregion

    // region UI methods
    private void setupView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false)
        );
        mRecyclerView.hasFixedSize();
        mRecyclerView.setAdapter(mAdapter);
        showLoading();
    }

    private void setupData() {
        String newsSourceId = getIntent().getStringExtra(Constants.NEWS_SOURCE_ID);
        if (!TextUtils.isEmpty(newsSourceId)) {
            mNewsViewModel.setNewsSourceName(newsSourceId);
            mNewsViewModel.getAllArticles().observe(this, apiResponse -> {
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
    private void handleResponse(List<Article> articles) {
        if (articles.size() > 0) {
            showContent();
            Toast.makeText(this,
                    "NewsSources found: " + articles.size(),
                    Toast.LENGTH_SHORT).show();

            mAdapter.setData(articles);
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
