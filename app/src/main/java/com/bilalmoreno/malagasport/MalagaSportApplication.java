package com.bilalmoreno.malagasport;

import android.annotation.TargetApi;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import com.bilalmoreno.malagasport.data.service.MalagaSportService;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Pattern;

public class MalagaSportApplication extends Application {

    /*
    Tags para las preferencias compartidas de la app.
     */
    public static final String LOGIN_DATA_TAG = "loginData";
    public static final String USER_TAG = "userEmail";
    public static final String PASSWORD_TAG = "password";
    /*
    Requisitos email
     */
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    /*
    Rquisitos password
     */
    public static final int MIN_PASSWORD_LENGTH = 8;
    public static final int MAX_PASSWORD_LENGTH = 32;
    /*
    Formato de fecha por defecto
     */
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    /*
    Tags settings
     */
    public static final String SETTINGS = "settings";
    public static final String METEO_ON_MAP = "meteo_on_map";
    /*
    Notificaciones
     */
    public static final String CHANNEL_DB_CHANGES_ID = "db_changes";
    /*
    ID del usuario que inicia sesión
     */
    private static String userId;
    /*
    Context
     */
    private static Context context;

    public static Context getContext() {
        return context;
    }

    /**
     * Metodo que devuelve el ID del usuario de la sesión actual.
     *
     * @return
     */
    public static String getUserId() {
        return userId;
    }

    /**
     * Método que establece el ID del usuario de la sesión actual.
     *
     * @param userId
     */
    public static void setUserId(String userId) {
        MalagaSportApplication.userId = userId;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        createNotificationChannel();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(new Intent(context, MalagaSportService.class));
        } else {
            startService(new Intent(context, MalagaSportService.class));
        }
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
