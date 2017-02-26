package com.grishberg.mvvmstatelessexample.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by grishberg on 26.02.17.
 */

public final class SharedPreferenceUtils {
    private static final String DEF_STRING_VALUE = "";

    private final SharedPreferences preferences;

    public SharedPreferenceUtils(final Context context) {
        preferences = context.getSharedPreferences("mainPreferences", Context.MODE_PRIVATE);
    }

    public void putString(final String name, final String val) {
        final SharedPreferences.Editor ed = preferences.edit();
        ed.putString(name, val);
        ed.apply();
    }

    public String getString(final String name) {
        return preferences.getString(name, DEF_STRING_VALUE);
    }
}
