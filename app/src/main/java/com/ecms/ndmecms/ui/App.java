package com.ecms.ndmecms.ui;

import android.app.Application;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("App is called", "");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(new Intent(this, MyService.class));
        }
    }
}