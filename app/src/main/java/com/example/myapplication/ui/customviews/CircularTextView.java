/*
 * *
 *  * Created by Asheesh Sharma on 1st January 2019.
 *  * Copyright (c) January 2019 . All rights reserved.
 *  * Last modified 4/1/19 1:24 AM
 *
 */

package com.example.myapplication.ui.customviews;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

import com.example.myapplication.R;


public class CircularTextView extends AppCompatTextView {

    public CircularTextView(Context context) {
        super(context);
    }

    public CircularTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircularTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setRandomColor(int randomNumber) {
        GradientDrawable bgShape = (GradientDrawable) getBackground();
        Log.d("KKKK", bgShape!=null ? "null nahi hai" : "null aya");
        String[] colors = getResources().getStringArray(R.array.contactsColors);
        bgShape.setColor(Color.parseColor(colors[randomNumber]));
    }

    public void setTextFromInitials(@NonNull String initials){
        setText(initials);
        setTextColor(getResources().getColor(android.R.color.white));
        setTextSize(18);
    }

}
