package com.gbmxdev.waterreminder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class YourService extends Service {
    Alarm alarm = new Alarm();

    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        alarm.setAlarm(this, intent);
        return START_STICKY;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        alarm.setAlarm(this, intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
