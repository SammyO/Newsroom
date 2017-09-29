package com.oddhov.newsroom.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sammy on 07/09/17.
 */

@Module
public class ApplicationModule {
    private final Context mContext;

    public ApplicationModule(Context context) {
        this.mContext = context;
    }

    @Provides
    Context provideContext() {
        return mContext;
    }
}
