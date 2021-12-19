package com.ecms.ndmecms.Adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ecms.ndmecms.Fragments.AdminMessageFragment;
import com.ecms.ndmecms.Fragments.UserMessageFragment;

public class MessagesAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public MessagesAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AdminMessageFragment adminMessageFragment = new AdminMessageFragment();
                return adminMessageFragment;
            case 1:
                UserMessageFragment userMessageFragment = new UserMessageFragment();
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