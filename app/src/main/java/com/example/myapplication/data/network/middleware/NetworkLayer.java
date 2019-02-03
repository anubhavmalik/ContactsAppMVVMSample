/*
 * *
 *  * Created by Asheesh Sharma on 1st January 2019.
 *  * Copyright (c) January 2019 . All rights reserved.
 *  * Last modified 1/1/19 1:23 PM
 *
 */

package com.example.myapplication.data.network.middleware;

import android.support.annotation.NonNull;

import com.example.myapplication.data.network.callbacks.INetworkCallbacks;
import com.example.myapplication.data.network.models.base.ApiResponse;
import com.example.myapplication.utils.AppLogger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkLayer<T extends ApiResponse, Y> {
    private Call<T> mCall;
    private INetworkCallbacks<Y> mINetworkCallbacks;
    private String mType;

    public NetworkLayer(Call<T> call, INetworkCallbacks<Y> iNetworkCallbacks, String type) {
        this.mCall = call;
        this.mINetworkCallbacks = iNetworkCallbacks;
        this.mType = type;
    }

    public void callNetworkAPI() {
        mCall.enqueue(new Callback<T>() {
            @Override
            public void onResponse  (@NonNull Call<T> call, @NonNull Response<T> response) {
                AppLogger.d("APP----> RESPONSE", response);
                mINetworkCallbacks.onComplete();
                mINetworkCallbacks.onSuccess((Y) response.body(), mType);
            }

            @Override
            public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
                AppLogger.d("APP----> ERROR", t.getLocalizedMessage());
                mINetworkCallbacks.onComplete();
                mINetworkCallbacks.onError(t.getMessage(), mType);
            }
        });
    }
}
