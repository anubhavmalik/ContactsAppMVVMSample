package com.example.myapplication.ui.tabcontainer.messages;

import android.databinding.ObservableArrayList;

import com.example.myapplication.base.BaseViewModel;
import com.example.myapplication.data.DataManager;
import com.example.myapplication.data.models.Message;
import com.example.myapplication.utils.JsonParseHelper;
import com.example.myapplication.utils.rx.SchedulerProvider;

public class MessagesViewModel extends BaseViewModel<MessagesViewNavigator> {

    public ObservableArrayList<Message> messagesObservableArrayList = new ObservableArrayList<>();
    public MessagesViewNavigator messagesViewNavigator;

    public void setMessagesViewNavigator(MessagesViewNavigator messagesViewNavigator) {
        this.messagesViewNavigator = messagesViewNavigator;
    }

    public MessagesViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void fetchMessagesList() {
        messagesObservableArrayList.clear();
        messagesObservableArrayList.addAll(JsonParseHelper.getInstance().getMessagesArrayList());
        messagesViewNavigator.notifyListFetched();
    }
}
