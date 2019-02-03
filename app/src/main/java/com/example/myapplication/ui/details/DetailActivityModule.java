package com.example.myapplication.ui.details;

import com.example.myapplication.data.DataManager;
import com.example.myapplication.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class DetailActivityModule {

    @Provides
    DetailViewModel provideDetailViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new DetailViewModel(dataManager, schedulerProvider);
    }

}
