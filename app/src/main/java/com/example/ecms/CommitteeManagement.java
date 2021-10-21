package com.example.ecms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import com.example.ecms.Models.Admin;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommitteeManagement extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AdminCommittee adapter;
    private ArrayList<Commitee> mahasiswaArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("COMMITTEE MANAGEMENT");
        setContentView(R.layout.activity_committee_management);

        //addData();
        getSupportActionBar().hide();
        Toolbar toolbartoAttend=(Toolbar)findViewById(R.id.commitee_toolbar);
        toolbartoAttend.setTitle("Committee");
        recyclerView = findViewById(R.id.recycler_view);
        commiteeCall();

        //adapter = new AdminCommittee(mahasiswaArrayList);

        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CommitteeManagement.this);

        //recyclerView.setLayoutManager(layoutManager);

        //recyclerView.setAdapter(adapter);
    }

    void addData(){
        mahasiswaArrayList = new ArrayList<>();
        mahasiswaArrayList.add(new Commitee("Broader Management","Show Meetings"));
        mahasiswaArrayList.add(new Commitee("Broader Management","Show Meetings"));
        mahasiswaArrayList.add(new Commitee("Broader Management","Show Meetings"));
        mahasiswaArrayList.add(new Commitee("Broader Management","Show Meetings"));
        mahasiswaArrayList.add(new Commitee("Broader Management","Show Meetings"));
        mahasiswaArrayList.add(new Commitee("Broader Management","Show Meetings"));




    }

    public final void commiteeCall() {
        ToComitteeRequest toComitteeRequest = new ToComitteeRequest();
        toComitteeRequest.setEmpid(PreferenceUtils.getUid(CommitteeManagement.this));
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(CommitteeManagement.this);
        progressDialog.setMessage("Please Wait....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        View contextView = findViewById(android.R.id.content);

        if (!DetectConnection.checkInternetConnection(this)) {
            progressDialog.dismiss();
            Snackbar snackbar = Snackbar.make(contextView, "Sorry, No Internet", Snackbar.LENGTH_LONG);
            snackbar.setAction("Retry", new CommitteeManagement.TryAgainListener());

            snackbar.show();
        } else {
            Call<List<ComitteeResponse>> loginResponseCall = ApiClient.getUserService().tocommitee(toComitteeRequest.getEmpid());


            try {
                loginResponseCall.enqueue(new Callback<List<ComitteeResponse>>() {
                    @Override
                    public void onResponse(Call<List<ComitteeResponse>> call, Response<List<ComitteeResponse>> response) {

                        if (response.isSuccessful()) {
//        Toast.makeText(AppointmentsActivity.this, "appointments got", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                            if (response.body() != null && response.body().size() > 0) {
                               // tvNoMeetings.setVisibility(View.GONE);
                                //recyclerViewToAttend.setVisibility(View.VISIBLE);
                                List<ComitteeResponse> comitteeResponses = response.body();
                                recyclerView.setLayoutManager(new LinearLayoutManager(CommitteeManagement.this));
                                recyclerView.setAdapter(new AdminCommittee(CommitteeManagement.this,comitteeResponses));
                                Log.d("key of the message", "appointments are.... " + response.body());
                            }
                            else {
                                recyclerView.setVisibility(View.GONE);
                              //  tvNoMeetings.setVisibility(View.VISIBLE);

                            }

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(CommitteeManagement.this, "appointments Failed", Toast.LENGTH_LONG).show();

                        }

                    }


                    @Override
                    public void onFailure(Call<List<ComitteeResponse>> call, Throwable t) {

                        progressDialog.dismiss();
                        Toast.makeText(CommitteeManagement.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

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
            if (!DetectConnection.checkInternetConnection(CommitteeManagement.this)) {
                Snackbar snackbar = Snackbar.make(contextView, "Sorry, you're offline", Snackbar.LENGTH_LONG);
                snackbar.setAction("Retry",new CommitteeManagement.TryAgainListener());
                snackbar.show();
            } else {

                Snackbar.make(contextView, "Internet Connected", Snackbar.LENGTH_SHORT)
                        .show();
            }

        }
    }


}
