package com.example.ecms.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecms.AdminMessages;
import com.example.ecms.AdminMessagesAdapter;
import com.example.ecms.Models.Admin;
import com.example.ecms.R;

import java.util.ArrayList;

public class ReadyToApprove extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ReadyApproveAdapter adapter;
    private ArrayList<ReadytoapproveMOdel> mahasiswaArrayList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(" ");
        setContentView(R.layout.fragment_ready_to_approve);

        addData();

        recyclerView = findViewById(R.id.recycler_view);

        adapter = new ReadyApproveAdapter(mahasiswaArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ReadyToApprove.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }

    void addData(){
        mahasiswaArrayList = new ArrayList<>();
        mahasiswaArrayList.add(new ReadytoapproveMOdel("Workshop for Descrimination","Departmental","Workshop for Descrimination","Start Date"));
        mahasiswaArrayList.add(new ReadytoapproveMOdel("Workshop for Descrimination","Departmental","Workshop for Descrimination","Start Date"));
        mahasiswaArrayList.add(new ReadytoapproveMOdel("Workshop for Descrimination","Departmental","Workshop for Descrimination","Start Date"));
        mahasiswaArrayList.add(new ReadytoapproveMOdel("Workshop for Descrimination","Departmental","Workshop for Descrimination","Start Date"));
        mahasiswaArrayList.add(new ReadytoapproveMOdel("Workshop for Descrimination","Departmental","Workshop for Descrimination","Start Date"));
        mahasiswaArrayList.add(new ReadytoapproveMOdel("Workshop for Descrimination","Departmental","Workshop for Descrimination","Start Date"));
        mahasiswaArrayList.add(new ReadytoapproveMOdel("Workshop for Descrimination","Departmental","Workshop for Descrimination","Start Date"));
        mahasiswaArrayList.add(new ReadytoapproveMOdel("Workshop for Descrimination","Departmental","Workshop for Descrimination","Start Date"));


    }

}

