package com.oddhov.newsroom.utils;

import com.oddhov.newsroom.R;

public class ScreenTransition {
    public static final ScreenTransition NEXT_SLIDING_SLIDING = new ScreenTransition(
            R.anim.slide_in_right_full, R.anim.slide_out_left_full);
    public static final ScreenTransition BACK_SLIDING_SLIDING = new ScreenTransition(
            R.anim.slide_in_left_full, R.anim.slide_out_right_full);

    public static final ScreenTransition FADE_IN_OUT = new ScreenTransition(
            android.R.anim.fade_in, android.R.anim.fade_out);


    private int mEnter;
    private int mExit;

    private ScreenTransition(int enter, int exit) {
        this.mEnter = enter;
        this.mExit = exit;
    }

    public int getEnter() {
        return mEnter;
    }

    public int getExit() {
        return mExit;
    }
}
