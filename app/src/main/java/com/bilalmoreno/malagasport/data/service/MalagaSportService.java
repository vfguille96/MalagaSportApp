package com.bilalmoreno.malagasport.data.service;

import android.app.IntentService;
import android.app.Notification;
import android.content.ComponentName;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.bilalmoreno.malagasport.MalagaSportApplication;
import com.bilalmoreno.malagasport.R;
import com.bilalmoreno.malagasport.data.service.api.InstallationsApiAdapter;
import com.bilalmoreno.malagasport.data.service.api.WorkoutsApiAdapter;
import com.bilalmoreno.malagasport.data.service.listener.InstallationsCallback;
import com.bilalmoreno.malagasport.data.service.listener.WorkoutsCallback;
import com.bilalmoreno.malagasport.data.service.model.installation.Installations;
import com.bilalmoreno.malagasport.data.service.model.workout.Workouts;

import java.util.EmptyStackException;
import java.util.Stack;

import retrofit2.Call;

public class MalagaSportService extends IntentService implements InstallationsCallback.OnDownloadErrorListener {
    private static final String name = "MalagaSportService";
    private Notification notificationSuccess;
    private Notification notificationError;
    private NotificationManagerCompat notificationManager;
    private Call<Workouts> workoutsCall;
    private Stack<Call<Installations>> calls;

    boolean error = false;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public MalagaSportService() {
        super(name);

        calls = new Stack<>();
        calls.push(InstallationsApiAdapter.getInstance().getBaloncesto());
        calls.push(InstallationsApiAdapter.getInstance().getAjedrez());
        calls.push(InstallationsApiAdapter.getInstance().getCentrosDeportivos());
        calls.push(InstallationsApiAdapter.getInstance().getVoley());
        calls.push(InstallationsApiAdapter.getInstance().getSenderimos());
        calls.push(InstallationsApiAdapter.getInstance().getPetanca());
        calls.push(InstallationsApiAdapter.getInstance().getMotor());
        calls.push(InstallationsApiAdapter.getInstance().getPiscinas());
        calls.push(InstallationsApiAdapter.getInstance().getTenis());
        calls.push(InstallationsApiAdapter.getInstance().getVoleyPlaya());
        calls.push(InstallationsApiAdapter.getInstance().getPadel());
        calls.push(InstallationsApiAdapter.getInstance().getAtletismo());
        calls.push(InstallationsApiAdapter.getInstance().getBadminton());
        calls.push(InstallationsApiAdapter.getInstance().getEscalada());
        calls.push(InstallationsApiAdapter.getInstance().getFutbol3());
        calls.push(InstallationsApiAdapter.getInstance().getFutbolSala());
        calls.push(InstallationsApiAdapter.getInstance().getSalasDeportivas());
        calls.push(InstallationsApiAdapter.getInstance().getSkateBMX());
        calls.push(InstallationsApiAdapter.getInstance().getAparatos());
        calls.push(InstallationsApiAdapter.getInstance().getPistasPolideportivas());
        calls.push(InstallationsApiAdapter.getInstance().getTenisMesa());
        calls.push(InstallationsApiAdapter.getInstance().getFutbolPlaya());
        calls.push(InstallationsApiAdapter.getInstance().getFutbol());
        calls.push(InstallationsApiAdapter.getInstance().getPabellones());

        workoutsCall =  WorkoutsApiAdapter.getInstance().getWorkouts();
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

    @Override
    public void onDestroy() {
        if (error) {
            notificationManager.notify(0, notificationError);
        } else {
            notificationManager.notify(0, notificationSuccess);
        }
        super.onDestroy();
    }

    @Override
    public ComponentName startForegroundService(Intent service) {
        return super.startForegroundService(service);
    }

    private void updateDataBase() {
        calls.pop().enqueue(new InstallationsCallback(this));
    }

    @Override
    public void onDownloadError() {
        error = true;
    }

    @Override
    public void onCallFinish() {
        try {
            calls.pop().enqueue(new InstallationsCallback(this));
        } catch (EmptyStackException e) {
            // Workouts
            Call<Workouts> callWorkout = WorkoutsApiAdapter.getInstance().getWorkouts();
            callWorkout.enqueue(new WorkoutsCallback(this));
        }
    }
}
