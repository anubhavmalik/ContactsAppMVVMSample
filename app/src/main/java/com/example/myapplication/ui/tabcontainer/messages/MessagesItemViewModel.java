package com.example.myapplication.ui.tabcontainer.messages;

import com.example.myapplication.data.models.Message;

public class MessagesItemViewModel {
    public final String body;
    public final int senderId;
    public final long timestamp;
    private final Message mListItem;

    public MessagesItemViewModel(Message mListItem) {
        this.body = mListItem.getText();
        this.senderId = mListItem.getSenderId();
        this.timestamp = mListItem.getTimestamp();
        this.mListItem = mListItem;
    }

}
