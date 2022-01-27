package com.ecms.ndmecms.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.ecms.ndmecms.R;
import com.google.android.material.tabs.TabLayout;

public class UserMessages extends AppCompatActivity {


    TabLayout tabLayoutMessages;
    ViewPager viewPagerMessages;


    public static UserMessages newInstance() {
        return new UserMessages();
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        setTitle("User Messages");
        setContentView(R.layout.fragment_inbox);
     //   View view = inflater.inflate(R.layout.fragment_inbox, container, false);

        tabLayoutMessages = (TabLayout) findViewById(R.id.messages_tablayout);
        viewPagerMessages = (ViewPager) findViewById(R.id.messages_viewPager);

        tabLayoutMessages.addTab(tabLayoutMessages.newTab().setText("Meetings"));
        tabLayoutMessages.addTab(tabLayoutMessages.newTab().setText("Public"));
        tabLayoutMessages.addTab(tabLayoutMessages.newTab().setText("Private"));

        tabLayoutMessages.setTabGravity(TabLayout.GRAVITY_FILL);

        final UserMessagesAdapter messagesAdapter = new UserMessagesAdapter(this, getSupportFragmentManager(), tabLayoutMessages.getTabCount());
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



    }}