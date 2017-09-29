package com.oddhov.newsroom.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.oddhov.newsroom.NewsroomApp;
import com.oddhov.newsroom.R;
import com.oddhov.newsroom.data.models.ArticleSource;
import com.oddhov.newsroom.di.component.DaggerMainActivityComponent;
import com.oddhov.newsroom.di.module.ViewModelModule;
import com.oddhov.newsroom.viewmodels.DataAdapter;
import com.oddhov.newsroom.viewmodels.ListNewsViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sammy on 26/09/17.
 */

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Inject
    DataAdapter mAdapter;
    @Inject
    ListNewsViewModel mListNewsViewModel;

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

        mListNewsViewModel.loadNews().observe(this, apiResponse -> {
            if (apiResponse == null) {
                return;
            }

            if (apiResponse.getError() != null) {
                handleError(apiResponse.getError());
            } else {
                handleResponse(apiResponse.getArticleSource());
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void setupView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false)
        );
        mRecyclerView.hasFixedSize();
        mRecyclerView.setAdapter(mAdapter);
    }

    private void handleResponse(ArticleSource articleSource) {

        Toast.makeText(this,
                "Articles found: " + articleSource.getArticles().size(),
                Toast.LENGTH_SHORT).show();

        mAdapter.setData(articleSource.getArticles());

//        if (issues != null && issues.size() > 0) {
//            mAdapter.addIssues(issues);
//        } else {
//            mAdapter.clearIssues();
//            Toast.makeText(
//                    this,
//                    "No issues found for the searched repository.",
//                    Toast.LENGTH_SHORT
//            ).show();
//        }
    }

    private void handleError(Throwable error) {
//        mAdapter.clearIssues();
        Toast.makeText(this,
                "Oops! Some error occured.",
                Toast.LENGTH_SHORT).show();
    }

}
