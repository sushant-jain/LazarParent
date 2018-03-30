package com.trufle.lazarparent;

import android.app.Activity;
import android.app.Application;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Calendar;


public class App extends Application {
    private static final String TAG = "App";
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this);

//        ParseObject po=new ParseObject(Constants.Parse.Mother.TABLE_NAME);
//        po.put(Constants.Parse.Mother.AADHAR_MOTHER,"112");
//        po.put(Constants.Parse.Mother.NAME_MOTHER,"aaab");
//        po.put(Constants.Parse.Mother.ADDRESS,"addr");
//        po.saveInBackground();
//
//        ParseObject poc=new ParseObject(Constants.Parse.Transactions.TABLE_NAME);
//        poc.put(Constants.Parse.Transactions.VACCINE,"abc");
//        poc.put(Constants.Parse.Transactions.DONE_DATE,Calendar.getInstance().getTime());
//        poc.put(Constants.Parse.Transactions.DUE_DATE,Calendar.getInstance().getTime());
//        poc.put(Constants.Parse.Transactions.PERSON,po);
//
//        poc.saveInBackground();
//
//        ParseObject pod=new ParseObject(Constants.Parse.Pending.TABLE_NAME);
//        pod.put(Constants.Parse.Pending.VACCINE,"qwe");
//        pod.put(Constants.Parse.Pending.DUE_DATE,Calendar.getInstance().getTime());
//        pod.put(Constants.Parse.Pending.PERSON,po);
//        pod.saveInBackground();

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }


}
