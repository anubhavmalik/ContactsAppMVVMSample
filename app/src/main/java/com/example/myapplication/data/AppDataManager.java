/*
 * *
 *  * Created by Asheesh Sharma on 1st January 2019.
 *  * Copyright (c) January 2019 . All rights reserved.
 *  * Last modified 1/1/19 2:08 PM
 *
 */

package com.example.myapplication.data;

import android.content.Context;

import com.example.myapplication.data.network.models.Requests.SmsMessageRequest;
import com.example.myapplication.data.network.models.Responses.MessageResponseWrapper;
import com.example.myapplication.data.network.requestLayer.ApiService;
import com.example.myapplication.utils.SharedPrefsUtils;
import com.google.gson.Gson;

import javax.inject.Inject;

import retrofit2.Call;

/*
 *   This is a class which is just a
 *   wrapper over all network and
 *   db calls.
 *
 */

public class AppDataManager implements DataManager {

    private final ApiService mApiService;

    private final Context mContext;

    private final SharedPrefsUtils mPreferencesHelper;

    private final Gson mGson;

    @Inject
    public AppDataManager(Context context, SharedPrefsUtils preferencesHelper, ApiService apiHelper, Gson gson) {
        mContext = context;
        mPreferencesHelper = preferencesHelper;
        mApiService = apiHelper;
        mGson = gson;
    }

    @Override
    public Call<MessageResponseWrapper> sendMessageToServer(String AUTH_KEY, String message, String sender, String target, String otp) {
        return mApiService.postSmsToApi(AUTH_KEY, message, sender, target, otp);
    }

//    authkey=263815AefZZPrIn2d5c6c530e&message=Hello, your OTP is : 999909&sender=ANUBHAVOTP&mobile=918076654542&otp=999909

    //TODO: Implement the api methods here
}