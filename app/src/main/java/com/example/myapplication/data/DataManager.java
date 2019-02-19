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
    Call<MessageResponseWrapper> sendMessageToServer(String AUTH_KEY, String message, String sender, String target, String otp);

    //    ?authkey=263815AefZZPrIn2d5c6c530e&message=Hello, your OTP is : 999909&sender=ANUBHAVOTP&mobile=918076654542&otp=999909
}
