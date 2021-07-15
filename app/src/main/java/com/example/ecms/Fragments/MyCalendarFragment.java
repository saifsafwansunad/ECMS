package com.example.ecms.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecms.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyCalendarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyCalendarFragment extends Fragment {



    public static MyCalendarFragment newInstance() {
        return new MyCalendarFragment();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_calendar, container, false);
    }
}