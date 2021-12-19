package com.ecms.ndmecms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.ecms.ndmecms.Adapters.AddNewMeetingViewpagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class AddNewMeetingActivity extends AppCompatActivity {
    TabLayout tabLayoutmeeting;
    ViewPager viewPagerMeeting;
    AddNewMeetingViewpagerAdapter viewPagerAdapterMeetng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_meeting);
viewPagerMeeting=(ViewPager)findViewById(R.id.add_new_meeting_viewpager);
        tabLayoutmeeting=(TabLayout) findViewById(R.id.add_newmeetng_tablayout);
        viewPagerAdapterMeetng = new AddNewMeetingViewpagerAdapter(getSupportFragmentManager());

        viewPagerMeeting.setAdapter(viewPagerAdapterMeetng);
        tabLayoutmeeting.setupWithViewPager(viewPagerMeeting);


    }
}