package com.example.ecms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class LoginActivity extends AppCompatActivity {
AppCompatButton buttonLogin;
CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    buttonLogin=(AppCompatButton) findViewById(R.id.login_btn);
    getSupportActionBar().hide();

    buttonLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        }
    });
    }
}