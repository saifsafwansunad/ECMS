package com.ecms.ndmecms.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ecms.ndmecms.Fragments.FusionTestTitleFragments.CorrespondenceAttachmentFragment;
import com.ecms.ndmecms.Fragments.FusionTestTitleFragments.FusionTestDetailsFragment;

public class FusionTestTitleVPAdapter extends FragmentPagerAdapter {


    public FusionTestTitleVPAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new FusionTestDetailsFragment();
        }
        else if (position == 1)
        {
            fragment = new CorrespondenceAttachmentFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
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
            title = "Correspondence Attachments";
        }

        return title;
    }
}
