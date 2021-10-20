package com.example.ecms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecms.Adapters.ManageAdapter;
import com.example.ecms.Adapters.ToAttendMeetingsAdapter;
import com.example.ecms.ApiRequests.ToAttendMeetingRequest;
import com.example.ecms.ApiResponse.ToAttendMeetingResponse;
import com.example.ecms.Models.ManageModel;
import com.example.ecms.Models.ToattendMeetingsModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ToAttendMeetingActivity extends AppCompatActivity {
    TextView tvNoMeetings;
RecyclerView recyclerViewToAttend;
private ToAttendMeetingsAdapter toAttendMeetingsAdapter;
    private ArrayList<ToattendMeetingsModel> toattendMeetingsModelslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_attend_meeting);
//        addData();
        tvNoMeetings = findViewById(R.id.no_Meetings_tv);
        recyclerViewToAttend = findViewById(R.id.to_attend_meetings_recyclerview);
        toAttendMeetingsCall();
//        toAttendMeetingsAdapter = new ToAttendMeetingsAdapter(toattendMeetingsModelslist);
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
        toAttendMeetingRequest.setuId(PreferenceUtils.getUid(ToAttendMeetingActivity.this));
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
                                recyclerViewToAttend.setLayoutManager(new LinearLayoutManager(ToAttendMeetingActivity.this));
                                recyclerViewToAttend.setAdapter(new ToAttendMeetingsAdapter(ToAttendMeetingActivity.this,meetingsList));
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
                        Toast.makeText(ToAttendMeetingActivity.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

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