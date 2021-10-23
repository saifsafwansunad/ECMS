package com.example.ecms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.ecms.Adapters.ToAttendMeetingsAdapter;
import com.example.ecms.ApiRequests.ToAttendMeetingRequest;
import com.example.ecms.ApiResponse.ToAttendMeetingResponse;
import com.example.ecms.ui.ReadyApproveAdapter;
import com.example.ecms.ui.ReadyToApprove;
import com.example.ecms.ui.ReadytoapproveMOdel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommitteeMeetings extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CommiteMeetingsAdapter adapter;
    private List<CommiteeMeetingModel> commiteeMeetingModels;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Committee Meetings");
        setContentView(R.layout.activity_committee_meetings);
        commiteeMeetingsParticular();

        //addData();

        recyclerView = findViewById(R.id.recycler_view);

       /* adapter = new CommiteMeetingsAdapter(commiteeMeetingModels);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CommitteeMeetings.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    */}

   /* void addData(){
        mahasiswaArrayList = new ArrayList<>();
        mahasiswaArrayList.add(new CommiteeMeetingModel("Workshop for Descrimination","Departmental","Workshop for Descrimination","Start Date"));
        mahasiswaArrayList.add(new CommiteeMeetingModel("Workshop for Descrimination","Departmental","Workshop for Descrimination","Start Date"));
        mahasiswaArrayList.add(new CommiteeMeetingModel("Workshop for Descrimination","Departmental","Workshop for Descrimination","Start Date"));
        mahasiswaArrayList.add(new CommiteeMeetingModel("Workshop for Descrimination","Departmental","Workshop for Descrimination","Start Date"));
        mahasiswaArrayList.add(new CommiteeMeetingModel("Workshop for Descrimination","Departmental","Workshop for Descrimination","Start Date"));
        mahasiswaArrayList.add(new CommiteeMeetingModel("Workshop for Descrimination","Departmental","Workshop for Descrimination","Start Date"));
        mahasiswaArrayList.add(new CommiteeMeetingModel("Workshop for Descrimination","Departmental","Workshop for Descrimination","Start Date"));

    }*/


    public final void commiteeMeetingsParticular() {
        ToAttendMeetingRequest toAttendMeetingRequest = new ToAttendMeetingRequest();
        toAttendMeetingRequest.setuId(PreferenceUtils.getUid(CommitteeMeetings.this));
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(CommitteeMeetings.this);
        progressDialog.setMessage("Please Wait....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        View contextView = findViewById(android.R.id.content);

        if (!DetectConnection.checkInternetConnection(this)) {
            progressDialog.dismiss();
            Snackbar snackbar = Snackbar.make(contextView, "Sorry, No Internet", Snackbar.LENGTH_LONG);
            snackbar.setAction("Retry", new CommitteeMeetings.TryAgainListener());

            snackbar.show();
        } else {
            Call<List<CommiteeMeetingModel>> loginResponseCall = ApiClient.getUserService().toPaticularCommitee(AdminCommittee.meetingDetails);


            try {
                loginResponseCall.enqueue(new Callback<List<CommiteeMeetingModel>>() {
                    @Override
                    public void onResponse(Call<List<CommiteeMeetingModel>> call, Response<List<CommiteeMeetingModel>> response) {

                        if (response.isSuccessful()) {
//        Toast.makeText(AppointmentsActivity.this, "appointments got", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                         //   Toast.makeText(CommitteeMeetings.this, "success", Toast.LENGTH_SHORT).show();
                            if (response.body() != null && response.body().size() > 0)

                                commiteeMeetingModels = response.body();
                                recyclerView.setLayoutManager(new LinearLayoutManager(CommitteeMeetings.this));
                                recyclerView.setAdapter(new CommiteMeetingsAdapter(CommitteeMeetings.this,commiteeMeetingModels));
                                Log.d("key of the message", "appointments are.... " + response.body());


                        } else {
                            progressDialog.dismiss();
                          //  Toast.makeText(CommitteeMeetings.this, "appointments Failed", Toast.LENGTH_LONG).show();

                        }

                    }


                    @Override
                    public void onFailure(Call<List<CommiteeMeetingModel>> call, Throwable t) {

                        progressDialog.dismiss();
                        Toast.makeText(CommitteeMeetings.this, "Not Found", Toast.LENGTH_LONG).show();
                        //Toast.makeText(CommitteeMeetings.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

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
            if (!DetectConnection.checkInternetConnection(CommitteeMeetings.this)) {
                Snackbar snackbar = Snackbar.make(contextView, "Sorry, you're offline", Snackbar.LENGTH_LONG);
                snackbar.setAction("Retry",new CommitteeMeetings.TryAgainListener());
                snackbar.show();
            } else {

                Snackbar.make(contextView, "Internet Connected", Snackbar.LENGTH_SHORT)
                        .show();
            }

        }
    }

}

