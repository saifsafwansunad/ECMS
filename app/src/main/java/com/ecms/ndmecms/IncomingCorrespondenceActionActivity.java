package com.ecms.ndmecms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ecms.ndmecms.Adapters.AssignedCorrespondenceTaskAdapter;
import com.ecms.ndmecms.Models.CorrespondenceTaskObject;

import java.util.ArrayList;

public class IncomingCorrespondenceActionActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AssignedCorrespondenceTaskAdapter assignedCorrespondenceTaskAdapter;
    private ArrayList<CorrespondenceTaskObject> correspondenceTaskObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Incoming Correspondence Actions");
        setContentView(R.layout.activity_incoming_correspondence_action);

        addData();

        recyclerView = findViewById(R.id.assigned_task_rv);

        assignedCorrespondenceTaskAdapter = new AssignedCorrespondenceTaskAdapter(correspondenceTaskObjects, IncomingCorrespondenceActionActivity.this);

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