package com.ecms.ndmecms.ui;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ecms.ndmecms.MeetingsFragment;
import com.ecms.ndmecms.PublicFragment;

public class UserMessagesAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public UserMessagesAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                MeetingsFragment adminMessageFragment = new MeetingsFragment();
                return adminMessageFragment;
            case 1:
                PublicFragment userMessageFragment = new PublicFragment();
                return userMessageFragment;

            case 2:
                PrivateFragment privateFragment = new PrivateFragment();
                return privateFragment;

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