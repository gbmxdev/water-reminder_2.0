package com.gbmxdev.waterreminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.EditText;


/*
Todo:
    *get time interval input from plaintext widget
    *call alarm manager with input
    configure alarm manager with time interval
    call method for notification based on new intent
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //this is suppose to run a method on intent creation
        /*
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                //Extra bundle is null
            } else {
                alarmSetter(getIntent());
            }

        }
        */
    }
    public void startCMD( View view )  {
        Intent intent = new Intent();
        EditText editText = (EditText) findViewById(R.id.editTextTime );
        String message = editText.getText().toString();
        int time = Integer.parseInt(message);

 //do stuff
        intent.putExtra("time", time);
        //startActivity(intent);
        alarmSetter(intent);

    }
    private void alarmSetter(Intent intent){
        int condition=intent.getIntExtra("time",0);
        //this is the first alarmmanager attempt
        /*
        Context context = this;
        int requestId = 0;


        PendingIntent pendingIntent =  PendingIntent.getService(context, requestId, intent,  PendingIntent.FLAG_NO_CREATE);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_HALF_HOUR, AlarmManager.INTERVAL_HALF_HOUR, pendingIntent);
        if (pendingIntent != null && alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }
         */
        if(condition== 15) {
             //Call the method
            createNotificationChannel();

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "Water Reminder")
                    .setSmallIcon(R.drawable.icon)
                    .setContentTitle("Water Reminder!")
                    .setContentText("Please have a glass of water.")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            // notificationId is a unique int for each notification that you must define
            notificationManager.notify(1, builder.build());

        } else {
            //method you want
            System.exit(0);
        }
    }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("Water Reminder", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    @Override
    protected void onNewIntent(Intent intent) {
        //doesn't fucking work
        super.onNewIntent(intent);
        alarmSetter(intent);
    }
}