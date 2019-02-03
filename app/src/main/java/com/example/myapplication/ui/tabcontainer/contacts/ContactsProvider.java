package com.example.myapplication.ui.tabcontainer.contacts;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ContactsProvider {

    @ContributesAndroidInjector(modules = ContactsModule.class)
    abstract ContactsFragment provideContactsFragmentFactory();
}
