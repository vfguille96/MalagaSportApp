package com.bilalmoreno.malagasport.ui.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v14.preference.SwitchPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.bilalmoreno.malagasport.MalagaSportApplication;
import com.bilalmoreno.malagasport.R;

public class SettingsFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener {

    public static final String TAG = "SettingsFragment";
    SwitchPreference meteoOnMap;

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.settings);
        meteoOnMap = (SwitchPreference) findPreference(MalagaSportApplication.METEO_ON_MAP);
        meteoOnMap.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        SharedPreferences settings = getContext().getSharedPreferences(MalagaSportApplication.SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        if (preference == meteoOnMap) {
            editor.putBoolean(MalagaSportApplication.METEO_ON_MAP, meteoOnMap.isChecked());
            editor.apply();
            return true;
        }
        return false;
    }
}
