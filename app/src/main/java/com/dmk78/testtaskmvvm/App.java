package com.dmk78.testtaskmvvm;

import android.app.Application;

import com.dmk78.testtaskmvvm.di.AppComponent;
import com.dmk78.testtaskmvvm.di.DaggerAppComponent;


public class App extends Application {
    private static AppComponent appComponent;


    @Override
    public void onCreate() {
        super.onCreate();
       appComponent = DaggerAppComponent.create();

    }

    public static AppComponent getComponent() {
        return appComponent;
    }

}
