package com.example.myapplication.ui.tabcontainer.messages;

import android.arch.lifecycle.ViewModelProvider;

import com.example.myapplication.data.DataManager;
import com.example.myapplication.factories.ViewModelProviderFactory;
import com.example.myapplication.utils.rx.SchedulerProvider;

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
    ViewModelProvider.Factory provideMessagesViewModel(MessagesViewModel messagesViewModel) {
        return new ViewModelProviderFactory<>(messagesViewModel);
    }
}
