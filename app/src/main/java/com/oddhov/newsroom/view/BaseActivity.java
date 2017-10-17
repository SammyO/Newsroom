package com.oddhov.newsroom.view;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ViewAnimator;

import com.oddhov.newsroom.R;

import butterknife.BindView;

/**
 * Created by sammy on 15/10/2017.
 */

public class BaseActivity extends AppCompatActivity {
    //region Static Fields
    protected static final int POSITION_LIST = 0;
    protected static final int POSITION_LOADING = 1;
    protected static final int POSITION_EMPTY = 2;
    protected static final int POSITION_ERROR = 3;
    //endregion

    @BindView(R.id.recyclerView)
    protected RecyclerView mRecyclerView;
    @BindView(R.id.viewAnimator)
    protected ViewAnimator mViewAnimator;
    @BindView(R.id.swipeRefreshLayout)
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    protected void showContent() {
        mViewAnimator.setDisplayedChild(POSITION_LIST);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    protected void showError() {
        mViewAnimator.setDisplayedChild(POSITION_ERROR);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    protected void showLoading() {
        mViewAnimator.setDisplayedChild(POSITION_LOADING);
    }

    protected void showEmpty() {
        mViewAnimator.setDisplayedChild(POSITION_EMPTY);
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
