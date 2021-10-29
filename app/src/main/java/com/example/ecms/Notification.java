package com.example.ecms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Notification extends AppCompatActivity {
    private String CHANNEL_ID = "My Notification";

    Button notification_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        notification_send=findViewById(R.id.button);


        notification_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNotification = new Intent(Notification.this, ToAttendMeetingActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(Notification.this, 0, intentNotification, PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Builder builderNotificationCompat = new NotificationCompat.Builder(Notification.this, CHANNEL_ID)
                        .setContentIntent(pendingIntent)
                        .setContentTitle("My Notification")
                        .setContentText("My Notification text here.")
                        .setSmallIcon(android.R.drawable.btn_star_big_on);

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "This is my first Notification", NotificationManager.IMPORTANCE_DEFAULT);
                notificationManager.createNotificationChannel(notificationChannel);
                notificationManager.notify(0,builderNotificationCompat.build());
            }
        });
    }
}