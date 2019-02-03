package com.example.myapplication.ui.tabcontainer.contacts;

import android.databinding.ObservableArrayList;

import com.example.myapplication.base.BaseViewModel;
import com.example.myapplication.data.DataManager;
import com.example.myapplication.data.models.Contact;
import com.example.myapplication.utils.JsonParseHelper;
import com.example.myapplication.utils.rx.SchedulerProvider;

public class ContactsViewModel extends BaseViewModel<ContactsViewNavigator> {

    public ObservableArrayList<Contact> contactsObservableArrayList = new ObservableArrayList<>();
    private ContactsViewNavigator navigator;

    @Override
    public void setNavigator(ContactsViewNavigator navigator) {
        this.navigator = navigator;
    }

    public ContactsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void fetchContactsList() {
        contactsObservableArrayList.addAll(JsonParseHelper.getInstance().getContactsListFromJson());
        navigator.notifyListFetched();
    }

}
