package com.ecms.ndmecms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.ecms.ndmecms.Adapters.FusionTestTitleVPAdapter;
import com.google.android.material.tabs.TabLayout;

public class FusionTestTitleActivity extends AppCompatActivity {

    TabLayout tabLayoutFusionTestTitle;
    ViewPager viewPagerFusionTestTitle;
    FusionTestTitleVPAdapter fusionTestTitleVPAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Fusion Test Title");
        setContentView(R.layout.activity_fusion_test_title);

        viewPagerFusionTestTitle=(ViewPager)findViewById(R.id.fusion_test_viewpager);
        tabLayoutFusionTestTitle=(TabLayout) findViewById(R.id.fusion_test_tabLayout);
        fusionTestTitleVPAdapter = new FusionTestTitleVPAdapter(getSupportFragmentManager());

        viewPagerFusionTestTitle.setAdapter(fusionTestTitleVPAdapter);
        tabLayoutFusionTestTitle.setupWithViewPager(viewPagerFusionTestTitle);
    }
}