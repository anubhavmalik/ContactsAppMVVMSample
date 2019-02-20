package com.example.myapplication.ui.tabcontainer.messages;

import com.example.myapplication.data.models.Contact;
import com.example.myapplication.data.models.Message;

public class MessagesItemViewModel {
    public final String body;
    public final Contact contact;
    public final Long timestamp;
    private final Message mListItem;

    public MessagesItemViewModel(Message mListItem) {
        this.body = mListItem.getText();
        this.contact = mListItem.getContact();
        this.timestamp = mListItem.getTimestamp();
        this.mListItem = mListItem;
    }

}
