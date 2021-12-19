package com.ecms.ndmecms.Adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ecms.ndmecms.Fragments.MyCalendarFragment;
import com.ecms.ndmecms.Fragments.OrgCalendarFragment;

public class MyAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public MyAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                OrgCalendarFragment orgCalendarFragment = new OrgCalendarFragment();
                return orgCalendarFragment;
            case 1:
                MyCalendarFragment myCalendarFragment = new MyCalendarFragment();
                return myCalendarFragment;
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