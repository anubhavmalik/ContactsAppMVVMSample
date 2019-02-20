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
    private String otp = "";
    public boolean isMessageSent;

    public void setContact(Contact contact) {
        this.contact = contact;
        otp = SmsAppService.newInstance().generateRandomOtp();
        contactName = new ObservableField<>(contact.getFullName());
        contactEmail = new ObservableField<>(contact.getEmail());
        contactInitials = new ObservableField<>(contact.getInitials());
        contactPhone = new ObservableField<>(contact.getPhone());
        body = new ObservableField<>("Hello, your 6 digit otp is : " + otp);
    }

    public void setDetailNavigator(DetailNavigator detailNavigator) {
        this.detailNavigator = detailNavigator;
    }

    public DetailViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void composeMessage() {
//        detailNavigator.openComposeMessageScreen();
        if (!isMessageSent) {
            detailNavigator.openComposeMessageScreen();
            isMessageSent = !isMessageSent;
        } else {
            sendSms();
        }
    }

    public void sendSms() {
        detailNavigator.setLoading(true);
        getDataManager()
                .sendMessageToServer(AppConstants.API_KEY, body.get(), AppConstants.API_OTP_SENDER, contactPhone.get(), otp)
                .enqueue(new Callback<MessageResponseWrapper>() {
                    @Override
                    public void onResponse(Call<MessageResponseWrapper> call, Response<MessageResponseWrapper> response) {
                        detailNavigator.setLoading(false);
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                if (response.body().getMessageStatus() != null) {
                                    if (response.body().getMessageStatus().equalsIgnoreCase(AppConstants.SMS_STATUS_SUCCESS)) {
                                        JsonParseHelper.getInstance().addJsonTextMessageToContact(contact, body.get(), System.currentTimeMillis());
                                        detailNavigator.endActivity();
                                    } else {
                                        detailNavigator.handleError(false);
                                    }
                                } else {
                                    detailNavigator.handleError(false);
                                }
                            } else {
                                detailNavigator.handleError(false);
                            }
                        } else {
                            detailNavigator.handleError(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<MessageResponseWrapper> call, Throwable t) {
                        detailNavigator.setLoading(false);
                        detailNavigator.handleError(false);
                    }
                });
    }

}
