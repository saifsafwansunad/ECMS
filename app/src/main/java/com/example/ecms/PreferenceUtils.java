package com.example.ecms;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceUtils {
    public PreferenceUtils(){
    }
    public static boolean saveEmail(String email, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString(LoginConstants.KEY_EMAIL, email);


        prefsEditor.apply();
        return true;
    }
    public static String getEmail(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(LoginConstants.KEY_EMAIL, null);
    }


    public static String getUid(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(LoginConstants.USER_ID, null);
    }


    public static boolean saveUid(String Uid, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString(LoginConstants.USER_ID, Uid);

        prefsEditor.apply();
        return true;
    }

    public static boolean getCouncilor(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean(String.valueOf(LoginConstants.IS_COUNCILOR), false);
    }


    public static boolean saveCouncilor(boolean Uid, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putBoolean(String.valueOf(LoginConstants.IS_COUNCILOR), Uid);

        prefsEditor.apply();
        return true;
    }



    public static boolean savePassword(String password, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString(LoginConstants.KEY_PASSWORD, password);
        prefsEditor.apply();
        return true;
    }
    public static String getPassword(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(LoginConstants.KEY_PASSWORD, null);
    }

    public static boolean saveUserName(String userName, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString(LoginConstants.USER_NAME, userName);


        prefsEditor.apply();
        return true;
    }
    public static String getUserName(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(LoginConstants.USER_NAME, null);
    }

    public static boolean saveMobile(String mobile, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString(LoginConstants.USER_MOBILE, mobile);


        prefsEditor.apply();
        return true;
    }
    public static String getMobile(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(LoginConstants.USER_MOBILE, null);
    }


}
