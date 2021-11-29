package com.dodola.watchdogkiller;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import java.util.concurrent.TimeoutException;

public class SampleApplication extends Application {

    @Override
    protected void attachBaseContext(final Context base) {
        super.attachBaseContext(base);
        final Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                if (t.getName().equals("FinalizerWatchdogDaemon") && e instanceof TimeoutException) {
                    //ignore it
                } else {
                    defaultUncaughtExceptionHandler.uncaughtException(t, e);
                }
            }
        });
    }
}
