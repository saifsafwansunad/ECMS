package com.ecms.ndmecms.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ecms.ndmecms.R;

public class YourCalendarActivity extends AppCompatActivity {
    TextView title;
    ImageView backarrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_calendar);
        getSupportActionBar().hide();
        title=findViewById(R.id.title);

        title.setText("Your Calendar");
        backarrow=findViewById(R.id.imgBackArrow);

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}