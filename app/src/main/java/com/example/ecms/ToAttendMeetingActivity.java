package com.example.ecms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.ecms.Adapters.ManageAdapter;
import com.example.ecms.Adapters.ToAttendMeetingsAdapter;
import com.example.ecms.Models.ManageModel;
import com.example.ecms.Models.ToattendMeetingsModel;

import java.util.ArrayList;

public class ToAttendMeetingActivity extends AppCompatActivity {
RecyclerView recyclerViewToAttend;
private ToAttendMeetingsAdapter toAttendMeetingsAdapter;
    private ArrayList<ToattendMeetingsModel> toattendMeetingsModelslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_attend_meeting);
        addData();
        recyclerViewToAttend = findViewById(R.id.to_attend_meetings_recyclerview);

        toAttendMeetingsAdapter = new ToAttendMeetingsAdapter(toattendMeetingsModelslist);
        toAttendMeetingsAdapter = new ToAttendMeetingsAdapter(toattendMeetingsModelslist);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ToAttendMeetingActivity.this);

        recyclerViewToAttend.setLayoutManager(layoutManager);

        recyclerViewToAttend.setAdapter(toAttendMeetingsAdapter);
    }

    private void addData() {
        toattendMeetingsModelslist = new ArrayList<>();
        toattendMeetingsModelslist.add(new ToattendMeetingsModel("Mayoral Agenda","Council","Mayoral Agenda","3/24/2021 10:00 AM","false"));
        toattendMeetingsModelslist.add(new ToattendMeetingsModel("Mayoral Agenda","Council","Mayoral Agenda","3/24/2021 10:00 AM","false"));
        toattendMeetingsModelslist.add(new ToattendMeetingsModel("Mayoral Agenda","Council","Mayoral Agenda","3/24/2021 10:00 AM","false"));
        toattendMeetingsModelslist.add(new ToattendMeetingsModel("Mayoral Agenda","Council","Mayoral Agenda","3/24/2021 10:00 AM","false"));
        toattendMeetingsModelslist.add(new ToattendMeetingsModel("Mayoral Agenda","Council","Mayoral Agenda","3/24/2021 10:00 AM","false"));


    }
}