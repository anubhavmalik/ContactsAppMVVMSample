/*
 * *
 *  * Created by Asheesh Sharma on 1st January 2019.
 *  * Copyright (c) January 2019 . All rights reserved.
 *  * Last modified 1/1/19 12:38 PM
 *
 */

package com.example.myapplication.utils.rx;

import io.reactivex.Scheduler;

public interface SchedulerProvider {

    Scheduler computation();

    Scheduler io();

    Scheduler ui();
}
