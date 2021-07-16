package com.example.ecms.Fragments.MeetingsFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.ecms.CorrespondenceDetailsActivity;
import com.example.ecms.R;


public class DetailsFragment extends Fragment {



    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_details, container, false);

        Spinner spinnerMeetingType=(Spinner)view.findViewById(R.id.meeting_details_spinner);
        Spinner spinnerMeetingCommitee=(Spinner)view.findViewById(R.id.meeting_committee_spinner);
        Spinner spinnerMeetingFilesFromPlan=(Spinner)view.findViewById(R.id.files_from_plan_spinner);


        ArrayAdapter<String> corTypeAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Deapartment));

spinnerMeetingType.setAdapter(corTypeAdapter);
spinnerMeetingCommitee.setAdapter(corTypeAdapter);
spinnerMeetingFilesFromPlan.setAdapter(corTypeAdapter);
        return view;
    }
}