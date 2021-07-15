package com.example.ecms.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecms.Adapters.MessagesAdapter;
import com.example.ecms.R;
import com.example.ecms.ui.home.HomeFragment;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MessagesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessagesFragment extends Fragment {



    TabLayout tabLayoutMessages;
    ViewPager viewPagerMessages;


    public static MessagesFragment newInstance() {
        return new MessagesFragment();
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_messages, container, false);

        tabLayoutMessages=(TabLayout)view.findViewById(R.id.messages_tablayout);
        viewPagerMessages=(ViewPager)view.findViewById(R.id.messages_viewPager);

        tabLayoutMessages.addTab(tabLayoutMessages.newTab().setText("Admin Message"));
        tabLayoutMessages.addTab(tabLayoutMessages.newTab().setText("User Message"));
        tabLayoutMessages.setTabGravity(TabLayout.GRAVITY_FILL);

        final MessagesAdapter messagesAdapter = new MessagesAdapter(getContext(),getChildFragmentManager(), tabLayoutMessages.getTabCount());
        viewPagerMessages.setAdapter(messagesAdapter);

        viewPagerMessages.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutMessages));

        tabLayoutMessages.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerMessages.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }
}