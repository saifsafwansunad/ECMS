package com.example.ecms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ecms.Adapters.AddNewMeetingViewpagerAdapter;
import com.example.ecms.Adapters.CorrespondenceDetailsViewpagerAdapter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CorrespondenceDetailsActivity extends AppCompatActivity {


    TabLayout tabLayoutCorrespondenceDetails;
    ViewPager viewPagerCorrespondenceDetails;
    CorrespondenceDetailsViewpagerAdapter viewPagerAdapterCorrespondenceDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correspondence_details);

        viewPagerCorrespondenceDetails=(ViewPager)findViewById(R.id.correspondence_details_viewpager);
        tabLayoutCorrespondenceDetails=(TabLayout) findViewById(R.id.correspondence_details_tabLayout);
        viewPagerAdapterCorrespondenceDetails = new CorrespondenceDetailsViewpagerAdapter(getSupportFragmentManager());

        viewPagerCorrespondenceDetails.setAdapter(viewPagerAdapterCorrespondenceDetails);
        tabLayoutCorrespondenceDetails.setupWithViewPager(viewPagerCorrespondenceDetails);




    }


//public void setupSpinner(){
//    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item , getResources().getStringArray(R.array.Correspondent_Type));
//
//    AutoCompleteTextView mTextView = (AutoCompleteTextView) findViewById(R.id.type_actv);
//
//    mTextView.setAdapter(adapter);
//    mTextView.setKeyListener(null);
//    mTextView.setOnTouchListener(new View.OnTouchListener(){
//        @Override
//        public boolean onTouch(View v, MotionEvent event){
//            ((AutoCompleteTextView) v).showDropDown();
//            return false;
//        }
//    });
//}
}