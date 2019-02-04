/*
 * *
 *  * Created by Asheesh Sharma on 1st January 2019.
 *  * Copyright (c) January 2019 . All rights reserved.
 *  * Last modified 1/1/19 2:08 PM
 *
 */

package com.example.myapplication.data;


import com.example.myapplication.data.network.models.Requests.SmsMessageRequest;
import com.example.myapplication.data.network.models.Responses.MessageResponseWrapper;

import retrofit2.Call;

public interface DataManager {
    Call<MessageResponseWrapper> sendMessageToServer(String API_KEY, String API_SECRET, SmsMessageRequest smsMessageRequest);
}
