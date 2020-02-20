package com.danhtran12797.thd.appnext_test_2;

import android.app.Application;

import com.appnext.base.Appnext;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Appnext.init(this);
    }
}
