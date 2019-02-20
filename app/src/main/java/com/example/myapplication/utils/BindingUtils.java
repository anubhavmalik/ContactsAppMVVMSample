/*
 * *
 *  * Created by Asheesh Sharma on 1st January 2019.
 *  * Copyright (c) January 2019 . All rights reserved.
 *  * Last modified 1/1/19 12:38 PM
 *
 */

package com.example.myapplication.utils;

import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.myapplication.data.models.Contact;
import com.example.myapplication.data.models.Message;
import com.example.myapplication.ui.adapters.MessagesRecyclerAdapter;
import com.example.myapplication.ui.adapters.TabAdapter;
import com.example.myapplication.ui.adapters.TabsListRecyclerAdapter;
import com.example.myapplication.ui.customviews.CircularTextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public final class BindingUtils {

    private BindingUtils() {
        // This class is not publicly instantiable
    }

    @BindingAdapter({"bind:fragmentList", "bind:titleList"})
    public static void setPagerAdapter(ViewPager viewPager, ObservableArrayList<Fragment> fragments, ObservableArrayList<String> titles) {
        TabAdapter tabAdapter = (TabAdapter) viewPager.getAdapter();
        if(tabAdapter!=null) {
            for (int i = 0 ; i < fragments.size() ; i++) {
                tabAdapter.addFragment(fragments.get(i), titles.get(i));
            }
        }
    }

    @BindingAdapter("contactsAdapter")
    public static void setContactsAdapter(RecyclerView recyclerView, List<Contact> contactList){
        TabsListRecyclerAdapter adapter = (TabsListRecyclerAdapter)recyclerView.getAdapter();
        if(adapter!=null){
            adapter.clearItems();
            adapter.addItems(contactList);
        }
    }

    @BindingAdapter("setCircularTextView")
    public static void setCircularTextView(CircularTextView circularTextView, String initials){
        circularTextView.setTextFromInitials(initials);
        circularTextView.setRandomColor(new Random().nextInt(8));
    }

    @BindingAdapter("setFormattedTime")
    public static void setFormattedTime(TextView textView, Long timeStamp){
        if(textView!=null){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMM/yyyy HH:mm");
            textView.setText(simpleDateFormat.format(new Date(timeStamp)));
        }
    }

    @BindingAdapter("messagesAdapter")
    public static void setMessagesAdapter(RecyclerView recyclerView, List<Message> contactList){
        MessagesRecyclerAdapter adapter = (MessagesRecyclerAdapter)recyclerView.getAdapter();
        if(adapter!=null){
            adapter.clearItems();
            adapter.addItems(contactList);
        }
    }
}
