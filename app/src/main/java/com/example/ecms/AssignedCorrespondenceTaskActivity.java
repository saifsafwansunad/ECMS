package com.example.ecms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.ecms.Adapters.AssignedCorrespondenceTaskAdapter;
import com.example.ecms.Models.CorrespondenceTaskObject;
import com.example.ecms.Models.IncomingCorrepondenceObjects;

import java.util.ArrayList;

public class AssignedCorrespondenceTaskActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AssignedCorrespondenceTaskAdapter assignedCorrespondenceTaskAdapter;
    private ArrayList<CorrespondenceTaskObject> correspondenceTaskObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Assigned Correspondence Task");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assigned_correspondence_task);

        addData();

        recyclerView = findViewById(R.id.assigned_task_rv);

        assignedCorrespondenceTaskAdapter = new AssignedCorrespondenceTaskAdapter(correspondenceTaskObjects, AssignedCorrespondenceTaskActivity.this);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(assignedCorrespondenceTaskAdapter);
    }

    private void addData() {
        correspondenceTaskObjects=new ArrayList<>();
        correspondenceTaskObjects.add(new CorrespondenceTaskObject("Fusion test title", "fusion", "23/09/2020", "0", "MK SKOSNA", "75"));
        correspondenceTaskObjects.add(new CorrespondenceTaskObject("Fusion test title", "fusion", "23/09/2020", "0", "MK SKOSNA", "75"));
        correspondenceTaskObjects.add(new CorrespondenceTaskObject("Fusion test title", "fusion", "23/09/2020", "0", "MK SKOSNA", "75"));
        correspondenceTaskObjects.add(new CorrespondenceTaskObject("Fusion test title", "fusion", "23/09/2020", "0", "MK SKOSNA", "75"));
        correspondenceTaskObjects.add(new CorrespondenceTaskObject("Fusion test title", "fusion", "23/09/2020", "0", "MK SKOSNA", "75"));
        correspondenceTaskObjects.add(new CorrespondenceTaskObject("Fusion test title", "fusion", "23/09/2020", "0", "MK SKOSNA", "75"));
    }
}