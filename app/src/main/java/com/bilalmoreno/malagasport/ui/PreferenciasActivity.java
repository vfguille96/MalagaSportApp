package com.bilalmoreno.malagasport.ui;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;

import com.bilalmoreno.malagasport.R;

public class PreferenciasActivity extends PreferenceActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferencias);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
