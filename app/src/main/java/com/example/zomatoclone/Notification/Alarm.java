package com.example.zomatoclone.Notification;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.JobIntentService;
import androidx.core.app.NotificationCompat;

import com.example.zomatoclone.R;

public class Alarm extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Bitmap largeIcon = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.b);
        Notification notification = new NotificationCompat.Builder(getApplicationContext(),App.CHANNEL_ID_1)
                .setContentText("title2")
                .setContentTitle("title")
                .setLargeIcon(largeIcon)
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle())
                .setSmallIcon(R.drawable.a)
                .setPriority(NotificationCompat.PRIORITY_HIGH).build();

        NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        //manager.notify(1,notification);
        startForeground(1,notification);
        Log.e("--","");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                String message = intent.getStringExtra("Message");
                Toast.makeText(getApplicationContext(),"Service Started",Toast.LENGTH_LONG).show();
                Log.e("Thread",":- "+Thread.currentThread().getName());
                stopSelf();
                Looper.loop();
            }
        });
        t.start();
        return START_NOT_STICKY;
    }
}
