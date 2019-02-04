/*
 * *
 *  * Created by Asheesh Sharma on 1st January 2019.
 *  * Copyright (c) January 2019 . All rights reserved.
 *  * Last modified 1/1/19 1:23 PM
 *
 */

package com.example.myapplication.data.network.requestLayer;


import com.example.myapplication.data.network.models.Requests.SmsMessageRequest;
import com.example.myapplication.data.network.models.Responses.MessageResponseWrapper;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    // Declare all APIs here


    //    @FormUrlEncoded
    @POST("sms/json")
    Call<MessageResponseWrapper> postSmsToApi(@Query("api_key") String apiKey, @Query("api_secret") String apiSecret, @Body SmsMessageRequest smsMessageRequest);

}
