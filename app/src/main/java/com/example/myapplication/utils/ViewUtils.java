
/*
 * *
 *  * Created by Asheesh Sharma on 1st January 2019.
 *  * Copyright (c) January 2019 . All rights reserved.
 *  * Last modified 1/1/19 12:38 PM
 *
 */

package com.example.myapplication.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.example.myapplication.R;


public final class ViewUtils {
    public static Snackbar downloadSBar;
    private static String TAG = ViewUtils.class.getSimpleName();

    private ViewUtils() {
        // This class is not publicly instantiable
    }

    public static void changeIconDrawableToGray(Context context, Drawable drawable) {
        if (drawable != null) {
            drawable.mutate();
            drawable.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        }
    }

    public static int dpToPx(float dp) {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    public static float pxToDp(float px) {
        float densityDpi = Resources.getSystem().getDisplayMetrics().densityDpi;
        return px / (densityDpi / 160f);
    }

//    public static void initSnackBar(View baseView, int length) {
//
//        try {
//            downloadSBar = Snackbar.make(baseView, baseView.getContext().getResources().getString(R.string.downloading), length);
//            View view = ViewUtils.downloadSBar.getView();
//             set view color as red
//            view.setBackgroundColor(baseView.getContext().getResources().getColor(R.color.colorPrimary));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    public static boolean showToast(Context context, String msg) {
//        if (downloadSBar != null) {
//            downloadSBar.show();
//            return true;
//        }
//        return false;
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
        return true;
    }

//    public static void setSnackbarProgress(int progress, Context applicationContext) {
//
//        try {
//            if (true) {
//                return;
//            }
//            Log.d(TAG, "download progress " + progress);
//            if (downloadSBar != null) {
//                if (progress < 100 && (progress % 12 == 0)) {
//                    downloadSBar.setText(applicationContext.getResources().getString(R.string.downloading) + " " + progress + " %");
//                    downloadSBar.show();
//                } else if (100 <= progress) {
//                    downloadSBar.setText(applicationContext.getResources().getString(R.string.downloading));
//                    downloadSBar.show();
//                }
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

//    public static boolean dismissSnackbar() {
//        try {
//            if (downloadSBar != null) {
//                downloadSBar.dismiss();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return true;
//    }

}
