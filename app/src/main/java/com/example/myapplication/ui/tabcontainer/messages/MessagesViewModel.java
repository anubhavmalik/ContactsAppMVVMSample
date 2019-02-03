package com.example.myapplication.ui.tabcontainer.messages;

import com.example.myapplication.base.BaseViewModel;
import com.example.myapplication.data.DataManager;
import com.example.myapplication.utils.rx.SchedulerProvider;

public class MessagesViewModel extends BaseViewModel<MessagesViewNavigator> {


    public MessagesViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

}
