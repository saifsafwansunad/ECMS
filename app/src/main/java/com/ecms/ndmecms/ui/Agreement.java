package com.ecms.ndmecms.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.ecms.ndmecms.LoginActivity;
import com.ecms.ndmecms.PreferenceUtils;
import com.ecms.ndmecms.R;
import com.ecms.ndmecms.SplashScreen;

public class Agreement extends AppCompatActivity {

    CheckBox checkBox;
    TextView log; 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_agreement);
        checkBox=findViewById(R.id.agree);
        log=findViewById(R.id.log_agree);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        if(checkBox.isChecked()) log.setEnabled(true);
        
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    PreferenceUtils.saveAgree("true",Agreement.this);
                    Intent i = new Intent(Agreement.this, LoginActivity.class);
                    startActivity(i);

                }else Toast.makeText(Agreement.this, "please accept the agreement", Toast.LENGTH_SHORT).show();

            }
        });
    }
}