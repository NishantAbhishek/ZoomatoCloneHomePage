package com.example.zomatoclone.Notification;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.zomatoclone.R;
public class NotificationActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        notificationManagerCompat = NotificationManagerCompat.from(this);
        findViewById(R.id.sendNotification).setOnClickListener(v->sendNotification());
    }

    public void sendNotification() {
        String title = ((EditText)findViewById(R.id.ed_title1)).getText().toString();
        String title2 = ((EditText)findViewById(R.id.ed_title2)).getText().toString();

        Notification notification = new NotificationCompat.Builder(this,App.CHANNEL_ID_1)
                .setContentText(title2)
                .setContentTitle(title)
                .setColor(Color.BLUE)
                .setSmallIcon(R.drawable.a)
                .setPriority(NotificationCompat.PRIORITY_HIGH).build();
        notificationManagerCompat.notify(1,notification);
        Log.e(TAG,"SendNotification");
    }

    private static final String TAG = "NotificationActivity";

    public void sendBroadCastNotification(){
        String title = ((EditText)findViewById(R.id.ed_title1)).getText().toString();
        String title2 = ((EditText)findViewById(R.id.ed_title2)).getText().toString();

        Intent intent = new Intent(this,NotificationActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,0,intent,0);

        Intent broadcastIntent = new Intent(this, NotificationReceiver.class);
        broadcastIntent.putExtra("toastMessage", "message");
        PendingIntent actionIntent = PendingIntent.getBroadcast(this,0,broadcastIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this,App.CHANNEL_ID_1)
                .setSmallIcon(R.drawable.a)
                .setContentTitle(title)
                .setContentText(title2)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.BLUE)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .addAction(R.mipmap.ic_launcher, "Toast", actionIntent)
                .build();
        notificationManagerCompat.notify(1, notification);
    }
}