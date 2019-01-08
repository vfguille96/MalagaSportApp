package com.bilalmoreno.malagasport.ui;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.bilalmoreno.malagasport.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    public static final String TAG = "SettingsFragment";

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.preferencias);
    }
}
