package com.example.myapplication.ui.tabcontainer.messages;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.databinding.FragmentMessagesBinding;

import javax.inject.Inject;

public class MessagesFragment extends BaseFragment<FragmentMessagesBinding, MessagesViewModel>
        implements MessagesViewNavigator {

    @Inject
    MessagesViewModel mMessagesViewModel;

    Context context;

    FragmentMessagesBinding mFragmentMessagesBinding;

    public static MessagesFragment newInstance() {
        return new MessagesFragment();
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_contacts;
    }

    @Override
    public MessagesViewModel getViewModel() {
        return mMessagesViewModel;
    }

    @Override
    public void handleError() {
        // do something
    }

    @Override
    public void notifyListFetched() {
        //TODO: Refresh adapter here
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMessagesViewModel.setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentMessagesBinding = getViewDataBinding();
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }
}
