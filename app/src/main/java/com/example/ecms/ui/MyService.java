package com.example.ecms.ui;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.example.ecms.Adapters.ToAttendMeetingsAdapter;
import com.example.ecms.ApiClient;
import com.example.ecms.ApiRequests.ToAttendMeetingRequest;
import com.example.ecms.ApiResponse.ToAttendMeetingResponse;
import com.example.ecms.AttendMeetingDetailsActivity;
import com.example.ecms.DetectConnection;
import com.example.ecms.LoginActivity;
import com.example.ecms.MainActivity;
import com.example.ecms.Notification;
import com.example.ecms.PreferenceUtils;
import com.example.ecms.ToAttendMeetingActivity;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyService extends Service {
    public static final String MY_SERVICE = "it.unibz.bluedroid.bluetooth.service.MY_SERVICE";

    private String CHANNEL_ID = "My Notification";
    private Timer timer;
    private TimerTask task;

    protected Handler handler;
    public static int count = 0;
    private  int prevSize;

    public MyService() {
    }

    @Override
    public void onDestroy() {
        timer.cancel();
        task.cancel();
        Log.d("onCreate() is stopeed.","" );

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

//        Log.d("serviceTag", intent.getAction());
//        Log.d("serviceTag", intent.getStringExtra("service"));
        if(MainActivity.stopService){
            stopForeground(true);
            stopSelf();
        }else {
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
        }


        return super.onStartCommand(intent, flags, startId);
    }



    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Intent restartServiceIntent = new Intent(getApplicationContext(),this.getClass());
        restartServiceIntent.setPackage(getPackageName());
        try{
            startService(restartServiceIntent);

        }catch (IllegalStateException e){
            Log.d("illegalstate", "");


            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(restartServiceIntent);
            }
            else {
                startService(restartServiceIntent);
            }
        }
        toAttendMeetingsCall();

        super.onTaskRemoved(rootIntent);
    }



    public final void toAttendMeetingsCall() {
        ToAttendMeetingRequest toAttendMeetingRequest = new ToAttendMeetingRequest();
        toAttendMeetingRequest.setuId(PreferenceUtils.getUid(getApplicationContext()));

        Log.d("key of the message", "size ");
//        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();




        Call<List<ToAttendMeetingResponse>> loginResponseCall = ApiClient.getUserService().toAttendMeeting(toAttendMeetingRequest.getuId());


            try {
                loginResponseCall.enqueue(new Callback<List<ToAttendMeetingResponse>>() {
                    @Override
                    public void onResponse(Call<List<ToAttendMeetingResponse>> call, Response<List<ToAttendMeetingResponse>> response) {

                        if (response.isSuccessful()) {
                            if (response.body() != null && response.body().size() > 0) {
                                List<ToAttendMeetingResponse> meetingsList = response.body();
                                int size=meetingsList.size();
                                Log.d("key of the message", "size " + size);
//                                Toast.makeText(MyService.this, "size " + size, Toast.LENGTH_SHORT).show();


                                //// this is notification implementation
                                if(count == 0 ){
                                    prevSize = size;
                                    count=count+1;
                                }else {
                                    if(size > prevSize){
                                        prevSize = size;
                                        ToAttendMeetingResponse toAttendMeetingResponse1 = meetingsList.get(meetingsList.size()-1);
                                        //// this is notification implementation
                                        ToAttendMeetingsAdapter.meetingDetails = toAttendMeetingResponse1;
                                        Intent intentNotification = new Intent(MyService.this, AttendMeetingDetailsActivity.class);
                                        PendingIntent pendingIntent = PendingIntent.getActivity(MyService.this, 0, intentNotification, PendingIntent.FLAG_UPDATE_CURRENT);

                                        NotificationCompat.Builder builderNotificationCompat = new NotificationCompat.Builder(MyService.this, CHANNEL_ID)
                                                .setContentIntent(pendingIntent)
                                                .setContentTitle(toAttendMeetingResponse1.getMeetingType())
                                                .setContentText(toAttendMeetingResponse1.getTitle())
                                                .setAutoCancel(true)
                                                .setSmallIcon(android.R.drawable.btn_star_big_on);

                                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                                        NotificationChannel notificationChannel = null;
                                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                            notificationChannel = new NotificationChannel(CHANNEL_ID, "Notification", NotificationManager.IMPORTANCE_DEFAULT);
                                        }
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                            notificationManager.createNotificationChannel(notificationChannel);
                                        }
                                        notificationManager.notify(0,builderNotificationCompat.build());
                                    }
                                }



                                //upto here



                            }

                        }
                    }




                    @Override
                    public void onFailure(Call<List<ToAttendMeetingResponse>> call, Throwable t) {

//                        Toast.makeText(MyService.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                    }
                });

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

    public boolean stopService(Intent name) {
        // TODO Auto-generated method stub
        timer.cancel();
        task.cancel();
        return super.stopService(name);
    }
    }


