package com.ecms.ndmecms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.ecms.ndmecms.Adapters.MeetingAttachmentAdapter;
import com.ecms.ndmecms.Adapters.ToAttendMeetingsAdapter;
import com.ecms.ndmecms.ApiResponse.MeetingAttachment;
import com.ecms.ndmecms.ApiResponse.ToAttendMeetingResponse;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class AttendMeetingDetailsActivity extends AppCompatActivity {
    ToAttendMeetingResponse meeting;
    TextView title_toattend,meeting_type_toattend, agenda_toattend, meet_detail_description, date_time_toattend, isMSteam__toattend_textview, meeting_id_textview
            ,MSTeamMeetingJoinUrl_textview, MSTeamMeetingWebLink_textview;
    RecyclerView meeting_details_attachment_recyclerview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attend_meeting_details);
        getSupportActionBar().hide();
        Toolbar toolbarMeetingsdetail = (Toolbar)findViewById(R.id.meetngs_details_toolbar);
        toolbarMeetingsdetail.setTitle("Meeting To Attend");
        meeting = ToAttendMeetingsAdapter.meetingDetails;
//        ToAttendMeetingResponse meeting = getIntent().getParcelableExtra("Meetings");
//        Toast.makeText(this, meeting.getMeetingId(), Toast.LENGTH_SHORT).show();

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
        List<MeetingAttachment> meetingAttachments;

        if(meeting.getMeetingAttachments() instanceof JsonPrimitive){
//            YourModelForData object = YourDataComponentForObject(data);
            // Do anything with Object
            meetingAttachments = new ArrayList<>();
        } else {
            meetingAttachments = YourDataComponentForArray(meeting.getMeetingAttachments());
            // Do anything with array
        }

        meeting_details_attachment_recyclerview.setAdapter(new MeetingAttachmentAdapter(AttendMeetingDetailsActivity.this, meetingAttachments));
//



    }

    public static List<MeetingAttachment> convertObjectToList(Object obj) {
        List<MeetingAttachment> list = new ArrayList<>();
        if (obj.getClass().isArray()) {
            list = Arrays.asList((MeetingAttachment[])obj);
        } else if (obj instanceof Collection) {
            list = new ArrayList<MeetingAttachment>((Collection<MeetingAttachment>)obj);
        }
        return list;
    }
//    public YourModelForData YourDataComponentForObject(JsonElement data) {
//        Type type = new TypeToken<YourModelForData>() {
//        }.getType();
//        YourModelForData item = new Gson().fromJson(data, type);
//    }

    public List<MeetingAttachment> YourDataComponentForArray(JsonElement data) {
        Type type = new TypeToken<List<MeetingAttachment>>() {
        }.getType();
        List<MeetingAttachment> items = new Gson().fromJson(data, type);

        return  items;
    }
}