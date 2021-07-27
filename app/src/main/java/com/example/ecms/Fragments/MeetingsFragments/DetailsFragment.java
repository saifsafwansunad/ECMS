package com.example.ecms.Fragments.MeetingsFragments;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;

import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.ecms.CorrespondenceDetailsActivity;
import com.example.ecms.IncomingCorrespondenceActivity;
import com.example.ecms.R;
import com.example.ecms.RejectMeeting;
import com.example.ecms.RescheduleMeeting;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class DetailsFragment extends Fragment {


    FloatingActionMenu materialDesignFAM;
    com.github.clans.fab.FloatingActionButton floatingActionButtonReschedule, floatingActionButtonCancel;
    private boolean isFabMenuOpen = false;

    public DetailsFragment() {
        // Required empty public constructor
    }

    LinearLayout linearLayoutFab1, linearLayoutFab2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        Spinner spinnerMeetingType = (Spinner) view.findViewById(R.id.meeting_details_spinner);
        Spinner spinnerMeetingCommitee = (Spinner) view.findViewById(R.id.meeting_committee_spinner);
        Spinner spinnerMeetingFilesFromPlan = (Spinner) view.findViewById(R.id.files_from_plan_spinner);
        materialDesignFAM = (FloatingActionMenu) view.findViewById(R.id.meeting_fab_menu);
        floatingActionButtonReschedule = (com.github.clans.fab.FloatingActionButton) view.findViewById(R.id.reschedule_fab);
        floatingActionButtonCancel = (com.github.clans.fab.FloatingActionButton) view.findViewById(R.id.cancel_fab);

        floatingActionButtonReschedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RescheduleMeeting rescheduleMeeting= new RescheduleMeeting();
                Bundle bundle=new Bundle();
                bundle.putBoolean("notAlertDialog",true);
                rescheduleMeeting.setArguments(bundle);
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);


                rescheduleMeeting.show(ft, "dialog");
            }
        });

        floatingActionButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RejectMeeting dialogFragment = new RejectMeeting();

                Bundle bundle = new Bundle();
                bundle.putBoolean("notAlertDialog", true);

                dialogFragment.setArguments(bundle);

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);


                dialogFragment.show(ft, "dialog");
            }
        });


        ArrayAdapter<String> corTypeAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Deapartment));


        spinnerMeetingType.setAdapter(corTypeAdapter);
        spinnerMeetingCommitee.setAdapter(corTypeAdapter);
        spinnerMeetingFilesFromPlan.setAdapter(corTypeAdapter);
        return view;
    }


}