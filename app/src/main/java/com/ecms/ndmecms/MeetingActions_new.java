package com.ecms.ndmecms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ecms.ndmecms.Adapters.MeetingsViewpagerAdap;
import com.ecms.ndmecms.Adapters.ToAttendMeetingsAdapter;
import com.ecms.ndmecms.Adapters.ViewPagerCardsAdapter;
import com.ecms.ndmecms.ApiRequests.ToAttendMeetingRequest;
import com.ecms.ndmecms.ApiResponse.ToAttendMeetingResponse;
import com.ecms.ndmecms.ui.UserMessagesAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MeetingActions_new extends AppCompatActivity {
    public static int countMeetings,attendedMeetings;

    TabLayout tabLayoutMeetings;
    private ViewPager mViewPager,viewPagerMeeting;
    RecyclerView recyclerViewToAttend; 
    private ViewPagerCardsAdapter mCardAdapter;
    Context context;
    String detailsArray[];
    TextView title,attend_count;
    ImageView backarrow;

    String detailsArray2[] = {"sdg", "gsdg", "gdsgdsg","adasd"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_actions_new);

        getSupportActionBar().hide();
        title=findViewById(R.id.title);
        viewPagerMeeting=findViewById(R.id.meeting_viewPager);
        tabLayoutMeetings=findViewById(R.id.meeting_tablayout);
        attend_count=findViewById(R.id.attend_count);
        tabLayoutMeetings.addTab(tabLayoutMeetings.newTab().setText("Upcoming"));
        tabLayoutMeetings.addTab(tabLayoutMeetings.newTab().setText("Attended"));
        title.setText("Meeting Actions");

        backarrow=findViewById(R.id.imgBackArrow);

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerViewToAttend = findViewById(R.id.meetings_recyclerview);
        toAttendMeetingsCall();

        tabLayoutMeetings.setTabGravity(TabLayout.GRAVITY_FILL);

        final MeetingsViewpagerAdap messagesAdapter = new MeetingsViewpagerAdap(this, getSupportFragmentManager(), tabLayoutMeetings.getTabCount());
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


        mViewPager = (ViewPager) findViewById(R.id.home_viewpager);
        mViewPager.setClipToPadding(false);
        mViewPager.setPadding(40, 0, 40, 0);
        mViewPager.setOffscreenPageLimit(4);
        mCardAdapter = new ViewPagerCardsAdapter(getApplicationContext());


        if (LoginActivity.checkValue()) {
            detailsArray = new String[]{"asd"};
        } else {
            detailsArray = new String[]{"asd", "dasf", "gsdg", "asdas"};
        }
        for (int i = 0; i < detailsArray.length; i++) {

            mCardAdapter.addCardItemS(new CardItems(detailsArray, detailsArray2[i]));
        }

//        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);

        mViewPager.setAdapter(mCardAdapter);


//        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(4);

    }

    public final void toAttendMeetingsCall() {
        ToAttendMeetingRequest toAttendMeetingRequest = new ToAttendMeetingRequest();
        try{
            if(PreferenceUtils.getUid(MeetingActions_new.this)!=null){
                toAttendMeetingRequest.setuId(PreferenceUtils.getUid(MeetingActions_new.this));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(MeetingActions_new.this);
        progressDialog.setMessage("Please Wait....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        View contextView = findViewById(android.R.id.content);

        if (!DetectConnection.checkInternetConnection(this)) {
            progressDialog.dismiss();
            Snackbar snackbar = Snackbar.make(contextView, "Sorry, No Internet", Snackbar.LENGTH_LONG);
            snackbar.setAction("Retry", new MeetingActions_new.TryAgainListener());

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
                                List<ToAttendMeetingResponse> meetingsList = response.body();
                                int size=meetingsList.size();
                                Log.d("key of the message", "size " + size);
//                                Toast.makeText(MyService.this, "size " + size, Toast.LENGTH_SHORT).show();

                                Calendar c = Calendar.getInstance();
                                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm aa");
                                String getCurrentDateTime = sdf.format(c.getTime());
                                Date currentdate = null;
                                try {
                                    currentdate = new SimpleDateFormat("MM/dd/yyyy").parse(getCurrentDateTime);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }


                                countMeetings=0;
                                attendedMeetings=0;
                                for (int i = 0; i < meetingsList.size(); i++) {
                                    // if(meetingsList.get(i).startDate>=)
                                    String date_startDate= meetingsList.get(i).startDate;
                                    try {
                                        Date date1=new SimpleDateFormat("MM/dd/yyyy").parse(date_startDate);

                                        if(date1.after(currentdate) | date1.compareTo(currentdate) == 0){
                                            countMeetings++;
                                            Log.d("count meetings", "date1 is" + date1);


                                        }

                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }

                                    //     userLoginResponse = meetingsList.get(i);

                                    //   data = dataArrayList.get(i).getId();
                                    //here we need to take countmeetings value and send it to mainactivty for showing there
                                }
                                attendedMeetings=meetingsList.size()-countMeetings;
                                attend_count.setText(String.valueOf(countMeetings));
                                Log.d("count meetings", "are" + countMeetings);
                                Log.d("attendedMeetings", " attendedMeetings are" + attendedMeetings);

//                            Log.d("countMeeting2", String.valueOf(countMeetings));
                                //upto here


                            }



                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(MeetingActions_new.this, "Server Error", Toast.LENGTH_LONG).show();

                        }

                    }


                    @Override
                    public void onFailure(Call<List<ToAttendMeetingResponse>> call, Throwable t) {

                        progressDialog.dismiss();
                        Toast.makeText(MeetingActions_new.this, "Server Error " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

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
            if (!DetectConnection.checkInternetConnection(MeetingActions_new.this)) {
                Snackbar snackbar = Snackbar.make(contextView, "Sorry, you're offline", Snackbar.LENGTH_LONG);
                snackbar.setAction("Retry",new MeetingActions_new.TryAgainListener());
                snackbar.show();
            } else {

                Snackbar.make(contextView, "Internet Connected", Snackbar.LENGTH_SHORT)
                        .show();
            }

        }
    }


}