package com.example.ecms.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.ecms.Fragments.CorrespondenceDetailFragment.CorrespondenceDetailsFragment;
import com.example.ecms.Fragments.CorrespondenceDetailFragment.CorrespondenceTaskFragment;
import com.example.ecms.Fragments.CorrespondenceDetailFragment.CorrespondenceVerification;
import com.example.ecms.Fragments.CorrespondenceDetailFragment.CorrespondenceWorkflowFragment;


public class CorrespondenceDetailsViewpagerAdapter extends FragmentPagerAdapter {

    public CorrespondenceDetailsViewpagerAdapter(@NonNull  FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new CorrespondenceDetailsFragment();
        }
        else if (position == 1)
        {
            fragment = new CorrespondenceTaskFragment();
        }
        else if (position == 2)
        {
            fragment = new CorrespondenceWorkflowFragment();
        }
        else if (position==3)
        {
            fragment = new CorrespondenceVerification();
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
            title = "Task";
        }
        else if (position == 2)
        {
            title = "Workflow";
        }
        else if (position == 3){
            title="Verifications";
        }
        return title;
    }
}
