package com.example.ecms.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecms.Adapters.TaskStatusAdapter;
import com.example.ecms.ApiClient;
import com.example.ecms.ApiResponse.ReportResponse;
import com.example.ecms.DetectConnection;
import com.example.ecms.LoginActivity;
import com.example.ecms.LoginRequest;
import com.example.ecms.MainActivity;
import com.example.ecms.Models.TaskStatusObjects;
import com.example.ecms.PreferenceUtils;
import com.example.ecms.R;
import com.example.ecms.UserLoginResponse;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportActivity extends AppCompatActivity {

    Spinner ContactType;
    EditText report_subject, report_message;
    Button report_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        ContactType = findViewById(R.id.contact_type_spinner);
        report_subject = findViewById(R.id.report_subject);
        report_message = findViewById(R.id.report_message);
        report_submit = findViewById(R.id.report_submit);

        ArrayAdapter<String> contentType = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.ContentType));
        ContactType.setAdapter(contentType);

        report_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (report_subject.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Subject field is mandatory", Toast.LENGTH_SHORT).show();
                }else if (report_message.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Message field is mandatory", Toast.LENGTH_SHORT).show();
                }else {
                        String contactType_sp = (String) ContactType.getSelectedItem();
                        String report_subject_et = report_subject.getText().toString();
                        String report_message_et = report_message.getText().toString();
                        String uid = PreferenceUtils.getUid(getApplicationContext());

                        Log.d("Report1", contactType_sp);
                        Log.d("Report1", report_subject_et);
                        Log.d("Report1", report_message_et);
                        Log.d("Report1", uid);

                        reportIssueCall(uid,contactType_sp, report_subject_et, report_message_et);

                }

            }
        });
    }

    private void reportIssueCall(String uid,String contactType_sp, String report_subject_et, String report_message_et) {


        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(ReportActivity.this);
        progressDoalog.setMessage("Submitting....");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();



        View contextView = findViewById(android.R.id.content);

        if (!DetectConnection.checkInternetConnection(this)) {
            progressDoalog.dismiss();
            Snackbar snackbar =Snackbar.make(contextView, "Sorry, No Internet", Snackbar.LENGTH_LONG);
            snackbar.setAction("Retry", new TryAgainListener());

            snackbar.show();
        } else {
            Call<List<ReportResponse>> reportIssueCall = ApiClient.getUserService().reportIssue(uid, contactType_sp, report_subject_et, report_message_et);
            try {

                reportIssueCall.enqueue(new Callback<List<ReportResponse>>() {
                    @Override
                    public void onResponse(Call<List<ReportResponse>> call, Response<List<ReportResponse>> response) {
                        progressDoalog.dismiss();
                        if (response.isSuccessful()) {

                            List<ReportResponse>dataArrayList = response.body();
                            for (int i = 0; i < dataArrayList.size(); i++) {
                                ReportResponse report_msg = dataArrayList.get(i);
                                final Dialog dialog = new Dialog(ReportActivity.this);
                                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                dialog.setCancelable(false);
                                dialog.setContentView(R.layout.report_dialog_layout);
                                TextView textViewReport=(TextView)dialog.findViewById(R.id.report_dialog_tv);
                                Button buttonReport=(Button)dialog.findViewById(R.id.report_dialog_btn);
textViewReport.setText(report_msg.getMessage());
buttonReport.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        dialog.dismiss();
        Intent reportIntent = new Intent(ReportActivity.this, MainActivity.class);
        startActivity(reportIntent);
    }
});
                                dialog.show();



//                                Toast.makeText(ReportActivity.this, report_msg.getMessage(), Toast.LENGTH_LONG).show();



                                Log.d("Report1", "The message " + report_msg.getMessage());

                            }


                        }
                    }

                    @Override
                    public void onFailure(Call<List<ReportResponse>> call, Throwable t) {
                        progressDoalog.dismiss();

                        Toast.makeText(ReportActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
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
            if (!DetectConnection.checkInternetConnection(ReportActivity.this)) {
                Snackbar snackbar =Snackbar.make(contextView, "Sorry, you're offline", Snackbar.LENGTH_LONG);
                snackbar.setAction("Retry", new TryAgainListener());
                snackbar.show();
            }
            else {

                Snackbar.make(contextView, "Internet Connected", Snackbar.LENGTH_SHORT)
                        .show();
            }

        }
    }

}