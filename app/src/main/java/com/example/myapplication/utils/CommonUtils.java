/*
 * *
 *  * Created by Asheesh Sharma on 1st January 2019.
 *  * Copyright (c) January 2019 . All rights reserved.
 *  * Last modified 1/1/19 12:38 PM
 *
 */

package com.example.myapplication.utils;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.provider.Settings;
import android.util.Base64;
import android.util.Patterns;

import com.example.myapplication.app.MyApplication;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import static com.example.myapplication.utils.NetworkUtils.DEVICE_ID_ENCRYPTION_KEY;
import static java.lang.System.currentTimeMillis;


public final class CommonUtils {

    private CommonUtils() {
        // This utility class is not publicly instantiable
    }

    public static String getDeviceSerialNumber() {

        String serialNo = null;
        try {
            serialNo = Build.SERIAL;
            if (serialNo != null && !serialNo.equals(""))
                return serialNo;

            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class, String.class);
            serialNo = (String) (get.invoke(c, "ro.serialno", ""));
            if (serialNo != null && !serialNo.equals(""))
                return serialNo;

            serialNo = (String) get.invoke(c, "sys.serialnumber", "");
            if (serialNo != null && !serialNo.equals(""))
                return serialNo;

            serialNo = (String) get.invoke(c, "ril.serialnumber", "");
            if (serialNo != null && !serialNo.equals(""))
                return serialNo;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String getAndroidId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String encryptString(String value, String key) {

        try {
            Cipher c = Cipher.getInstance("AES");
            SecretKeySpec k = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            c.init(Cipher.ENCRYPT_MODE, k);
            byte[] encBytes = c.doFinal(value.getBytes("UTF-8"));
            String encStr = new String(Base64.encode(encBytes, Base64.NO_WRAP), "UTF-8");
            return encStr;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }


    @SuppressLint("all")
    public static String getDevId(Context context) {
        if (false) {
            return "asdjflaksdfb";
        }

        SharedPreferences mPrefs = context.getSharedPreferences("app_state", Context.MODE_PRIVATE);
        String deviceId = mPrefs.getString("device_id", "none");

        if (deviceId == null || deviceId.equals("") || deviceId.equals("none")) {
            // Nothing in pref. Probably fresh install
            String unencDevice_id = "";
            String serialNo = getDeviceSerialNumber();
            if (serialNo == null || serialNo.equals(""))
                serialNo = "Not_available";

            String android_id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            if (android_id == null || android_id.equals("")) {
                android_id = "Not_available";
            }

            if (!android_id.equals("Not_available") || !serialNo.equals("Not_available")) {
                // you have some data, atleast
                unencDevice_id = serialNo + "-" + android_id;
            } else {
                String buildModel = Build.MODEL;
                if (buildModel == null)
                    buildModel = "unknown";
                String manufacturer = Build.MANUFACTURER;
                if (manufacturer == null)
                    manufacturer = "unknown";
                unencDevice_id = "Mojo." + buildModel + "." + manufacturer + "." + currentTimeMillis();
            }

            String encDevice_id = encryptString(unencDevice_id, DEVICE_ID_ENCRYPTION_KEY);
            if (encDevice_id == null) {
                try {
                    unencDevice_id = URLEncoder.encode(unencDevice_id, "UTF-8");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new SharedPrefsUtils().setStringPreference(MyApplication.getNonUiContext(), SharedPrefsUtils.deviceId, unencDevice_id);
                return unencDevice_id;
            }

            try {
                encDevice_id = URLEncoder.encode(encDevice_id, "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
            new SharedPrefsUtils().setStringPreference(MyApplication.getNonUiContext(), SharedPrefsUtils.deviceId, encDevice_id);
            return encDevice_id;

        } else {
            return deviceId;
        }
    }

    public static String getTimestamp() {
        return new SimpleDateFormat(AppConstants.TIMESTAMP_FORMAT, Locale.US).format(new Date());
    }

    public static boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static String loadJSONFromAsset(Context context, String jsonFileName) throws IOException {
        AssetManager manager = context.getAssets();
        InputStream is = manager.open(jsonFileName);

        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        return new String(buffer, "UTF-8");
    }

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }
}
