package com.example.myapplication.ui.tabcontainer.messages;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MessagesProvider {
    @ContributesAndroidInjector(modules = MessagesModule.class)
    abstract MessagesFragment provideMessagesFragmentFactory();
}
