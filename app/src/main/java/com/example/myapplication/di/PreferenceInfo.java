/*
 * *
 *  * Created by Asheesh Sharma on 1st January 2019.
 *  * Copyright (c) January 2019 . All rights reserved.
 *  * Last modified 1/1/19 1:03 PM
 *
 */

package com.example.myapplication.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;


/**
 * Created by Asheesh Sharma on 01/01/19.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface PreferenceInfo {

}
