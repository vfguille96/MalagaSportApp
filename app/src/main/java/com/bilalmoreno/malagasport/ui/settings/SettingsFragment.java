package com.bilalmoreno.malagasport.ui.settings;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import androidx.annotation.Nullable;

import com.bilalmoreno.malagasport.R;

public class SettingsFragment extends PreferenceFragment {
    public static final String TAG = "settings_fragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}
