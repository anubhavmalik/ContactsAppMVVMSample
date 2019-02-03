/*
 * *
 *  * Created by Asheesh Sharma on 1st January 2019.
 *  * Copyright (c) January 2019 . All rights reserved.
 *  * Last modified 1/1/19 12:38 PM
 *
 */

package com.example.myapplication.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;


public final class AppUtils {

    private AppUtils() {
        // This class is not publicly instantiable
    }

    public static void openPlayStoreForApp(Context context) {
        final String appPackageName = context.getPackageName();
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(appPackageName))); //correct the link
        } catch (android.content.ActivityNotFoundException e) {
            e.printStackTrace();
//            context.startActivity(new Intent(Intent.ACTION_VIEW,
//                    Uri.parse(context
//                            .getResources()
//                            .getString(R.string.app_google_play_store_link) + appPackageName)));
        }
    }


    public static void startActivity(Activity activity, Class destination, boolean endPrevious) {
        Intent intent = new Intent(activity, destination);

        if (endPrevious) {
            activity.finish();
        }
        activity.startActivity(intent);
    }

}
