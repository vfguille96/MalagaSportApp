package com.bilalmoreno.malagasport.data.service;

import android.app.IntentService;
import android.app.Notification;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bilalmoreno.malagasport.MalagaSportApplication;
import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.data.db.model.Installation;
import com.bilalmoreno.malagasport.data.service.api.InstallationsApiAdapter;
import com.bilalmoreno.malagasport.data.service.model.installation.Installations;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MalagaSportService extends IntentService implements Callback<Installations> {
    private static final String name = "MalagaSportService";
    private Notification notification;
    private NotificationManagerCompat notificationManager;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public MalagaSportService() {
        super(name);
        //TODO Inicializar DAO y demás objetos necesarios para sincronizar los datos
    }

    @Override
    public void onCreate() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, MalagaSportApplication.CHANNEL_DB_CHANGES_ID)
                .setSmallIcon(R.drawable.ic_action_fav)
                .setColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .setContentTitle(getString(R.string.notification_updating_title))
                .setContentText(getString(R.string.notification_updating_text));
        Notification notification = builder.build();
        startForeground(100, notification);
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        //TODO Descargar, analizar y consolidar datos.
        updateInstallations();

        // Notificar que terminó la actualización
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, MalagaSportApplication.CHANNEL_DB_CHANGES_ID)
                .setSmallIcon(R.drawable.ic_action_fav)
                .setContentTitle(getString(R.string.notification_updated_title))
                .setContentText(getString(R.string.notification_updated_text))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);
        notification = builder.build();
        notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(0, notification);
//        stopSelf();
    }

    private void updateInstallations() {
        Call<Installations> call = InstallationsApiAdapter.getInstance().getBaloncesto();
        call.enqueue(MalagaSportService.this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Stoping Service", Toast.LENGTH_SHORT).show();
        //TODO Notificar que terminó la actualización
    }

    @Override
    public ComponentName startForegroundService(Intent service) {
        return super.startForegroundService(service);
    }

    @Override
    public void onResponse(Call<Installations> call, Response<Installations> response) {
        //TODO Tratar contenido e insertar en la base de datos
    }

    @Override
    public void onFailure(Call<Installations> call, Throwable t) {
        //TODO Notificar error
    }
}
