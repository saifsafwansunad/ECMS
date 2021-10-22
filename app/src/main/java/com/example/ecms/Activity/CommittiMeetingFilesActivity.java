package com.example.ecms.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.ecms.Adapters.CommitteeFilesAdapter;
import com.example.ecms.Adapters.ToAttendMeetingsAdapter;
import com.example.ecms.ApiClient;
import com.example.ecms.ApiRequests.CommitteeFilesRequest;
import com.example.ecms.ApiRequests.ToAttendMeetingRequest;
import com.example.ecms.ApiResponse.CommitteeFilesResponse;
import com.example.ecms.ApiResponse.ToAttendMeetingResponse;
import com.example.ecms.DetectConnection;
import com.example.ecms.PreferenceUtils;
import com.example.ecms.R;
import com.example.ecms.ToAttendMeetingActivity;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommittiMeetingFilesActivity extends AppCompatActivity {

    String folderPath;
    private String folderAddress = "Committees";
    RecyclerView cf_folder_rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_committi_meeting_files);
        cf_folder_rv = findViewById(R.id.cf_folder_rv);

        Intent intent = getIntent();

         folderPath = intent.getStringExtra("folderPath");
        folderAddress = folderAddress+ "/" + folderPath;

        getSupportActionBar().hide();
        Toolbar toolbartoAttend=(Toolbar)findViewById(R.id.commitee_folder_toolbar);
        toolbartoAttend.setTitle("Committee/" + folderPath);

        committifolders();
    }

    private final void committifolders() {

        CommitteeFilesRequest committeeFilesRequest = new CommitteeFilesRequest();
        committeeFilesRequest.setCFname(folderAddress);
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(CommittiMeetingFilesActivity.this);
        progressDialog.setMessage("Please Wait....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        View contextView = findViewById(android.R.id.content);

        if (!DetectConnection.checkInternetConnection(this)) {
            progressDialog.dismiss();
            Snackbar snackbar = Snackbar.make(contextView, "Sorry, No Internet", Snackbar.LENGTH_LONG);
            snackbar.setAction("Retry", new CommittiMeetingFilesActivity.TryAgainListener());

            snackbar.show();
        } else {
            Call<CommitteeFilesResponse> folderResponseCall = ApiClient.getUserService2().CFList(committeeFilesRequest.getCFname());


            try {
                folderResponseCall.enqueue(new Callback<CommitteeFilesResponse>() {
                    @Override
                    public void onResponse(Call<CommitteeFilesResponse> call, Response<CommitteeFilesResponse> response) {

                        if (response.isSuccessful()) {
//        Toast.makeText(AppointmentsActivity.this, "appointments got", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                            if (response.body() != null) {
                                cf_folder_rv.setVisibility(View.VISIBLE);
                                CommitteeFilesResponse Files = response.body();
                                List<CommitteeFilesResponse.FileSy> folderList = Files.getFileSys();
                                cf_folder_rv.setLayoutManager(new LinearLayoutManager(CommittiMeetingFilesActivity.this));
                                cf_folder_rv.setAdapter(new CommitteeFilesAdapter(folderList, CommittiMeetingFilesActivity.this));
                                //Log.d("key of the message", "appointments are.... " + response.body());
                            }
                            else {
                                Toast.makeText(CommittiMeetingFilesActivity.this, "No Files", Toast.LENGTH_SHORT).show();

                            }

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(CommittiMeetingFilesActivity.this, "Folder fetch Failed", Toast.LENGTH_LONG).show();


                        }

                    }


                    @Override
                    public void onFailure(Call<CommitteeFilesResponse> call, Throwable t) {

                        progressDialog.dismiss();
                        Toast.makeText(CommittiMeetingFilesActivity.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        Log.d("Reason", t.getLocalizedMessage() );

                    }
                });

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

    }

    public class TryAgainListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // Code to undo the user's last action
            View contextView = findViewById(android.R.id.content);
            // Make and display Snackbar
            if (!DetectConnection.checkInternetConnection(CommittiMeetingFilesActivity.this)) {
                Snackbar snackbar = Snackbar.make(contextView, "Sorry, you're offline", Snackbar.LENGTH_LONG);
                snackbar.setAction("Retry",new CommittiMeetingFilesActivity.TryAgainListener());
                snackbar.show();
            } else {

                Snackbar.make(contextView, "Internet Connected", Snackbar.LENGTH_SHORT)
                        .show();
            }

        }
    }

    public void onClickCalled(String folderName) {
        // Call another acitivty here and pass some arguments to it.
        folderAddress = folderAddress + "/" + folderName;
//        Toast.makeText(this, folderAddress, Toast.LENGTH_SHORT).show();
        committifolders();

    }

    @Override
    public void onBackPressed() {
        if(folderAddress.equals("Committee/" + folderPath)){
            super.onBackPressed();
        }else{
//            Toast.makeText(this, "You cant go back right now", Toast.LENGTH_SHORT).show();
            int lastIndexBackSlash = folderAddress.lastIndexOf('/');
            folderAddress = folderAddress.substring(0, lastIndexBackSlash);
//            Toast.makeText(this, folderAddress, Toast.LENGTH_SHORT).show();
            committifolders();
        }

    }
}