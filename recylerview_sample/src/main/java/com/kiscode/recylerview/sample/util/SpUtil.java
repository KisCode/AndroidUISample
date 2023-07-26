package com.kiscode.recylerview.sample.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtil {
    private static final String SP_NAME = "SP_NAME";
    private static SpUtil instance;
    private SharedPreferences sharedPreferences;

    public SpUtil(Context context) {
        sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    public static SpUtil getInstance(Context context) {
        if (instance == null) {
            instance = new SpUtil(context.getApplicationContext());
        }
        return instance;
    }

    public void putInt(String key, int value) {
        sharedPreferences.edit().putInt(key, value).commit();
    }

    public int getInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }
}