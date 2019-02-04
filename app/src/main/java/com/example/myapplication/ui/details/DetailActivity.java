package com.example.myapplication.ui.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.data.models.Contact;
import com.example.myapplication.databinding.ActivityDetailBinding;
import com.example.myapplication.utils.AppConstants;
import com.google.gson.Gson;

import javax.inject.Inject;

public class DetailActivity extends BaseActivity<ActivityDetailBinding, DetailViewModel> implements DetailNavigator {

    @Inject
    DetailViewModel detailViewModel;

    ActivityDetailBinding activityDetailBinding;

    public static Intent newIntent(Context context){
        return new Intent(context, DetailActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    public DetailViewModel getViewModel() {
        return detailViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDetailBinding = getViewDataBinding();
        getDataFromBundle();
        detailViewModel.setDetailNavigator(this);
    }

    private void getDataFromBundle() {
        if(getIntent()!=null){
            if(getIntent().getStringExtra(AppConstants.CONTACT_ATTACH)!=null){
                try{
                    Gson gson = new Gson();
                    detailViewModel.setContact(gson.fromJson(getIntent().getStringExtra(AppConstants.CONTACT_ATTACH), Contact.class));
                    activityDetailBinding.invalidateAll();
                }
                catch (Exception e){
                    e.printStackTrace();
                    handleError(true);
                }
            }
            else {
                handleError(true);
            }
        }
        else {
            handleError(true);
        }
    }

    @Override
    public void handleError(boolean finishCurrent) {
        // do something
        if(finishCurrent){
            Toast.makeText(this, "Oops! Something went wrong", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void openComposeMessageScreen() {
        //TODO: open compose screen
        activityDetailBinding.messageContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void endActivity(){
        Toast.makeText(this, "Message has been sent", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void setLoading(boolean show) {
        activityDetailBinding.progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
