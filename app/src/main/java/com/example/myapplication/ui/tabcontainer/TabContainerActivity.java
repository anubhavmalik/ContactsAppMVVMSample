package com.example.myapplication.ui.tabcontainer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.ui.adapters.TabAdapter;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class TabContainerActivity extends BaseActivity<ActivityMainBinding, TabContainerViewModel>
        implements HasSupportFragmentInjector, TabContainerNavigator {

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    @Inject
    TabContainerViewModel tabContainerViewModel;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    TabAdapter tabAdapter;

    ActivityMainBinding mActivityMainBinding;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public TabContainerViewModel getViewModel() {
        return tabContainerViewModel;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = getViewDataBinding();
        initAdapters();
        setUp();
    }

    private void initAdapters(){
        tabAdapter = new TabAdapter(getSupportFragmentManager());
    }

    private void setUp(){
        tabContainerViewModel.setNavigator(this);
        mActivityMainBinding.tabContainerPager.setAdapter(tabAdapter);
        mActivityMainBinding.tabContainerTabLayout.setupWithViewPager(mActivityMainBinding.tabContainerPager, true);
        mActivityMainBinding.tabContainerPager.setOffscreenPageLimit(1);
        tabAdapter.notifyDataSetChanged();
        mActivityMainBinding.invalidateAll();
    }

    @Override
    protected void onResume() {
        super.onResume();
        tabAdapter.notifyDataSetChanged();
    }

    @Override
    public void handleError() {
        //do something
    }
}
