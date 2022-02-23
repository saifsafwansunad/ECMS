package com.ecms.ndmecms.Activity;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ecms.ndmecms.Adapters.AttendedMeetingsFragment;
import com.ecms.ndmecms.Adapters.UpcomingMeetingsFragment;

public class MeetingsViewpagerAdap1 extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public MeetingsViewpagerAdap1(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AttendedMeetingsFragment1 adminMessageFragment = new AttendedMeetingsFragment1();
                return adminMessageFragment;
            case 1:
                UpcomingMeetingsFragment1 userMessageFragment = new UpcomingMeetingsFragment1();
                return userMessageFragment;


            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}
