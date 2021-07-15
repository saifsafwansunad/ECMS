package com.example.ecms.ui;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.ecms.Adapters.FragmentAdapter;
import com.example.ecms.Adapters.MessagesAdapter;
import com.example.ecms.Fragments.MessagesFragment;
import com.example.ecms.R;
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

        tabLayoutMessages.addTab(tabLayoutMessages.newTab().setText("Inbox"));
        tabLayoutMessages.addTab(tabLayoutMessages.newTab().setText("Sent"));
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