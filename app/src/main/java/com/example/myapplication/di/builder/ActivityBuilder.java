/*
 * *
 *  * Created by Asheesh Sharma on 1st January 2019.
 *  * Copyright (c) January 2019 . All rights reserved.
 *  * Last modified 1/1/19 2:41 PM
 *
 */

package com.example.myapplication.di.builder;


import com.example.myapplication.ui.tabcontainer.TabContainerActivity;
import com.example.myapplication.ui.tabcontainer.TabContainerModule;
import com.example.myapplication.ui.tabcontainer.contacts.ContactsProvider;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {
            TabContainerModule.class,
            ContactsProvider.class,
    })

//    TODO: Replace with fragments in the tab container activity

    abstract TabContainerActivity bindTabbedContainerActivity();

}
