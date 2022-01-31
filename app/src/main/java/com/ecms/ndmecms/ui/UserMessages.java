package com.ecms.ndmecms.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.ecms.ndmecms.LoginActivity;
import com.ecms.ndmecms.MainActivity;
import com.ecms.ndmecms.PreferenceUtils;
import com.ecms.ndmecms.ProfileActivity;
import com.ecms.ndmecms.R;
import com.google.android.material.tabs.TabLayout;

public class UserMessages extends AppCompatActivity {


    TabLayout tabLayoutMessages;
    ViewPager viewPagerMessages;
    TextView profileName,logout_second,profile;

    public static UserMessages newInstance() {
        return new UserMessages();
    }


    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        setTitle("NDM ECMS");
        getSupportActionBar().hide();

        setContentView(R.layout.fragment_inbox);
     //   View view = inflater.inflate(R.layout.fragment_inbox, container, false);

        tabLayoutMessages = (TabLayout) findViewById(R.id.messages_tablayout);
        viewPagerMessages = (ViewPager) findViewById(R.id.messages_viewPager);

        tabLayoutMessages.addTab(tabLayoutMessages.newTab().setText("Meetings"));
        tabLayoutMessages.addTab(tabLayoutMessages.newTab().setText("Public"));
        tabLayoutMessages.addTab(tabLayoutMessages.newTab().setText("Private"));

        profileName=findViewById(R.id.profile_name);
        logout_second=findViewById(R.id.logout_second);
        profile=findViewById(R.id.profile_second);



        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserMessages.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        logout_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceUtils.savePassword(null, UserMessages.this);
                PreferenceUtils.saveEmail(null, UserMessages.this);
                PreferenceUtils.saveUid(null, UserMessages.this);
                // MyService.count = 0;

                //trying to stop the service
              /*  stopService = true;
                Intent stopIntent = new Intent(getApplicationContext(), MyService.class);
                stopIntent.putExtra("service", "yes");
                stopIntent.setAction("stopService");
                getApplicationContext().startService(stopIntent);*/
                final ProgressDialog progressDoalog;
                progressDoalog = new ProgressDialog(UserMessages.this);
                progressDoalog.setMessage("Logging Out....");
                progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDoalog.show();

                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(i);
                finish();
            }
        });
        profileName.setText(PreferenceUtils.getUserName(getApplicationContext()));
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