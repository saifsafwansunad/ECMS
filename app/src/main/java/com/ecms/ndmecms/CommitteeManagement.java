package com.ecms.ndmecms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.ecms.ndmecms.Activity.ReportActivity;
import com.ecms.ndmecms.ui.ViewEmployeeActivity;
import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommitteeManagement extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AdminCommittee adapter;
    private ArrayList<Commitee> mahasiswaArrayList;
    TextView title;
    ImageView backarrow,dots, offline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("COMMITTEE MANAGEMENT");
        setContentView(R.layout.activity_committee_management);

        //addData();
        getSupportActionBar().hide();
//        Toolbar toolbartoAttend=(Toolbar)findViewById(R.id.commitee_toolbar);
//        toolbartoAttend.setTitle("Committee");
        title=findViewById(R.id.title);
        title.setText("Committee");
        ImageView backarrow=findViewById(R.id.imgBackArrow);
        recyclerView = findViewById(R.id.recycler_view);
        commiteeCall();

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //adapter = new AdminCommittee(mahasiswaArrayList);

        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CommitteeManagement.this);

        //recyclerView.setLayoutManager(layoutManager);

        //recyclerView.setAdapter(adapter);

        dots=findViewById(R.id.dots_report1);
        offline=findViewById(R.id.offline1);

        dots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), dots);

//                MenuPopupHelper menuHelper = new MenuPopupHelper(UserMessages.this, (MenuBuilder) popupMenu.getMenu(), dots);
//                menuHelper.setForceShowIcon(true);
//                menuHelper.show();

                /*  The below code in try catch is responsible to display icons*/
                try {
                    Field[] fields = popupMenu.getClass().getDeclaredFields();
                    for (Field field : fields) {
                        if ("mPopup".equals(field.getName())) {
                            field.setAccessible(true);
                            Object menuPopupHelper = field.get(popupMenu);
                            Class<?> classPopupHelper = Class.forName(menuPopupHelper.getClass().getName());
                            Method setForceIcons = classPopupHelper.getMethod("setForceShowIcon", boolean.class);
                            setForceIcons.invoke(menuPopupHelper, true);
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Inflating popup menu from popup_menu.xml file
                popupMenu.getMenuInflater().inflate(R.menu.main, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        // Toast message on menu item clicked
                        switch (menuItem.getItemId())
                        {


                            case R.id.profile_m:
                                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                                startActivity(intent);
                                return true;
                            case R.id.report:
                                Intent reportIntent = new Intent(getApplicationContext(), ReportActivity.class);
                                startActivity(reportIntent);
                                return true;
                            case R.id.action_privacy:
                                Intent privacyIntent = new Intent(getApplicationContext(), PrivacyPolicyActivity.class);
                                startActivity(privacyIntent);
                                return true;
                            case R.id.logout_m:
                                PreferenceUtils.savePassword(null, getApplicationContext());
                                PreferenceUtils.saveEmail(null, getApplicationContext());
                                PreferenceUtils.saveUid(null, getApplicationContext());
                                // MyService.count = 0;

                                //trying to stop the service
              /*  stopService = true;
                Intent stopIntent = new Intent(getApplicationContext(), MyService.class);
                stopIntent.putExtra("service", "yes");
                stopIntent.setAction("stopService");
                getApplicationContext().startService(stopIntent);*/
                                final ProgressDialog progressDoalog;
                                progressDoalog = new ProgressDialog(getApplicationContext());
                                progressDoalog.setMessage("Logging Out....");
                                progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                progressDoalog.show();

                                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                                startActivity(i);
                                finish();
                                return true;
                        }


                        return true;
                    }
                });
                // Showing the popup menu
                popupMenu.show();
            }
        });

        offline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewEmployeeActivity.class);
                startActivity(intent);
            }
        });
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
