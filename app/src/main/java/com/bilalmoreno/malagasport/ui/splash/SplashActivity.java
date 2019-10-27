package com.bilalmoreno.malagasport.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bilalmoreno.malagasport.MalagaSportApplication;
import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.ui.MainNavigationActivity;
import com.bilalmoreno.malagasport.ui.login.LoginActivity;

public class SplashActivity extends AppCompatActivity implements SplashContract.View {

    private SplashContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        SharedPreferences loginData = getSharedPreferences(MalagaSportApplication.LOGIN_DATA_TAG, Context.MODE_PRIVATE);

        String user = loginData.getString(MalagaSportApplication.USER_TAG, "");
        String password = loginData.getString(MalagaSportApplication.PASSWORD_TAG, "");

        presenter = new SplashPresenter(this);
        presenter.validateCredentials(user, password);

    }

    @Override
    public void onAuthenticationFailed() {
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    public void onAuthenticationSucess() {
        startActivity(new Intent(SplashActivity.this, MainNavigationActivity.class));
        finish();
    }
}
