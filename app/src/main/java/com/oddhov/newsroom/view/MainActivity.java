package com.oddhov.newsroom.view;

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
import com.oddhov.newsroom.di.component.DaggerMainActivityComponent;
import com.oddhov.newsroom.di.module.ViewModelModule;
import com.oddhov.newsroom.viewmodels.ListNewsSourcesViewModel;
import com.oddhov.newsroom.viewmodels.NewsSourcesListAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sammy on 26/09/17.
 */

public class MainActivity extends AppCompatActivity {
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
    ListNewsSourcesViewModel mListNewsViewModel;

    // region Lifecycle Methods
    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerMainActivityComponent.builder()
                .applicationComponent(((NewsroomApp) getApplication()).getApplicationComponent())
                .viewModelModule(new ViewModelModule(this))
                .build()
                .inject(this);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupView();

        mListNewsViewModel.getAllNewsSources().observe(this, apiResponse -> {
            if (apiResponse == null || apiResponse.isError()) {
                return;
            }

            // Not sure about this approach. Generics in ApiResponse are nice, but I'd like
            // a way of ensuring they're of the right type.
            if (apiResponse.getData() instanceof List<?> &&
                    ((List<?>) apiResponse.getData()).get(0) instanceof NewsSource) {
                handleResponse((List) apiResponse.getData());
            } else {
                handleError(new Throwable("Incorrect casting"));
            }
        });
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
        showContent();
        Toast.makeText(this,
                "NewsSources found: " + newsSourceList.size(),
                Toast.LENGTH_SHORT).show();

        mAdapter.setData(newsSourceList);
    }

    private void handleError(Throwable error) {
        showError();
        Toast.makeText(this,
                "Oops! Some error occured.",
                Toast.LENGTH_SHORT).show();
    }
    // endregion


}
