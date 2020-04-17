package com.cherryinc;

import android.app.Application;

import com.cherryinc.manedger.PreferencesManagerImpl;
import com.onesignal.OneSignal;


public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        new PreferencesManagerImpl(this);


// OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();


    }



}
