package com.ecms.ndmecms.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ecms.ndmecms.Fragments.MeetingsFragments.AttachmentFragment;
import com.ecms.ndmecms.Fragments.MeetingsFragments.AttendeesFragment;
import com.ecms.ndmecms.Fragments.MeetingsFragments.DetailsFragment;
import com.ecms.ndmecms.Fragments.MeetingsFragments.ExternalFragment;

public class AddNewMeetingViewpagerAdapter extends FragmentPagerAdapter {


    public  AddNewMeetingViewpagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new DetailsFragment();
        }
        else if (position == 1)
        {
            fragment = new AttendeesFragment();
        }
        else if (position == 2)
        {
            fragment = new ExternalFragment();
        }
        else if (position==3)
        {
            fragment = new AttachmentFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "Details";
        }
        else if (position == 1)
        {
            title = "Attendees";
        }
        else if (position == 2)
        {
            title = "External";
        }
        else if (position == 3){
            title="Attachment";
        }
        return title;
    }

}
