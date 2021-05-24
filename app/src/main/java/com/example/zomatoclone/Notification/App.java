package com.example.zomatoclone.Notification;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public class App extends Application {

    public static final String CHANNEL_ID_1 = "Scheduler";
    public static final String CHANNEL_ID_2 = "Scheduler";



    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationsChannels();
    }

    private void createNotificationsChannels(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID_1,"Scheduled", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Manage Everything");

            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);
        }
    }



}
