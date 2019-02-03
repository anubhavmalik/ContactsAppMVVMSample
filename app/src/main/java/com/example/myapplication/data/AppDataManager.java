/*
 * *
 *  * Created by Asheesh Sharma on 1st January 2019.
 *  * Copyright (c) January 2019 . All rights reserved.
 *  * Last modified 1/1/19 2:08 PM
 *
 */

package com.example.myapplication.data;

import android.content.Context;

import com.example.myapplication.data.network.requestLayer.ApiService;
import com.example.myapplication.utils.SharedPrefsUtils;

import javax.inject.Inject;

/*
 *   This is a class which is just a
 *   wrapper over all network and
 *   db calls.
 *   To make the code better, you can
 *   write your calls here and get
 *   response accordingly.
 *
 */

public class AppDataManager implements DataManager {

    private final ApiService mApiService;

    private final Context mContext;

    private final SharedPrefsUtils mPreferencesHelper;

    private String lat;

    private String lng;

    private SharedPrefsUtils sharedPrefsUtils = new SharedPrefsUtils();

    @Inject
    public AppDataManager(Context context, SharedPrefsUtils preferencesHelper, ApiService apiHelper) {
        mContext = context;
        mPreferencesHelper = preferencesHelper;
        mApiService = apiHelper;
//        mGson = gson;
    }

    //TODO: Implement the api methods here
}