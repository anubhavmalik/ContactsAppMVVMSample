package com.example.myapplication.utils;

import java.util.Random;

public class SmsAppService {

    private static SmsAppService smsAppService;



    public static SmsAppService newInstance() {
        if (smsAppService == null) {
            smsAppService = new SmsAppService();
        }
        return smsAppService;
    }

//    public void sendSms(String target){
//        NexmoClient client = new NexmoClient.Builder()
//                .apiKey("5f2d38e2")
//                .apiSecret("KllTf6uvVARNmSCn")
//                .build();
//
//    }

    public String generateRandomOtp() {
        return String.valueOf(new Random().nextInt(999999));
    }
}
