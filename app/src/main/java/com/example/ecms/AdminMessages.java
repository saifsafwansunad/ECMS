package com.example.ecms;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecms.Models.Admin;

import java.util.ArrayList;

public class AdminMessages extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AdminMessagesAdapter adapter;
    private ArrayList<Admin> mahasiswaArrayList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_admin_message);

        addData();

        recyclerView = findViewById(R.id.recycler_view);

        adapter = new AdminMessagesAdapter(mahasiswaArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(AdminMessages.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }

    void addData(){
        mahasiswaArrayList = new ArrayList<>();
        mahasiswaArrayList.add(new Admin("LABEL","LABEL","LABEL","LABEL"));
        mahasiswaArrayList.add(new Admin("LABEL","LABEL","LABEL","LABEL"));
        mahasiswaArrayList.add(new Admin("LABEL","LABEL","LABEL","LABEL"));
        mahasiswaArrayList.add(new Admin("LABEL","LABEL","LABEL","LABEL"));
        mahasiswaArrayList.add(new Admin("LABEL","LABEL","LABEL","LABEL"));
        mahasiswaArrayList.add(new Admin("LABEL","LABEL","LABEL","LABEL"));

    }

}
