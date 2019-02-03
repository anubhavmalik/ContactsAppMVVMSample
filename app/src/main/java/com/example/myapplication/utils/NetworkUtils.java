/*
 * *
 *  * Created by Asheesh Sharma on 1st January 2019.
 *  * Copyright (c) January 2019 . All rights reserved.
 *  * Last modified 1/1/19 12:38 PM
 *
 */

package com.example.myapplication.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

public final class NetworkUtils {

    private static final int CONNECTION_WIFI_CONNECTED = 1;
    private static final int CONNECTION_WWAN_CONNECTED = 2;
    private static final int CONNECTION_NOT_CONNECTED = 3;
    public static String DEVICE_ID_ENCRYPTION_KEY = "S1A3A7V1N19P20I3";

    private NetworkUtils() {
        // This class is not publicly instantiable
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
        return false;
    }

    public static int getConnectionStatus(Context context) {

        ConnectivityManager connectManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectManager == null) {
            return CONNECTION_NOT_CONNECTED;
        }
        NetworkInfo netInfo = connectManager.getActiveNetworkInfo();

        if (netInfo == null || !netInfo.isConnectedOrConnecting()) {
            return CONNECTION_NOT_CONNECTED;
        } else {
            if ((netInfo.getType() == ConnectivityManager.TYPE_WIFI) || (netInfo.getType() == ConnectivityManager.TYPE_WIMAX)) {
                return CONNECTION_WIFI_CONNECTED;
            }
            if ((netInfo.getType() == ConnectivityManager.TYPE_MOBILE) || (netInfo.getType() == ConnectivityManager.TYPE_MOBILE_DUN) || (netInfo.getType() == ConnectivityManager.TYPE_MOBILE_HIPRI) ||
                    (netInfo.getType() == ConnectivityManager.TYPE_MOBILE_SUPL)
                    || (netInfo.getType() == ConnectivityManager.TYPE_MOBILE_MMS)) {
                return CONNECTION_WWAN_CONNECTED;
            }
        }
        return CONNECTION_NOT_CONNECTED;

    }

    public static String getNetworkGeneration(Context context) {

        if (getConnectionStatus(context) == CONNECTION_WIFI_CONNECTED) {
            return "HIGH";
        }

        TelephonyManager mTelephonyManager = (TelephonyManager)
                context.getSystemService(Context.TELEPHONY_SERVICE);
        if (mTelephonyManager == null) {
            return "UNKNOWN";
        }
        int networkType = mTelephonyManager.getNetworkType();
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return "LOW";
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return "MED";
            case TelephonyManager.NETWORK_TYPE_LTE:
                return "HIGH";
            default:
                return "UNKNOWN";
        }

    }
}
