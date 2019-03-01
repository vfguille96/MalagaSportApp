package com.bilalmoreno.malagasport.data.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import java.util.Calendar;

public class MalagaSportServiceBroadcastReceiver extends BroadcastReceiver {
    private static final int REQUEST_SERVICE = 1487;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null && intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            setServiceAlarm(context);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(new Intent(context, MalagaSportService.class));
            } else {
                context.startService(new Intent(context, MalagaSportService.class));
            }
        }
    }

    /**
     * Método que crea una alarma en el sistema para que despierte al servicio de actualización de
     * la base de datos cada 24 horas.
     * @param context
     */
    private void setServiceAlarm(Context context) {
        Intent intent = new Intent(context, MalagaSportServiceBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, REQUEST_SERVICE, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }
}
