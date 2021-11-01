package com.example.ecms.ui;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.example.ecms.ApiClient;
import com.example.ecms.ApiRequests.ToAttendMeetingRequest;
import com.example.ecms.ApiResponse.ToAttendMeetingResponse;
import com.example.ecms.DetectConnection;
import com.example.ecms.LoginActivity;
import com.example.ecms.Notification;
import com.example.ecms.PreferenceUtils;
import com.example.ecms.ToAttendMeetingActivity;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyService extends Service {
    private String CHANNEL_ID = "My Notification";

    protected Handler handler;

    public MyService() {
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
       /* onTaskRemoved(intent);
        Toast.makeText(getApplicationContext(),"This is a Service running in Background",
                Toast.LENGTH_SHORT).show();
        toAttendMeetingsCall();*/
        ////startService(new Intent(getApplicationContext(), MyService.class));

        /*ToAttendMeetingActivity toAttendMeetingActivity=new ToAttendMeetingActivity();
        toAttendMeetingActivity.toAttendMeetingsCall();*/

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

// write your code to post content on server
                onTaskRemoved(intent);
                Toast.makeText(getApplicationContext(),"This is a Service running in Background",
                        Toast.LENGTH_SHORT).show();
            }
        },2000);
        return android.app.Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }



    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Intent restartServiceIntent = new Intent(getApplicationContext(),this.getClass());
        restartServiceIntent.setPackage(getPackageName());
        startService(restartServiceIntent);
        toAttendMeetingsCall();

        super.onTaskRemoved(rootIntent);
    }

    public final void toAttendMeetingsCall() {
        ToAttendMeetingRequest toAttendMeetingRequest = new ToAttendMeetingRequest();




        Call<List<ToAttendMeetingResponse>> loginResponseCall = ApiClient.getUserService().toAttendMeeting(String.valueOf(1));


            try {
                loginResponseCall.enqueue(new Callback<List<ToAttendMeetingResponse>>() {
                    @Override
                    public void onResponse(Call<List<ToAttendMeetingResponse>> call, Response<List<ToAttendMeetingResponse>> response) {

                        if (response.isSuccessful()) {
                            if (response.body() != null && response.body().size() > 0) {
                                List<ToAttendMeetingResponse> meetingsList = response.body();
                                int size=meetingsList.size();
                                Log.d("key of the message", "size " + size);

                                Intent intentNotification = new Intent(MyService.this, ToAttendMeetingActivity.class);
                                PendingIntent pendingIntent = PendingIntent.getActivity(MyService.this, 0, intentNotification, PendingIntent.FLAG_UPDATE_CURRENT);

                                NotificationCompat.Builder builderNotificationCompat = new NotificationCompat.Builder(MyService.this, CHANNEL_ID)
                                        .setContentIntent(pendingIntent)
                                        .setContentTitle("My Notification")
                                        .setContentText("My Notification text here.")
                                        .setSmallIcon(android.R.drawable.btn_star_big_on);

                                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                                NotificationChannel notificationChannel = null;
                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                    notificationChannel = new NotificationChannel(CHANNEL_ID, "This is my first Notification", NotificationManager.IMPORTANCE_DEFAULT);
                                }
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    notificationManager.createNotificationChannel(notificationChannel);
                                }
                                notificationManager.notify(0,builderNotificationCompat.build());




                            }

                        }
                    }




                    @Override
                    public void onFailure(Call<List<ToAttendMeetingResponse>> call, Throwable t) {

                        Toast.makeText(MyService.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                    }
                });

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }


