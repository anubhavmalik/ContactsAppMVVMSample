package com.example.myapplication.ui.tabcontainer;

import com.example.myapplication.data.DataManager;
import com.example.myapplication.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class TabContainerModule {
    @Provides
    TabContainerViewModel provideFeedViewModel(DataManager dataManager,
                                               SchedulerProvider schedulerProvider) {
        return new TabContainerViewModel(dataManager, schedulerProvider);
    }
}
