package com.example.myapplication.ui.details;

import android.databinding.ObservableField;

import com.example.myapplication.base.BaseViewModel;
import com.example.myapplication.data.DataManager;
import com.example.myapplication.data.models.Contact;
import com.example.myapplication.data.network.models.Requests.SmsMessageRequest;
import com.example.myapplication.data.network.models.Responses.MessageResponseWrapper;
import com.example.myapplication.utils.AppConstants;
import com.example.myapplication.utils.JsonParseHelper;
import com.example.myapplication.utils.SmsAppService;
import com.example.myapplication.utils.rx.SchedulerProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailViewModel extends BaseViewModel<DetailNavigator> {

    public DetailNavigator detailNavigator;
    public Contact contact;
    public ObservableField<String> contactName;
    public ObservableField<String> contactEmail;
    public ObservableField<String> contactPhone;
    public ObservableField<String> contactInitials;
    public ObservableField<String> body;
    public boolean isMessageSent;

    public void setContact(Contact contact) {
        this.contact = contact;
        contactName = new ObservableField<>(contact.getFullName());
        contactEmail = new ObservableField<>(contact.getEmail());
        contactInitials = new ObservableField<>(contact.getInitials());
        contactPhone = new ObservableField<>(contact.getPhone());
        body = new ObservableField<>("Hi, your otp is : " + SmsAppService.newInstance().generateRandomOtp());
    }

    public void setDetailNavigator(DetailNavigator detailNavigator) {
        this.detailNavigator = detailNavigator;
    }

    public DetailViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void composeMessage() {
//        detailNavigator.openComposeMessageScreen();
        if(!isMessageSent) {
            detailNavigator.openComposeMessageScreen();
            isMessageSent = !isMessageSent;
        }
        else {
            sendSms();
        }
    }

    public void sendSms() {
        detailNavigator.setLoading(true);
        SmsMessageRequest smsMessageRequest = new SmsMessageRequest("NEXMO", contactPhone.get(), body.get());
        getDataManager()
                .sendMessageToServer(AppConstants.API_KEY, AppConstants.API_SECRET, smsMessageRequest)
                .enqueue(new Callback<MessageResponseWrapper>() {
                    @Override
                    public void onResponse(Call<MessageResponseWrapper> call, Response<MessageResponseWrapper> response) {
                        detailNavigator.setLoading(false);
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                if (response.body().getMessages() != null) {
                                    new JsonParseHelper().addJsonTextMessageToContact(contact.getId(), body.get(), System.currentTimeMillis());
                                    detailNavigator.endActivity();
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<MessageResponseWrapper> call, Throwable t) {
                        detailNavigator.setLoading(false);
                    }
                });
    }

}
