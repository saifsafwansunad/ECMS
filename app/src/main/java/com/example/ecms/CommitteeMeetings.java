package com.example.ecms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.ecms.ui.ReadyApproveAdapter;
import com.example.ecms.ui.ReadyToApprove;
import com.example.ecms.ui.ReadytoapproveMOdel;

import java.util.ArrayList;

public class CommitteeMeetings extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CommiteMeetingsAdapter adapter;
    private ArrayList<CommiteeMeetingModel> mahasiswaArrayList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Committee Meetings");
        setContentView(R.layout.activity_committee_meetings);

        addData();

        recyclerView = findViewById(R.id.recycler_view);

        adapter = new CommiteMeetingsAdapter(mahasiswaArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CommitteeMeetings.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }

    void addData(){
        mahasiswaArrayList = new ArrayList<>();
        mahasiswaArrayList.add(new CommiteeMeetingModel("Workshop for Descrimination","Departmental","Workshop for Descrimination","Start Date"));
        mahasiswaArrayList.add(new CommiteeMeetingModel("Workshop for Descrimination","Departmental","Workshop for Descrimination","Start Date"));
        mahasiswaArrayList.add(new CommiteeMeetingModel("Workshop for Descrimination","Departmental","Workshop for Descrimination","Start Date"));
        mahasiswaArrayList.add(new CommiteeMeetingModel("Workshop for Descrimination","Departmental","Workshop for Descrimination","Start Date"));
        mahasiswaArrayList.add(new CommiteeMeetingModel("Workshop for Descrimination","Departmental","Workshop for Descrimination","Start Date"));
        mahasiswaArrayList.add(new CommiteeMeetingModel("Workshop for Descrimination","Departmental","Workshop for Descrimination","Start Date"));
        mahasiswaArrayList.add(new CommiteeMeetingModel("Workshop for Descrimination","Departmental","Workshop for Descrimination","Start Date"));

    }

}

