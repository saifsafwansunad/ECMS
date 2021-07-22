package com.example.ecms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.ecms.Models.Admin;

import java.util.ArrayList;

public class CommitteeManagement extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AdminCommittee adapter;
    private ArrayList<Commitee> mahasiswaArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("COMMITTEE MANAGEMENT");
        setContentView(R.layout.activity_committee_management);

        addData();

        recyclerView = findViewById(R.id.recycler_view);

        adapter = new AdminCommittee(mahasiswaArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CommitteeManagement.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }

    void addData(){
        mahasiswaArrayList = new ArrayList<>();
        mahasiswaArrayList.add(new Commitee("Broader Management","Show Meetings"));
        mahasiswaArrayList.add(new Commitee("Broader Management","Show Meetings"));
        mahasiswaArrayList.add(new Commitee("Broader Management","Show Meetings"));
        mahasiswaArrayList.add(new Commitee("Broader Management","Show Meetings"));
        mahasiswaArrayList.add(new Commitee("Broader Management","Show Meetings"));
        mahasiswaArrayList.add(new Commitee("Broader Management","Show Meetings"));




    }

}
