package com.last.testapplication;

import android.app.Application;

import com.last.fm.api.Lfm;

public class TestApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //Initialize here.
        Lfm.initializeWithSecret(this);
        //You can initialize without secret,but remember that you cannot use methods that require authentication.
        //Lfm.initialize(this);

    }
}
