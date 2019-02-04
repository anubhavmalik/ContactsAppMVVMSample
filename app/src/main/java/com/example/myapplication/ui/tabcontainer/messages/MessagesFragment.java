package com.example.myapplication.ui.tabcontainer.messages;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.databinding.FragmentMessagesBinding;
import com.example.myapplication.ui.adapters.MessagesRecyclerAdapter;

import javax.inject.Inject;

public class MessagesFragment extends BaseFragment<FragmentMessagesBinding, MessagesViewModel>
        implements MessagesViewNavigator {

    @Inject
    MessagesViewModel mMessagesViewModel;

    @Inject
    MessagesRecyclerAdapter messagesRecyclerAdapter;

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
        return R.layout.fragment_messages;
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
        mMessagesViewModel.setMessagesViewNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentMessagesBinding = getViewDataBinding();
        mMessagesViewModel.fetchMessagesList();
        setUp();
    }

    private void setUp() {
        mMessagesViewModel.setNavigator(this);
        mMessagesViewModel.fetchMessagesList();
        mFragmentMessagesBinding.messagesRv.setAdapter(messagesRecyclerAdapter);
        mFragmentMessagesBinding.messagesRv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        messagesRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }
}
