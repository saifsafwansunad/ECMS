package com.ecms.ndmecms.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ecms.ndmecms.Adapters.EmployeeAdapterClass;
import com.ecms.ndmecms.DatabaseHelperClass;
import com.ecms.ndmecms.Models.EmployeeModelClass;
import com.ecms.ndmecms.R;

import java.util.List;

public class ViewEmployeeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employee);
        getSupportActionBar().hide();
        title=findViewById(R.id.title);
        title.setText("Offline downloads");
        ImageView backarrow=findViewById(R.id.imgBackArrow);

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DatabaseHelperClass databaseHelperClass = new DatabaseHelperClass(this);
        List<EmployeeModelClass> employeeModelClasses = databaseHelperClass.getEmployeeList();

        if (employeeModelClasses.size() > 0){
            EmployeeAdapterClass employeadapterclass = new EmployeeAdapterClass(employeeModelClasses,ViewEmployeeActivity.this);
            recyclerView.setAdapter(employeadapterclass);
        }else {
            Toast.makeText(this, "There is no downloads", Toast.LENGTH_SHORT).show();
        }




    }
}
