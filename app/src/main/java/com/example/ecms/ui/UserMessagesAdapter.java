package com.example.ecms.ui;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.ecms.Fragments.AdminMessageFragment;
import com.example.ecms.Fragments.UserMessageFragment;
import com.example.ecms.Inbox;
import com.example.ecms.Sent;

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
                Inbox adminMessageFragment = new Inbox();
                return adminMessageFragment;
            case 1:
                Sent userMessageFragment = new Sent();
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