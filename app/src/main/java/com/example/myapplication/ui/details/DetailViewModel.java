package com.example.myapplication.ui.details;

import android.databinding.ObservableField;

import com.example.myapplication.base.BaseViewModel;
import com.example.myapplication.data.DataManager;
import com.example.myapplication.data.models.Contact;
import com.example.myapplication.utils.rx.SchedulerProvider;

public class DetailViewModel extends BaseViewModel<DetailNavigator> {

    public DetailNavigator detailNavigator;
    public Contact contact;
    public ObservableField<String> contactName;
    public ObservableField<String> contactEmail;
    public ObservableField<String> contactPhone;
    public ObservableField<String> contactInitials;


    public void setContact(Contact contact) {
        this.contact = contact;
        contactName = new ObservableField<>(contact.getFullName());
        contactEmail = new ObservableField<>(contact.getEmail());
        contactInitials = new ObservableField<>(contact.getInitials());
        contactPhone = new ObservableField<>(contact.getPhone());
    }

    public void setDetailNavigator(DetailNavigator detailNavigator) {
        this.detailNavigator = detailNavigator;
    }

    public DetailViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void composeMessage(){
        detailNavigator.openComposeMessageScreen();
    }

}
