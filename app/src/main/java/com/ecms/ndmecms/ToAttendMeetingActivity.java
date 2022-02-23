package com.ecms.ndmecms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.ecms.ndmecms.Activity.ReportActivity;
import com.ecms.ndmecms.Adapters.ToAttendMeetingsAdapter;
import com.ecms.ndmecms.ApiRequests.ToAttendMeetingRequest;
import com.ecms.ndmecms.ApiResponse.ToAttendMeetingResponse;
import com.ecms.ndmecms.Models.ToattendMeetingsModel;
import com.ecms.ndmecms.ui.ViewEmployeeActivity;
import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ToAttendMeetingActivity extends AppCompatActivity {
    TextView tvNoMeetings,title,mt_downloadAll;
    AlertDialog.Builder builder;

    RecyclerView recyclerViewToAttend;
private ToAttendMeetingsAdapter toAttendMeetingsAdapter;
    private ArrayList<ToattendMeetingsModel> toattendMeetingsModelslist;
    ImageView dots,offline;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout. activity_to_attend_meeting);

//        addData();
//        getSupportActionBar().hide();
       /* Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Meetings");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_baseline_keyboard_backspace_24);*/
getSupportActionBar().hide();
        tvNoMeetings = findViewById(R.id.no_Meetings_tv);
        title=findViewById(R.id.title);
        title.setText("Meetings");
        builder = new AlertDialog.Builder(this);
        mt_downloadAll = findViewById(R.id.mt_downloadAll);

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