package com.example.ecms.Fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.example.ecms.R;


public class OrgCalendarFragment extends Fragment {

    public static OrgCalendarFragment newInstance() {
        return new OrgCalendarFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_org_calendar, container, false);

//        CalendarView orgCalendar = view.findViewById(R.id.custom_calendar_view);
//        int as = orgCalendar.getDateTextAppearance();



        return view;
    }
}