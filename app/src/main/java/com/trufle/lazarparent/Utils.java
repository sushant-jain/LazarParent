package com.trufle.lazarparent;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by vishal on 2/4/18 as a part of Store-Manager-Pro.
 */

@SuppressWarnings("WeakerAccess")
public class Utils {
    private static final String TAG = "Utils";

    //utility function to show toast.
    public static void showToast(Context ctx, String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
    }

    //Utility function to build an alert dialog
    //if either of posBtn or negBtn or ntrlBtn is not required set its title to null
    public static AlertDialog alertDialog(Context ctx, String title, String msg, View view,
                                          String posBtnTitle, DialogInterface.OnClickListener posBtnClick,
                                          String ntrlBtnTitle, DialogInterface.OnClickListener ntrlBtnClick,
                                          String negBtnTitle, DialogInterface.OnClickListener negBtnClick) {

        AlertDialog.Builder ab = new AlertDialog.Builder(ctx);
        ab.setTitle(title);
        if (msg != null) ab.setMessage(msg);
        if (view != null) ab.setView(view);
        if (posBtnTitle != null) {
            ab.setPositiveButton(posBtnTitle, posBtnClick);
        }
        if (negBtnTitle != null) {
            ab.setNegativeButton(negBtnTitle, negBtnClick);
        }
        if (ntrlBtnTitle != null) {
            ab.setNeutralButton(ntrlBtnTitle, ntrlBtnClick);
        }
        return ab.create();
    }


    public static String readSharedPreferences(Context c, String key) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(c);
        return sp.getString(key, Constants.SharedPreferences.DEFAULT);
    }

    public static Date atEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static Date atStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

}
