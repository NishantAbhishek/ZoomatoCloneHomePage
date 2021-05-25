package com.example.zomatoclone.Notification;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
        Log.e("ThreadAc",":- "+Thread.currentThread().getName());
        findViewById(R.id.sendNotification).setOnClickListener(v->sendAlarm());
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

    public void sendBroadCastNotification() {
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

    public void sendNotificationBigText(){
        String title = ((EditText)findViewById(R.id.ed_title1)).getText().toString();
        String title2 = ((EditText)findViewById(R.id.ed_title2)).getText().toString();

        Intent intent = new Intent(this,NotificationActivity.class);
        PendingIntent content = PendingIntent.getActivity(this,0,intent,0);

        Intent broadcastIntent = new Intent(this,NotificationReceiver.class);
        broadcastIntent.putExtra("toastMessage", "message");
        PendingIntent actionIntent = PendingIntent.getBroadcast(this,0,broadcastIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(),R.drawable.a);

        Notification notification = new NotificationCompat.Builder(this,App.CHANNEL_ID_1)
                .setSmallIcon(R.drawable.a)
                .setLargeIcon(largeIcon)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("this is life this is life this is life " +
                        "this is life this is life this is life this is life").setSummaryText("Summary").setBigContentTitle("Big title"))
                .setContentTitle(title)
                .setContentText(title2)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.BLUE)
                .setContentIntent(content)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .addAction(R.mipmap.ic_launcher, "Toast", actionIntent)
                .build();
        notificationManagerCompat.notify(1, notification);
    }

    public void sendNotificationInbox(){
        String title = ((EditText)findViewById(R.id.ed_title1)).getText().toString();
        String title2 = ((EditText)findViewById(R.id.ed_title2)).getText().toString();
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(),R.drawable.b);

        Notification notification = new NotificationCompat.Builder(this,App.CHANNEL_ID_1)
                .setContentText(title2)
                .setContentTitle(title)
                .setLargeIcon(largeIcon)
                .setStyle(new NotificationCompat.InboxStyle()
                .addLine("Line")
                .addLine("Line")
                .addLine("Line")
                .addLine("Line"))
                .setColor(Color.BLUE)
                .setSmallIcon(R.drawable.a)
                .setPriority(NotificationCompat.PRIORITY_HIGH).build();
        notificationManagerCompat.notify(1,notification);
        Log.e(TAG,"SendNotification");
    }
    public void sendNotificationBigpicture(){
        String title = ((EditText)findViewById(R.id.ed_title1)).getText().toString();
        String title2 = ((EditText)findViewById(R.id.ed_title2)).getText().toString();
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(),R.drawable.b);

        Notification notification = new NotificationCompat.Builder(this,App.CHANNEL_ID_1)
                .setContentText(title2)
                .setContentTitle(title)
                .setLargeIcon(largeIcon)
                .setStyle(new NotificationCompat.BigPictureStyle()
                .bigPicture(largeIcon)
                .bigLargeIcon(null))
                .setSmallIcon(R.drawable.a)
                .setPriority(NotificationCompat.PRIORITY_HIGH).build();

        notificationManagerCompat.notify(1,notification);
        Log.e(TAG,"SendNotification");
    }
    public void sendNotificationMediaStyle(){
        String title = ((EditText)findViewById(R.id.ed_title1)).getText().toString();
        String title2 = ((EditText)findViewById(R.id.ed_title2)).getText().toString();
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(),R.drawable.b);

        Notification notification = new NotificationCompat.Builder(this,App.CHANNEL_ID_1)
                .setContentText(title2)
                .setContentTitle(title)
                .setLargeIcon(largeIcon)
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle())
                .setSmallIcon(R.drawable.a)
                .setPriority(NotificationCompat.PRIORITY_HIGH).build();

        notificationManagerCompat.notify(1,notification);
        Log.e(TAG,"SendNotification");
    }

    public void sendNotificationMediaStyleN(){
        String title = ((EditText)findViewById(R.id.ed_title1)).getText().toString();
        String title2 = ((EditText)findViewById(R.id.ed_title2)).getText().toString();
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(),R.drawable.b);

        Notification notification = new NotificationCompat.Builder(this,App.CHANNEL_ID_1)
                .setContentText(title2)
                .setContentTitle(title)
                .setLargeIcon(largeIcon)
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine("Line")
                        .addLine("Line")
                        .addLine("Line")
                        .addLine("Line"))
                .setColor(Color.BLUE)
                .setSmallIcon(R.drawable.a)
                .setPriority(NotificationCompat.PRIORITY_HIGH).build();
        for (int i = 0; i <8 ; i++) {
            notificationManagerCompat.notify(i,notification);
        }
        Log.e(TAG,"SendNotification");
    }



    public void sendAlarm(){
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this,NotificationReceiver.class);
        intent.putExtra("toastMessage", "Service Message");
        intent.setAction("AlarmOn");

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);

        long timeAtButtonClick= System.currentTimeMillis();
        long tenSecond = 1000*10;

        alarmManager.set(AlarmManager.RTC_WAKEUP,timeAtButtonClick+tenSecond,pendingIntent);


        //Create pending intent with the same id and appropriate intent FLAG.
        //Cancel that pending intent.
        //Cancel the alarm using alarm manager.
//        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(this,0,intent,0);
//        alarmManager.cancel(pendingIntent2);


    }

}