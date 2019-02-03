package com.example.myapplication.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.example.myapplication.di.component.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class MyApplication extends Application implements HasActivityInjector {
    static Application application;

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    public static Context getNonUiContext() {
        return application.getApplicationContext();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);

    }
}
