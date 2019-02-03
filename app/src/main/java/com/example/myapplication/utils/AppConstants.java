/*
 * *
 *  * Created by Asheesh Sharma on 1st January 2019.
 *  * Copyright (c) January 2019 . All rights reserved.
 *  * Last modified 1/1/19 3:39 PM
 *
 */

package com.example.myapplication.utils;

public final class AppConstants {

    public static final int API_STATUS_CODE_LOCAL_ERROR = 0;

    public static final String DB_NAME = "mojotimes";

    public static final long NULL_INDEX = -1L;

    public static final String PREF_NAME = "sharedmojopref";

    public static final int STATUS_CODE_FAILED = 500;

    public static final int STATUS_CODE_SUCCESS = 200;

    public static final String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";

    //Request Header Constants
    public static final String DEVICE_ID = "Device-id";
    public static final String AUTHORIZATION = "Authorization";
    public static final String VERSION_NAME = "Version-name";
    public static final String VERSION_CODE = "Version-code";
    public static final String CLIENT_ID = "Client-id";
    public static final String CONNECTION_QUALITY = "Connection-quality";
    public static final String CLIENT_SECRET = "Client-secret";
    public static final String LAT = "lat";
    public static final String LNG = "lng";

    public static final String SELECTED_ITEM_ID = "SELECTED_ITEM_ID";
    public static final int APP_REQUEST_CODE = 991;
    public static final String AUTHOR_NAME = "AUTHOR_NAME";

    public static final String FCM_TOKEN = "fcmToken";
    public static final String FCM_TOKEN_SENT = "fcmTokenSent";
    public static final String USER_NAME = "userName";
    public static final String IS_CONNECTED_ON_WHATSAPP = "isConnectedOnWhatsapp";
    public static final String APP_OPEN_COUNT = "appOpenCount";

    public static final String CATEGORY_ID = "categoryId";
    public static final String CATEGORY_KEY = "categoryKey";
    public static final String VIDEO_PLAYED = "videoPlayed";


    private AppConstants() {
        // This utility class is not publicly instantiable
    }
}
