package com.example.ecms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecms.Adapters.MeetingAttachmentAdapter;
import com.example.ecms.Adapters.ToAttendMeetingsAdapter;
import com.example.ecms.ApiResponse.ToAttendMeetingResponse;

public class AttendMeetingDetailsActivity extends AppCompatActivity {
    ToAttendMeetingResponse meeting;
    TextView title_toattend,meeting_type_toattend, agenda_toattend, meet_detail_description, date_time_toattend, isMSteam__toattend_textview, meeting_id_textview
            ,MSTeamMeetingJoinUrl_textview, MSTeamMeetingWebLink_textview;
    RecyclerView meeting_details_attachment_recyclerview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attend_meeting_details);
        meeting = ToAttendMeetingsAdapter.meetingDetails;
//        ToAttendMeetingResponse meeting = getIntent().getParcelableExtra("Meetings");
        Toast.makeText(this, meeting.getMeetingId(), Toast.LENGTH_SHORT).show();

        title_toattend = findViewById(R.id.title_toattend);
        meeting_type_toattend = findViewById(R.id.meeting_type_toattend);
        agenda_toattend = findViewById(R.id.agenda_toattend);
        meet_detail_description = findViewById(R.id.meet_detail_description);
        date_time_toattend = findViewById(R.id.date_time_toattend);
        isMSteam__toattend_textview = findViewById(R.id.isMSteam__toattend_textview);
        meeting_id_textview = findViewById(R.id.meeting_id_textview);
        MSTeamMeetingJoinUrl_textview = findViewById(R.id.MSTeamMeetingJoinUrl_textview);
        MSTeamMeetingWebLink_textview = findViewById(R.id.MSTeamMeetingWebLink_textview);
        meeting_details_attachment_recyclerview = findViewById(R.id.meeting_details_attachment_recyclerview);

        title_toattend.setText(meeting.getTitle());
        meeting_type_toattend.setText(meeting.getMeetingType());
        agenda_toattend.setText(meeting.getAgenda());
        meet_detail_description.setText(meeting.getDescription());
        date_time_toattend.setText(meeting.getStartDate());
        isMSteam__toattend_textview.setText(meeting.getIsMSTeamMeeting());
        meeting_id_textview.setText(meeting.getmSTeamMeetingID());
        MSTeamMeetingJoinUrl_textview.setText(meeting.getmSTeamMeetingJoinUrl());
        MSTeamMeetingWebLink_textview.setText(meeting.getmSTeamMeetingWebLink());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(AttendMeetingDetailsActivity.this);

        meeting_details_attachment_recyclerview.setLayoutManager(layoutManager);

        meeting_details_attachment_recyclerview.setAdapter(new MeetingAttachmentAdapter(AttendMeetingDetailsActivity.this, meeting.getMeetingAttachments()));






    }
}