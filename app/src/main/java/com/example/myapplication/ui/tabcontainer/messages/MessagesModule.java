package com.example.myapplication.ui.tabcontainer.messages;

import android.arch.lifecycle.ViewModelProvider;

import com.example.myapplication.data.DataManager;
import com.example.myapplication.factories.ViewModelProviderFactory;
import com.example.myapplication.ui.adapters.MessagesRecyclerAdapter;
import com.example.myapplication.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class MessagesModule {
    @Provides
    MessagesViewModel messagesViewModel(DataManager dataManager,
                                        SchedulerProvider schedulerProvider) {
        return new MessagesViewModel(dataManager, schedulerProvider);
    }

    @Provides
    MessagesRecyclerAdapter provideMessageAdapter() {
        return new MessagesRecyclerAdapter(new ArrayList<>());
    }

    @Provides
    ViewModelProvider.Factory provideMessagesViewModel(MessagesViewModel messagesViewModel) {
        return new ViewModelProviderFactory<>(messagesViewModel);
    }
}
