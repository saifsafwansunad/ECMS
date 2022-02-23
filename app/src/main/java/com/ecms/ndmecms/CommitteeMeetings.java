package com.ecms.ndmecms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.ecms.ndmecms.Activity.MeetingsViewpagerAdap1;
import com.ecms.ndmecms.Activity.ReportActivity;
import com.ecms.ndmecms.Adapters.MeetingsViewpagerAdap;
import com.ecms.ndmecms.ApiRequests.ToAttendMeetingRequest;
import com.ecms.ndmecms.ui.ViewEmployeeActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommitteeMeetings extends AppCompatActivity {
    private RecyclerView recyclerView;
    TextView commiteeMeeting;

    TabLayout tabLayoutMeetings;
    private ViewPager mViewPager,viewPagerMeeting;

    private CommiteMeetingsAdapter adapter;
    private List<CommiteeMeetingModel> commiteeMeetingModels;
    TextView title;
    ImageView backarrow, dots, offline;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_committee_meetings);
        getSupportActionBar().hide();
        title=findViewById(R.id.title);
        title.setText("Committee Meetings");
        ImageView backarrow=findViewById(R.id.imgBackArrow);
        commiteeMeeting=findViewById(R.id.commitee_meeting);
        commiteeMeetingsParticular();

        //addData();

        viewPagerMeeting=findViewById(R.id.meeting_viewPager);
        tabLayoutMeetings=findViewById(R.id.meeting_tablayout);

        tabLayoutMeetings.addTab(tabLayoutMeetings.newTab().setText("upcoming"));

        tabLayoutMeetings.addTab(tabLayoutMeetings.newTab().setText("attended"));

        tabLayoutMeetings.setTabGravity(TabLayout.GRAVITY_FILL);

        final MeetingsViewpagerAdap1 messagesAdapter = new MeetingsViewpagerAdap1(this, getSupportFragmentManager(), tabLayoutMeetings.getTabCount());
        viewPagerMeeting.setAdapter(messagesAdapter);

        viewPagerMeeting.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutMeetings));


        tabLayoutMeetings.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerMeeting.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        recyclerView = findViewById(R.id.recycler_view);

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

       /* adapter = new CommiteMeetingsAdapter(commiteeMeetingModels);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CommitteeMeetings.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

    */

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
                            commiteeMeeting.setVisibility(View.GONE);
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
                        // Toast.makeText(CommitteeMeetings.this, "Not Found", Toast.LENGTH_LONG).show();
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

