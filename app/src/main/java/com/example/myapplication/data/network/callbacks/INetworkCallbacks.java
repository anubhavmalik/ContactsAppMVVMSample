/*
 * *
 *  * Created by Asheesh Sharma on 1st January 2019.
 *  * Copyright (c) January 2019 . All rights reserved.
 *  * Last modified 1/1/19 1:23 PM
 *
 */

package com.example.myapplication.data.network.callbacks;

public interface INetworkCallbacks<T> {
    void onSuccess(T t, String type);
    void onError(String error, String type);
    void onComplete();
}
