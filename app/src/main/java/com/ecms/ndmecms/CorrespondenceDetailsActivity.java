package com.ecms.ndmecms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.ecms.ndmecms.Adapters.CorrespondenceDetailsViewpagerAdapter;
import com.google.android.material.tabs.TabLayout;

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