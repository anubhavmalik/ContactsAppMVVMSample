package com.example.myapplication.ui.tabcontainer.contacts;

import android.util.Log;

import com.example.myapplication.data.models.Contact;

public class ContactsItemViewModel {
    public final String firstName;
    public final String phone;
    public final String lastName;
    private final Contact mListItem;
    private ContactsItemClickListener contactsItemClickListener;

    public ContactsItemViewModel(Contact mListItem, ContactsItemClickListener contactsItemClickListener) {
        this.firstName = mListItem.getFirstName();
        this.phone = mListItem.getPhone();
        this.lastName = mListItem.getLastName();
        this.mListItem = mListItem;
        this.contactsItemClickListener = contactsItemClickListener;
        Log.d("KKKK", getFullName());
    }

    public void onItemClick() {
        contactsItemClickListener.onItemClick(mListItem.getId());
    }

    public String getFullName() {
        return (firstName == null ? "" : firstName) + " " + (lastName == null ? "" : lastName);
    }

    public String getInitials() {
        return (firstName == null ? "" : firstName.charAt(0)) + " " + (lastName == null ? "" : lastName.charAt(0));
    }

    public interface ContactsItemClickListener {
        void onItemClick(int id);
    }
}