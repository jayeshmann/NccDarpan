package com.tsa.nccapp.utils;

import android.content.Context;
import android.content.SharedPreferences;


public class SaveSharedPrefernces {
    SharedPreferences sharedPreferences;

    public static final String PREFS_NAME = "nccApp.pref";

    public static final String User_id= "user_id";
    public String getUser_id(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String id = sharedPreferences.getString(User_id, "");

        return id;
    }

    public void setUser_id(Context context, String id) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(User_id, id);
        editor.commit();
    }
}
