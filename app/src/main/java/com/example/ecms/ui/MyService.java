package com.example.ecms.ui;

import android.app.ProgressDialog;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.ecms.ApiClient;
import com.example.ecms.ApiRequests.ToAttendMeetingRequest;
import com.example.ecms.ApiResponse.ToAttendMeetingResponse;
import com.example.ecms.DetectConnection;
import com.example.ecms.LoginActivity;
import com.example.ecms.PreferenceUtils;
import com.example.ecms.ToAttendMeetingActivity;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyService extends Service {
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


