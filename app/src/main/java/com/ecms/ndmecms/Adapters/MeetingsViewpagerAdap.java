package com.ecms.ndmecms.Adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ecms.ndmecms.MeetingsFragment;
import com.ecms.ndmecms.PublicFragment;
import com.ecms.ndmecms.ui.PrivateFragment;

public class MeetingsViewpagerAdap extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public MeetingsViewpagerAdap(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AttendedMeetingsFragment adminMessageFragment = new AttendedMeetingsFragment();
                return adminMessageFragment;
            case 1:
                UpcomingMeetingsFragment userMessageFragment = new UpcomingMeetingsFragment();
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