package com.example.myapplication.ui.tabcontainer.contacts;

import android.arch.lifecycle.ViewModelProvider;

import com.example.myapplication.data.DataManager;
import com.example.myapplication.data.models.Contact;
import com.example.myapplication.factories.ViewModelProviderFactory;
import com.example.myapplication.ui.adapters.TabsListRecyclerAdapter;
import com.example.myapplication.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class ContactsModule {

    @Provides
    ContactsViewModel contactsViewModel(DataManager dataManager,
                                        SchedulerProvider schedulerProvider) {
        return new ContactsViewModel(dataManager, schedulerProvider);
    }

    @Provides
    TabsListRecyclerAdapter tabsListRecyclerAdapter(){
        return new TabsListRecyclerAdapter(new ArrayList<Contact>());
    }

    @Provides
    ViewModelProvider.Factory provideContactsViewModel(ContactsViewModel contactsViewModel) {
        return new ViewModelProviderFactory<>(contactsViewModel);
    }
}
