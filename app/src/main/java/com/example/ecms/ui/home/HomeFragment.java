package com.example.ecms.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.ecms.Adapters.MessagesAdapter;
import com.example.ecms.Adapters.MyAdapter;
import com.example.ecms.Adapters.ViewPagerCardsAdapter;
import com.example.ecms.CardItems;
import com.example.ecms.PublicActivity;
import com.example.ecms.R;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {
    private ViewPager mViewPager;
    int page = 0;
    private ViewPagerCardsAdapter mCardAdapter;
    String detailsArray[] = {"asd", "dasf", "gsdg","asdas"};
    String detailsArray2[] = {"sdg", "gsdg", "gdsgdsg","adasd"};

Button buttonPublic,buttonPrivate;
    TabLayout tabLayout;
    ViewPager viewPager;

//    TabLayout tabLayoutMessages;
//    ViewPager viewPagerMessages;



    public static HomeFragment newInstance() {
        return new HomeFragment();
    }


    //    private ShadowTransformer mCardShadowTransformer;
//    private HomeViewModel homeViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        setRetainInstance(true);
        buttonPublic=(Button)root.findViewById(R.id.public_btn);
        buttonPublic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), PublicActivity.class);
                startActivity(intent);
            }
        });

        mViewPager = (ViewPager)root.findViewById(R.id.home_viewpager);
        mViewPager.setClipToPadding(false);
        mViewPager.setPadding(40,0,40,0);
        mViewPager.setOffscreenPageLimit(3);
        mCardAdapter = new ViewPagerCardsAdapter(getContext());


        for (int i = 0; i < detailsArray.length; i++) {

            mCardAdapter.addCardItemS(new CardItems(detailsArray, detailsArray2[i]));
        }

//        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);

        mViewPager.setAdapter(mCardAdapter);




//        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);


        tabLayout=(TabLayout)root.findViewById(R.id.calendar_tablayout);
        viewPager=(ViewPager)root.findViewById(R.id.calender_viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Organization Calendar"));
        tabLayout.addTab(tabLayout.newTab().setText("Your Calendar"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final MyAdapter adapter = new MyAdapter(getContext(),getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//        tabLayoutMessages=(TabLayout)root.findViewById(R.id.messages_tablayout);
//        viewPagerMessages=(ViewPager)root.findViewById(R.id.messages_viewPager);
//
//        tabLayoutMessages.addTab(tabLayoutMessages.newTab().setText("Organization Calendar"));
//        tabLayoutMessages.addTab(tabLayoutMessages.newTab().setText("Your Calendar"));
//        tabLayoutMessages.setTabGravity(TabLayout.GRAVITY_FILL);
//
//        final MessagesAdapter messagesAdapter = new MessagesAdapter(getContext(),getFragmentManager (), tabLayoutMessages.getTabCount());
//        viewPagerMessages.setAdapter(messagesAdapter);
//
//        viewPagerMessages.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutMessages));
//
//        tabLayoutMessages.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPagerMessages.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });


        return root;
    }



}