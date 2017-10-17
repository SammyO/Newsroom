package com.oddhov.newsroom;

import android.app.Application;

import com.oddhov.newsroom.di.component.ApplicationComponent;
import com.oddhov.newsroom.di.component.DaggerApplicationComponent;
import com.oddhov.newsroom.di.module.ApplicationModule;
import com.oddhov.newsroom.di.module.NewsRepositoryModule;
import com.oddhov.newsroom.utils.UtilsModule;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by sammy on 27/09/17.
 */

public class NewsroomApp extends Application {
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            if (LeakCanary.isInAnalyzerProcess(this)) {
                // This process is dedicated to LeakCanary for heap analysis.
                return;
            }
            LeakCanary.install(this);
        }

        initializeDependencies();

    }

    private void initializeDependencies() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this, this))
                .newsRepositoryModule(new NewsRepositoryModule())
                .utilsModule(new UtilsModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.mApplicationComponent;
    }
}