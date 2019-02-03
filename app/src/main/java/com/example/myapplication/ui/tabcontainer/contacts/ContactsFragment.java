package com.example.myapplication.ui.tabcontainer.contacts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.data.models.Contact;
import com.example.myapplication.databinding.FragmentContactsBinding;
import com.example.myapplication.ui.adapters.TabsListRecyclerAdapter;
import com.example.myapplication.ui.details.DetailActivity;
import com.example.myapplication.utils.AppConstants;
import com.google.gson.Gson;

import javax.inject.Inject;

public class ContactsFragment extends BaseFragment<FragmentContactsBinding, ContactsViewModel>
        implements ContactsViewNavigator, TabsListRecyclerAdapter.BlogAdapterListener {

    @Inject
    ContactsViewModel mContactsViewModel;

    @Inject
    TabsListRecyclerAdapter tabsListRecyclerAdapter;

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
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentContactsBinding = getViewDataBinding();
        setUp();
    }

    private void setUp() {
//        mFragmentMainBinding
        mContactsViewModel.setNavigator(this);
        mContactsViewModel.fetchContactsList();
        tabsListRecyclerAdapter.setListener(this);
        mFragmentContactsBinding.contactsRv.setAdapter(tabsListRecyclerAdapter);
        mFragmentContactsBinding.contactsRv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        tabsListRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public void onItemClick(Contact contact) {
        Intent intent = DetailActivity.newIntent(context);
        intent.putExtra(AppConstants.CONTACT_ATTACH, new Gson().toJson(contact));
        startActivity(intent);
    }
}
