
/*
 * *
 *  * Created by Asheesh Sharma on 1st January 2019.
 *  * Copyright (c) January 2019 . All rights reserved.
 *  * Last modified 1/1/19 3:44 PM
 *
 */

package com.example.myapplication.di.module;

import android.app.Application;
import android.content.Context;

import com.example.myapplication.data.AppDataManager;
import com.example.myapplication.data.DataManager;
import com.example.myapplication.data.network.requestLayer.ApiService;
import com.example.myapplication.data.network.requestLayer.MyServiceInterceptor;
import com.example.myapplication.di.ApiInfo;
import com.example.myapplication.di.DatabaseInfo;
import com.example.myapplication.di.PreferenceInfo;
import com.example.myapplication.utils.AppConstants;
import com.example.myapplication.utils.SharedPrefsUtils;
import com.example.myapplication.utils.rx.AppSchedulerProvider;
import com.example.myapplication.utils.rx.SchedulerProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;


@Module
public class AppModule {

    @Singleton
    @Provides
    static ApiService provideApiService(OkHttpClient okHttpClient, Gson gson) {
        Retrofit.Builder retrofitBuilder;
        retrofitBuilder = new Retrofit.Builder()
                .baseUrl("https://rest.nexmo.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create());
        return retrofitBuilder.build().create(ApiService.class);
    }

    @Provides
    @Singleton
    static OkHttpClient provideOkhttp(MyServiceInterceptor interceptor, HttpLoggingInterceptor httpLoggingInterceptor, Cache cache) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .cache(cache)
                .addInterceptor(interceptor)
                .addInterceptor(httpLoggingInterceptor);
        return builder.build();
    }


    @Provides
    @Singleton
    static Cache provideCache(Context context) {
        File cacheFile = new File(context.getCacheDir(), "HttpCache");
        cacheFile.mkdirs();

        return new Cache(cacheFile, 10 * 1000 * 1000); //10 MB
    }

    @Provides
    @Singleton
    static HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.d(message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    @Provides
    @Singleton
    static MyServiceInterceptor getServiceInterceptor(Context context) {
        return new MyServiceInterceptor(context);
    }


    @Provides
    @ApiInfo
    String provideApiKey() {
//        return BuildConfig.API_KEY;
        return "";
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    //
    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }


    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    SharedPrefsUtils providePreferencesHelper() {
        return new SharedPrefsUtils();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }
}
