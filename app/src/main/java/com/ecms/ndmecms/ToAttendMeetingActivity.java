package com.ecms.ndmecms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ecms.ndmecms.Adapters.ToAttendMeetingsAdapter;
import com.ecms.ndmecms.ApiRequests.ToAttendMeetingRequest;
import com.ecms.ndmecms.ApiResponse.ToAttendMeetingResponse;
import com.ecms.ndmecms.Models.ToattendMeetingsModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ToAttendMeetingActivity extends AppCompatActivity {
    TextView tvNoMeetings,title;
RecyclerView recyclerViewToAttend;
private ToAttendMeetingsAdapter toAttendMeetingsAdapter;
    private ArrayList<ToattendMeetingsModel> toattendMeetingsModelslist;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_attend_meeting);

//        addData();
//        getSupportActionBar().hide();
       /* Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Meetings");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_baseline_keyboard_backspace_24);*/

        tvNoMeetings = findViewById(R.id.no_Meetings_tv);
        title=findViewById(R.id.title);
        title.setText("Meetings");
        ImageView backarrow=findViewById(R.id.imgBackArrow);
        recyclerViewToAttend = findViewById(R.id.to_attend_meetings_recyclerview);
        toAttendMeetingsCall();

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });//        toAttendMeetingsAdapter = new ToAttendMeetingsAdapter(toattendMeetingsModelslist);
//        toAttendMeetingsAdapter = new ToAttendMeetingsAdapter(toattendMeetingsModelslist);

//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ToAttendMeetingActivity.this);

//        recyclerViewToAttend.setLayoutManager(layoutManager);

//        recyclerViewToAttend.setAdapter(toAttendMeetingsAdapter);
    }

    private void addData() {
        toattendMeetingsModelslist = new ArrayList<>();
        toattendMeetingsModelslist.add(new ToattendMeetingsModel("Mayoral Agenda","Council","Mayoral Agenda","3/24/2021 10:00 AM","false"));
        toattendMeetingsModelslist.add(new ToattendMeetingsModel("Mayoral Agenda","Council","Mayoral Agenda","3/24/2021 10:00 AM","false"));
        toattendMeetingsModelslist.add(new ToattendMeetingsModel("Mayoral Agenda","Council","Mayoral Agenda","3/24/2021 10:00 AM","false"));
        toattendMeetingsModelslist.add(new ToattendMeetingsModel("Mayoral Agenda","Council","Mayoral Agenda","3/24/2021 10:00 AM","false"));
        toattendMeetingsModelslist.add(new ToattendMeetingsModel("Mayoral Agenda","Council","Mayoral Agenda","3/24/2021 10:00 AM","false"));


    }

    public final void toAttendMeetingsCall() {
        ToAttendMeetingRequest toAttendMeetingRequest = new ToAttendMeetingRequest();
        try{
            if(PreferenceUtils.getUid(ToAttendMeetingActivity.this)!=null){
                toAttendMeetingRequest.setuId(PreferenceUtils.getUid(ToAttendMeetingActivity.this));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(ToAttendMeetingActivity.this);
        progressDialog.setMessage("Please Wait....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        View contextView = findViewById(android.R.id.content);

        if (!DetectConnection.checkInternetConnection(this)) {
            progressDialog.dismiss();
            Snackbar snackbar = Snackbar.make(contextView, "Sorry, No Internet", Snackbar.LENGTH_LONG);
            snackbar.setAction("Retry", new TryAgainListener());

            snackbar.show();
        } else {
            Call<List<ToAttendMeetingResponse>> loginResponseCall = ApiClient.getUserService().toAttendMeeting(toAttendMeetingRequest.getuId());


            try {
                loginResponseCall.enqueue(new Callback<List<ToAttendMeetingResponse>>() {
                    @Override
                    public void onResponse(Call<List<ToAttendMeetingResponse>> call, Response<List<ToAttendMeetingResponse>> response) {

                        if (response.isSuccessful()) {
//        Toast.makeText(AppointmentsActivity.this, "appointments got", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();

                            if (response.body() != null && response.body().size() > 0) {
                                tvNoMeetings.setVisibility(View.GONE);
                                recyclerViewToAttend.setVisibility(View.VISIBLE);
                                List<ToAttendMeetingResponse> meetingsList = response.body();
                                Collections.sort(meetingsList, Collections.reverseOrder());
                                List<ToAttendMeetingResponse> list;
                                if (meetingsList instanceof List)
                                    list = (List)meetingsList;
                                else
                                    list = new ArrayList(meetingsList);
                                //implemeting for date comparison
                           /*     Calendar toDayCalendar = Calendar.getInstance();
                                Date date1 = toDayCalendar.getTime();


                                Calendar tomorrowCalendar = Calendar.getInstance();
                                tomorrowCalendar.add(Calendar.DAY_OF_MONTH,1);
                                Date date2 = tomorrowCalendar.getTime();

// date1 is a present date and date2 is tomorrow date

                                if ( date1.compareTo(date2) < 0 ) {

                                    //  0 comes when two date are same,
                                    //  1 comes when date1 is higher then date2
                                    // -1 comes when date1 is lower then date2

                                }*/
                         /*       Calendar c = Calendar.getInstance();
                                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm aa");
                                String getCurrentDateTime = sdf.format(c.getTime());


                                    countMeetings=0;
                                for (int i = 0; i < meetingsList.size(); i++) {
                                   // if(meetingsList.get(i).startDate>=)
                                    String date_startDate= meetingsList.get(i).startDate;
                                    if(date_startDate.compareTo(getCurrentDateTime)<=0){
                                        countMeetings++;
                                    }
                                    //     userLoginResponse = meetingsList.get(i);

                                    //   data = dataArrayList.get(i).getId();
                                    //here we need to take countmeetings value and send it to mainactivty for showing there
                                }
                                Log.d("count meetings", "are" + countMeetings);*/

                                recyclerViewToAttend.setLayoutManager(new LinearLayoutManager(ToAttendMeetingActivity.this));
                                recyclerViewToAttend.setAdapter(new ToAttendMeetingsAdapter(ToAttendMeetingActivity.this,list));
                                Log.d("key of the message", "appointments are.... " + response.body());

                            }

                            else {
                                recyclerViewToAttend.setVisibility(View.GONE);
                                tvNoMeetings.setVisibility(View.VISIBLE);

                            }

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(ToAttendMeetingActivity.this, "appointments Failed", Toast.LENGTH_LONG).show();

                        }

                    }


                    @Override
                    public void onFailure(Call<List<ToAttendMeetingResponse>> call, Throwable t) {

                        progressDialog.dismiss();
                        Toast.makeText(ToAttendMeetingActivity.this, "Server Error " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                    }
                });

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
       // progressDialog.dismiss();
    }

    public class TryAgainListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // Code to undo the user's last action
            View contextView = findViewById(android.R.id.content);
            // Make and display Snackbar
            if (!DetectConnection.checkInternetConnection(ToAttendMeetingActivity.this)) {
                Snackbar snackbar = Snackbar.make(contextView, "Sorry, you're offline", Snackbar.LENGTH_LONG);
                snackbar.setAction("Retry",new  TryAgainListener());
                snackbar.show();
            } else {

                Snackbar.make(contextView, "Internet Connected", Snackbar.LENGTH_SHORT)
                        .show();
            }

        }
    }


   }