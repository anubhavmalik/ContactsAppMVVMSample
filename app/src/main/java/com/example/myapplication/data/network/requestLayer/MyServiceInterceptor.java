/*
 * *
 *  * Created by Asheesh Sharma on 1st January 2019.
 *  * Copyright (c) January 2019 . All rights reserved.
 *  * Last modified 1/1/19 11:45 PM
 *
 */

package com.example.myapplication.data.network.requestLayer;

import android.content.Context;

import com.example.myapplication.utils.SharedPrefsUtils;

import java.io.IOException;

import javax.inject.Singleton;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/*
  Set the authorization token
  when it is available.
 */

@Singleton
public class MyServiceInterceptor implements Interceptor {
    private String sessionToken;
    private Context context;
    private SharedPrefsUtils sharedPrefsUtils = new SharedPrefsUtils();

    public MyServiceInterceptor(Context context) {
        this.context = context;
        sessionToken = sharedPrefsUtils.getStringPreference(context, SharedPrefsUtils.AUTH_TOKEN);
        if(sessionToken==null){
            sessionToken="";
        }
    }

    @Override public Response intercept(Chain chain) throws IOException {

        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();
        HttpUrl url = originalHttpUrl.newBuilder()
                .build();
        Request.Builder requestBuilder = original.newBuilder()
//                .addHeader(AppConstants.DEVICE_ID, CommonUtils.getDevId(context))
//                .addHeader(AppConstants.AUTHORIZATION, sessionToken)
//                .addHeader(AppConstants.VERSION_NAME, BuildConfig.VERSION_NAME)
//                .addHeader(AppConstants.VERSION_CODE, Integer.toString(BuildConfig.VERSION_CODE))
//                .addHeader(AppConstants.CONNECTION_QUALITY, NetworkUtils.getNetworkGeneration(MyApplication.getNonUiContext()))
                .url(url);
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}