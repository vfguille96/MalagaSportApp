package com.bilalmoreno.malagasport.ui.splash;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bilalmoreno.malagasport.MalagaSportApplication;
import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.data.service.MalagaSportService;
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

        createNotificationChannel();

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

    /**
     * Método que crea un canal de notificaciones para la aplicación en API 26 o superior.
     * Este canal se usará para notificar al usuario cuando el servicio actualice la base de datos.
     */
    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel() {
            NotificationChannel channel = new NotificationChannel(MalagaSportApplication.CHANNEL_DB_CHANGES_ID, getString(R.string.notification_channel_name), NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(getString(R.string.notification_channel_description));
            channel.enableLights(true);
            channel.setLightColor(Color.BLUE);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
    }
}
