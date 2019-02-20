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


    @POST("sendotp.php")
    Call<MessageResponseWrapper> postSmsToApi(@Query("authkey") String authkey
            , @Query("message") String message
            , @Query("sender") String sender
            , @Query("mobile") String target
            , @Query("otp") String otp);

//    ?authkey=263815AefZZPrIn2d5c6c530e&message=Hello, your OTP is : 999909&sender=ANUBHAVOTP&mobile=918076654542&otp=999909
}
