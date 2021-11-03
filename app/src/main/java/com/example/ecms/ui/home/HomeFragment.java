package com.example.ecms.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.ecms.Activity.CommittiMeetingFilesActivity;
import com.example.ecms.Adapters.MessagesAdapter;
import com.example.ecms.Adapters.MyAdapter;
import com.example.ecms.Adapters.ViewPagerCardsAdapter;
import com.example.ecms.ApiClient;
import com.example.ecms.ApiRequests.ToAttendMeetingRequest;
import com.example.ecms.ApiResponse.ToAttendMeetingResponse;
import com.example.ecms.CardItems;
import com.example.ecms.LoginActivity;
import com.example.ecms.PreferenceUtils;
import com.example.ecms.PublicActivity;
import com.example.ecms.R;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private ViewPager mViewPager;
    int page = 0;
    private ViewPagerCardsAdapter mCardAdapter;
    String detailsArray[];
    String detailsArray2[] = {"sdg", "gsdg", "gdsgdsg","adasd"};

Button buttonPublic,buttonPrivate;
    TabLayout tabLayout;
    ViewPager viewPager;

//    TabLayout tabLayoutMessages;
//    ViewPager viewPagerMessages;



    public static HomeFragment newInstance() {
        return new HomeFragment();
    }


    //    private ShadowTransformer mCardShadowTransformer;
//    private HomeViewModel homeViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        setRetainInstance(true);
        //here we trying to fetch meeting dates api and refresh in adapter
        //toAttendMeetingsCall();
        buttonPublic=(Button)root.findViewById(R.id.public_btn);
        buttonPrivate=(Button)root.findViewById(R.id.private_button);

        buttonPublic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CommittiMeetingFilesActivity.class);
                intent.putExtra("folderPath", "PUBLIC SECTOR" );
                intent.putExtra("folderAddress", "" );
                getContext().startActivity(intent);
            }
        });

        buttonPrivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CommittiMeetingFilesActivity.class);
                intent.putExtra("folderPath", "/PRIVATE SECTOR" );
                intent.putExtra("folderAddress", "" );
                getContext().startActivity(intent);
            }
        });

        mViewPager = (ViewPager)root.findViewById(R.id.home_viewpager);
        mViewPager.setClipToPadding(false);
        mViewPager.setPadding(40,0,40,0);
        mViewPager.setOffscreenPageLimit(4);
        mCardAdapter = new ViewPagerCardsAdapter(getContext());


if(LoginActivity.checkValue()){
    detailsArray = new String[]{"asd"};
}
else {
    detailsArray=new String[]{"asd", "dasf", "gsdg", "asdas"};
}
        for (int i = 0; i < detailsArray.length; i++) {

            mCardAdapter.addCardItemS(new CardItems(detailsArray, detailsArray2[i]));
        }

//        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);

        mViewPager.setAdapter(mCardAdapter);




//        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(4);


        tabLayout=(TabLayout)root.findViewById(R.id.calendar_tablayout);
        viewPager=(ViewPager)root.findViewById(R.id.calender_viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Organization Calendar"));
        tabLayout.addTab(tabLayout.newTab().setText("Your Calendar"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final MyAdapter adapter = new MyAdapter(getContext(),getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//        tabLayoutMessages=(TabLayout)root.findViewById(R.id.messages_tablayout);
//        viewPagerMessages=(ViewPager)root.findViewById(R.id.messages_viewPager);
//
//        tabLayoutMessages.addTab(tabLayoutMessages.newTab().setText("Organization Calendar"));
//        tabLayoutMessages.addTab(tabLayoutMessages.newTab().setText("Your Calendar"));
//        tabLayoutMessages.setTabGravity(TabLayout.GRAVITY_FILL);
//
//        final MessagesAdapter messagesAdapter = new MessagesAdapter(getContext(),getFragmentManager (), tabLayoutMessages.getTabCount());
//        viewPagerMessages.setAdapter(messagesAdapter);
//
//        viewPagerMessages.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutMessages));
//
//        tabLayoutMessages.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPagerMessages.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });


        return root;
    }


/*
    public final void toAttendMeetingsCall() {
        ToAttendMeetingRequest toAttendMeetingRequest = new ToAttendMeetingRequest();
        toAttendMeetingRequest.setuId(PreferenceUtils.getUid(getContext()));

        Log.d("key of the message", "size ");
//        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();




        Call<List<ToAttendMeetingResponse>> loginResponseCall = ApiClient.getUserService().toAttendMeeting(toAttendMeetingRequest.getuId());


        try {
            loginResponseCall.enqueue(new Callback<List<ToAttendMeetingResponse>>() {
                @Override
                public void onResponse(Call<List<ToAttendMeetingResponse>> call, Response<List<ToAttendMeetingResponse>> response) {

                    if (response.isSuccessful()) {
                        if (response.body() != null && response.body().size() > 0) {
                            List<ToAttendMeetingResponse> meetingsList = response.body();
                            int size=meetingsList.size();
                            Log.d("key of the message", "size " + size);
//                                Toast.makeText(MyService.this, "size " + size, Toast.LENGTH_SHORT).show();

                            Calendar c = Calendar.getInstance();
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


                            Log.d("count meetings", "are" + countMeetings);
                            //upto here



                        }

                    }
                }




                @Override
                public void onFailure(Call<List<ToAttendMeetingResponse>> call, Throwable t) {

//                        Toast.makeText(MyService.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                }
            });

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
*/

}