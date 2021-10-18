package com.example.ecms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.ecms.Adapters.ManageAdapter;
import com.example.ecms.Models.ManageModel;

import java.util.ArrayList;

public class ManageMeetingsActivity extends AppCompatActivity {
private Spinner approve;
    private RecyclerView recyclerView;
    private ManageAdapter adapter;

    private ArrayList<ManageModel> mahasiswaArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        addData();
        Spinner approve=(Spinner) findViewById(R.id.approvee);

        ArrayAdapter<String> spin = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.approve));
        approve.setAdapter(spin);

        recyclerView = findViewById(R.id.recycler_view);

        adapter = new ManageAdapter(mahasiswaArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ManageMeetingsActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);


    }

    void addData(){
        mahasiswaArrayList = new ArrayList<>();
        mahasiswaArrayList.add(new ManageModel("LABEL","LABEL","LABEL","LABEL","LABEL"));
        mahasiswaArrayList.add(new ManageModel("LABEL","LABEL","LABEL","LABEL","LABEL"));
        mahasiswaArrayList.add(new ManageModel("LABEL","LABEL","LABEL","LABEL","LABEL"));
        mahasiswaArrayList.add(new ManageModel("LABEL","LABEL","LABEL","LABEL","LABEL"));
        mahasiswaArrayList.add(new ManageModel("LABEL","LABEL","LABEL","LABEL","LABEL"));
        mahasiswaArrayList.add(new ManageModel("LABEL","LABEL","LABEL","LABEL","LABEL"));
        mahasiswaArrayList.add(new ManageModel("LABEL","LABEL","LABEL","LABEL","LABEL"));

    }
}