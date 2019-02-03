package com.example.myapplication.ui.tabcontainer;

import android.databinding.ObservableArrayList;
import android.support.v4.app.Fragment;

import com.example.myapplication.base.BaseViewModel;
import com.example.myapplication.data.DataManager;
import com.example.myapplication.ui.tabcontainer.contacts.ContactsFragment;
import com.example.myapplication.ui.tabcontainer.messages.MessagesFragment;
import com.example.myapplication.utils.rx.SchedulerProvider;

public class TabContainerViewModel extends BaseViewModel<TabContainerNavigator> {

    TabContainerNavigator tabContainerNavigator;
    public ObservableArrayList<String> titleObservableArrayList = new ObservableArrayList<>();
    public ObservableArrayList<Fragment> fragmentObservableArrayList = new ObservableArrayList<>();

    public TabContainerViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        fillFragmentList();
    }

    private void fillFragmentList() {
        fragmentObservableArrayList.clear();
        titleObservableArrayList.clear();

        fragmentObservableArrayList.add(ContactsFragment.newInstance());
        titleObservableArrayList.add("Contacts");

        fragmentObservableArrayList.add(MessagesFragment.newInstance());
        titleObservableArrayList.add("Messages");
    }

    public void setTabContainerNavigator(TabContainerNavigator tabContainerNavigator) {
        this.tabContainerNavigator = tabContainerNavigator;
    }
}
