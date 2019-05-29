package com.bilalmoreno.malagasport.data.service;

import android.app.IntentService;
import android.app.Notification;
import android.content.ComponentName;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;

import com.bilalmoreno.malagasport.MalagaSportApplication;
import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.data.service.api.InstallationsApiAdapter;
import com.bilalmoreno.malagasport.data.service.api.WorkoutsApiAdapter;
import com.bilalmoreno.malagasport.data.service.listener.InstallationsCallback;
import com.bilalmoreno.malagasport.data.service.listener.WorkoutsCallback;
import com.bilalmoreno.malagasport.data.service.model.installation.Installations;
import com.bilalmoreno.malagasport.data.service.model.workout.Workouts;

import retrofit2.Call;

public class MalagaSportService extends IntentService implements InstallationsCallback.OnDownloadErrorListener {
    private static final String name = "MalagaSportService";
    private Notification notificationSuccess;
    private Notification notificationError;
    private NotificationManagerCompat notificationManager;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public MalagaSportService() {
        super(name);
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

        // Comenzar proceso de actualización
        updateDataBase();

        // Creación de la notificación de actualización existosa
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, MalagaSportApplication.CHANNEL_DB_CHANGES_ID)
                .setSmallIcon(R.drawable.ic_action_fav)
                .setContentTitle(getString(R.string.notification_updated_title))
                .setContentText(getString(R.string.notification_updated_text))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);
        notificationSuccess = builder.build();

        //Creación de la notificación de error
        builder.setContentTitle(getString(R.string.error_update_title))
        .setContentText(getString(R.string.error_update_text));
        notificationError = builder.build();

        //Estableciendo referencia al gestor de notifiaciones del sistema
        notificationManager = NotificationManagerCompat.from(this);
    }

    private void updateDataBase() {
        // Baloncesto
        Call<Installations> call = InstallationsApiAdapter.getInstance().getBaloncesto();
        call.enqueue(new InstallationsCallback(this));
        // Ajedrez
        call = InstallationsApiAdapter.getInstance().getAjedrez();
        call.enqueue(new InstallationsCallback(this));
        // Centros deportivos
        call = InstallationsApiAdapter.getInstance().getCentrosDeportivos();
        call.enqueue(new InstallationsCallback(this));
        // Voley
        call = InstallationsApiAdapter.getInstance().getVoley();
        call.enqueue(new InstallationsCallback(this));
        // Senderismo
        call = InstallationsApiAdapter.getInstance().getSenderimos();
        call.enqueue(new InstallationsCallback(this));
        // Petanca
        call = InstallationsApiAdapter.getInstance().getPetanca();
        call.enqueue(new InstallationsCallback(this));
        // Motos
        call = InstallationsApiAdapter.getInstance().getMotor();
        call.enqueue(new InstallationsCallback(this));
        // Piscinas
        call = InstallationsApiAdapter.getInstance().getPiscinas();
        call.enqueue(new InstallationsCallback(this));
        // Tenis
        call = InstallationsApiAdapter.getInstance().getTenis();
        call.enqueue(new InstallationsCallback(this));
        // Voley playa
        call = InstallationsApiAdapter.getInstance().getVoleyPlaya();
        call.enqueue(new InstallationsCallback(this));
        // Padel
        call = InstallationsApiAdapter.getInstance().getPadel();
        call.enqueue(new InstallationsCallback(this));
        // Atletismo
        call = InstallationsApiAdapter.getInstance().getAtletismo();
        call.enqueue(new InstallationsCallback(this));
        // Badminton
        call = InstallationsApiAdapter.getInstance().getBadminton();
        call.enqueue(new InstallationsCallback(this));
        // Escalada
        call = InstallationsApiAdapter.getInstance().getEscalada();
        call.enqueue(new InstallationsCallback(this));
        // Futbol 3
        call = InstallationsApiAdapter.getInstance().getFutbol3();
        call.enqueue(new InstallationsCallback(this));
        // Futbol sala
        call = InstallationsApiAdapter.getInstance().getFutbolSala();
        call.enqueue(new InstallationsCallback(this));
        // Salas deportivas
        call = InstallationsApiAdapter.getInstance().getSalasDeportivas();
        call.enqueue(new InstallationsCallback(this));
        // Skate/BMX
        call = InstallationsApiAdapter.getInstance().getSkateBMX();
        call.enqueue(new InstallationsCallback(this));
        // Aparatos
        call = InstallationsApiAdapter.getInstance().getAparatos();
        call.enqueue(new InstallationsCallback(this));
        // Pistas polideportivas
        call = InstallationsApiAdapter.getInstance().getPistasPolideportivas();
        call.enqueue(new InstallationsCallback(this));
        // Tenis de mesa
        call = InstallationsApiAdapter.getInstance().getTenisMesa();
        call.enqueue(new InstallationsCallback(this));
        // Futbol playa
        call = InstallationsApiAdapter.getInstance().getFutbolPlaya();
        call.enqueue(new InstallationsCallback(this));
        // Futbol
        call = InstallationsApiAdapter.getInstance().getFutbol();
        call.enqueue(new InstallationsCallback(this));
        // Pabellones
        call = InstallationsApiAdapter.getInstance().getPabellones();
        call.enqueue(new InstallationsCallback(this));
        // Workouts
        Call<Workouts> callWorkout = WorkoutsApiAdapter.getInstance().getWorkouts();
        callWorkout.enqueue(new WorkoutsCallback(this));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        notificationManager.notify(0, notificationSuccess);
    }

    @Override
    public ComponentName startForegroundService(Intent service) {
        return super.startForegroundService(service);
    }

    @Override
    public void onDownloadError() {
        notificationManager.notify(0, notificationError);
    }
}
