package com.example.myapplication.utils;

import android.util.Log;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.SmsSubmissionResponseMessage;
import com.nexmo.client.sms.messages.TextMessage;

import java.io.IOException;
import java.util.Random;

public class SmsAppService {

    private static SmsAppService smsAppService;

    public static SmsAppService newInstance() {
        if (smsAppService == null) {
            smsAppService = new SmsAppService();
        }
        return smsAppService;
    }

    public void sendSms(String target){
        NexmoClient client = new NexmoClient.Builder()
                .apiKey("5f2d38e2")
                .apiSecret("KllTf6uvVARNmSCn")
                .build();

        String messageText = "Hi. Your OTP is: " + generateRandomOtp();
        TextMessage message = new TextMessage("Nexmo", target, messageText);

        SmsSubmissionResponse response = null;
        try {
            response = client.getSmsClient().submitMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NexmoClientException e) {
            e.printStackTrace();
        }

        if(response!=null){
            if(response.getMessages()!=null){
                for (SmsSubmissionResponseMessage responseMessage : response.getMessages()) {
                    Log.d("tAGGG", responseMessage.getStatus().getMessageStatus() + "message status");
                }
            }
        }

    }

    private String generateRandomOtp() {
        return String.valueOf(new Random().nextInt(999999));
    }
}
