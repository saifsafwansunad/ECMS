package com.ecms.ndmecms.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ecms.ndmecms.R;

public class OrganisationCalendarActivity extends AppCompatActivity {
    TextView title;
    ImageView backarrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisation_calendar);

        getSupportActionBar().hide();
        title=findViewById(R.id.title);

        title.setText("Organisation Calendar");
        backarrow=findViewById(R.id.imgBackArrow);

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}