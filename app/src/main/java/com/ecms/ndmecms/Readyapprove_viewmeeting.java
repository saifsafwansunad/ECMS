package com.ecms.ndmecms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Readyapprove_viewmeeting extends AppCompatActivity implements View.OnClickListener, approvemeeting.DialogListener {
//private Button approve,reject;
TextView title,meetingtype,agenda,description,startDate,approveComment,approveDate,isMsteam,msTeamMeetingId,titletoolbar;
CommiteeMeetingModel meetingModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readyapprove_viewmeeting);
        setTitle("View Meetings");
//        approve=findViewById(R.id.approve);
//        reject=findViewById(R.id.reject);
        title=findViewById(R.id.title_com);

        titletoolbar=findViewById(R.id.title);

        title.setText("Meeting Details");
        meetingtype=findViewById(R.id.meeting_type);
        agenda=findViewById(R.id.agenda);
        description=findViewById(R.id.description);
        startDate=findViewById(R.id.start_date);
        approveComment=findViewById(R.id.approve_comment);
        approveDate=findViewById(R.id.approve_date);
        isMsteam=findViewById(R.id.ismeeting);
        msTeamMeetingId=findViewById(R.id.meeting_id);
        meetingModel=CommiteMeetingsAdapter.meetingDetails;
        Toast.makeText(this, meetingModel.getMeetingId(), Toast.LENGTH_SHORT).show();

        title.setText(meetingModel.getTitle());
        meetingtype.setText(meetingModel.getMeetingType());
        agenda.setText(meetingModel.getAgenda());
        description.setText(meetingModel.getDescription());
        startDate.setText(meetingModel.getStartDate());
        approveDate.setText(meetingModel.getStartDate());
        msTeamMeetingId.setText(meetingModel.getMSTeamMeetingID());
        isMsteam.setText(meetingModel.getIsMSTeamMeeting());

//
//        reject.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                RejectMeeting dialogFragment = new RejectMeeting();
//
//
//                Bundle bundle = new Bundle();
//                bundle.putBoolean("notAlertDialog", true);
//
//                dialogFragment.setArguments(bundle);
//
//                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
//                if (prev != null) {
//                    ft.remove(prev);
//                }
//                ft.addToBackStack(null);
//
//
//                dialogFragment.show(ft, "dialog");
//
//            }
//        });
//
//        approve.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                approvemeeting dialogFragment = new approvemeeting();
//
//
//                Bundle bundle = new Bundle();
//                bundle.putBoolean("notAlertDialog", true);
//
//                dialogFragment.setArguments(bundle);
//
//                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
//                if (prev != null) {
//                    ft.remove(prev);
//                }
//                ft.addToBackStack(null);
//
//
//                dialogFragment.show(ft, "dialog");
//
//            }
//        });




    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onFinishEditDialog(String inputText) {

    }
}