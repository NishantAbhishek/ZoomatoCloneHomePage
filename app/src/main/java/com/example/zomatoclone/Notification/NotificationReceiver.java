package com.example.zomatoclone.Notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.zomatoclone.R;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(!intent.getAction().equals("AlarmOn")){

        }else{
            String message = intent.getStringExtra("toastMessage");
            Toast.makeText(context,message,Toast.LENGTH_LONG).show();

            Intent startService = new Intent(context,Alarm.class);
            startService.putExtra("Message","Service is running");
            if(Build.VERSION.SDK_INT>Build.VERSION_CODES.O){
                context.startForegroundService(startService);
            }else{
                context.startService(startService);
            }

        }

    }
}
