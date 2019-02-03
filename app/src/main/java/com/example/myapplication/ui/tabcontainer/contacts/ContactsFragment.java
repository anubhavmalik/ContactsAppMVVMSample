package com.example.myapplication.ui.tabcontainer.contacts;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.databinding.FragmentContactsBinding;

import javax.inject.Inject;

public class ContactsFragment extends BaseFragment<FragmentContactsBinding, ContactsViewModel>
        implements ContactsViewNavigator {

    @Inject
    ContactsViewModel mContactsViewModel;

    Context context;

    FragmentContactsBinding mFragmentContactsBinding;

    public static ContactsFragment newInstance() {
        return new ContactsFragment();
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
    public ContactsViewModel getViewModel() {
        return mContactsViewModel;
    }

    @Override
    public void handleError() {
        // do something
    }

    @Override
    public void notifyListFetched() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContactsViewModel.setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentContactsBinding = getViewDataBinding();
        setUp();
    }

    private void setUp() {
//        mFragmentMainBinding
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }
}
