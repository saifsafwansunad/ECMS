package com.example.ecms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    TextView userName, userEmail, userMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();
        userName = findViewById(R.id.user_name_tv);
        userEmail = findViewById(R.id.user_email_tv);
        userMobile = findViewById(R.id.user_mobile_tv);

        userName.setText(PreferenceUtils.getUserName(this));
        userEmail.setText(PreferenceUtils.getEmail(this));
        userMobile.setText(PreferenceUtils.getMobile(this));

    }
}