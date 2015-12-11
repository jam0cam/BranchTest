package com.zappos.android;

import android.app.Application;

import com.mparticle.MParticle;

import io.branch.referral.Branch;

/**
 * Created by jitse on 12/9/15.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Branch.getAutoInstance(this);
        MParticle.start(this);
    }
}
