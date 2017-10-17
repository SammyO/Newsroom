package com.oddhov.newsroom.di.module;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sammy on 07/09/17.
 */

@Module
public class ApplicationModule {
    private Application mApplication;
    private final Context mContext;

    public ApplicationModule(Application application, Context context) {
        this.mApplication = application;
        this.mContext = context;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    Context provideContext() {
        return mContext;
    }
}
