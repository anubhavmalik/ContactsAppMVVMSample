/*
 * *
 *  * Created by Asheesh Sharma on 1st January 2019.
 *  * Copyright (c) January 2019 . All rights reserved.
 *  * Last modified 1/1/19 12:35 PM
 *
 */

package com.example.myapplication.factories;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class ViewModelProviderFactory<V> implements ViewModelProvider.Factory {
    private V mViewModel;

    public ViewModelProviderFactory(V viewModel) {
        mViewModel = viewModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(mViewModel.getClass())) {
            return (T) mViewModel;
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}
