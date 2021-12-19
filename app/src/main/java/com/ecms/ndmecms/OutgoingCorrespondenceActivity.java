package com.ecms.ndmecms;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.ecms.ndmecms.Adapters.IncomingCorrespondenceAdapter;
import com.ecms.ndmecms.Models.IncomingCorrepondenceObjects;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

public class OutgoingCorrespondenceActivity extends AppCompatActivity {

    androidx.appcompat.widget.Toolbar toolbar;
    ChipGroup chipGroup;
    Chip chipInProgress,chipApproval;
    RecyclerView recyclerViewIncoming;

    ArrayList<IncomingCorrepondenceObjects> incomingCorrepondenceObjectsArrayList,incomingCorrepondenceObjectsArrayListApproval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outgoing_correspondence);

        toolbar=(Toolbar) findViewById(R.id.incoming_toolbar);
        chipInProgress=(Chip)findViewById(R.id.inprogress_chip);
        chipApproval=(Chip)findViewById(R.id.approval_chip);
        ImageView imageViewAdd=(ImageView)findViewById(R.id.add_imageview);

        imageViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OutgoingCorrespondenceActivity.AddCorrespondenceDialog addCorrespondenceDialog=new OutgoingCorrespondenceActivity.AddCorrespondenceDialog();
                addCorrespondenceDialog.showDialog(OutgoingCorrespondenceActivity.this,"");
            }
        });

        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        toolbar.setTitle("Outgoing Correspondence");
        recyclerViewIncoming=(RecyclerView)findViewById(R.id.incomingcor_recyclerview);

        incomingCorrepondenceObjectsArrayList=new ArrayList<>();
        incomingCorrepondenceObjectsArrayList.add(new IncomingCorrepondenceObjects("345","10/12/2020","Updating of GRAP","Trevor Seagone","HIGH","recordsmanager"));
//        toolbar.setTitleTextColor(Color.parseColor("#000"));
        incomingCorrepondenceObjectsArrayList.add(new IncomingCorrepondenceObjects("345","10/12/2020","Updating of GRAP","Trevor Seagone","HIGH","recordsmanager"));
        incomingCorrepondenceObjectsArrayList.add(new IncomingCorrepondenceObjects("345","10/12/2020","Updating of GRAP","Trevor Seagone","HIGH","recordsmanager"));
        incomingCorrepondenceObjectsArrayList.add(new IncomingCorrepondenceObjects("345","10/12/2020","Updating of GRAP","Trevor Seagone","HIGH","recordsmanager"));
        incomingCorrepondenceObjectsArrayList.add(new IncomingCorrepondenceObjects("345","10/12/2020","Updating of GRAP","Trevor Seagone","HIGH","recordsmanager"));
        incomingCorrepondenceObjectsArrayList.add(new IncomingCorrepondenceObjects("345","10/12/2020","Updating of GRAP","Trevor Seagone","HIGH","recordsmanager"));

        IncomingCorrespondenceAdapter incomingCorrespondenceAdapter=new IncomingCorrespondenceAdapter(incomingCorrepondenceObjectsArrayList,this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerViewIncoming.setLayoutManager(linearLayoutManager);
        recyclerViewIncoming.setAdapter(incomingCorrespondenceAdapter);


        chipInProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewIncoming.setLayoutManager(linearLayoutManager);
                recyclerViewIncoming.setAdapter(incomingCorrespondenceAdapter);
            }
        });

        incomingCorrepondenceObjectsArrayListApproval=new ArrayList<>();

        incomingCorrepondenceObjectsArrayListApproval.add(new IncomingCorrepondenceObjects("954","10/12/2020","Updating of GRAP","Trevor Henry","Medium","recordsmanager"));
        incomingCorrepondenceObjectsArrayListApproval.add(new IncomingCorrepondenceObjects("123","10/12/2020","Updating of GRAP","Trevor MAxwell","Medium","recordsmanager"));
        incomingCorrepondenceObjectsArrayListApproval.add(new IncomingCorrepondenceObjects("022","10/12/2020","Updating of GRAP","Trevor Heisenberg","Medium","recordsmanager"));
        incomingCorrepondenceObjectsArrayListApproval.add(new IncomingCorrepondenceObjects("412","10/12/2020","Updating of GRAP","Trevor Noah","Medium","recordsmanager"));
        incomingCorrepondenceObjectsArrayListApproval.add(new IncomingCorrepondenceObjects("747","10/12/2020","Updating of GRAP","Trevor Seagone","Medium","recordsmanager"));
        IncomingCorrespondenceAdapter incomingCorrespondenceAdapterApproval=new IncomingCorrespondenceAdapter(incomingCorrepondenceObjectsArrayListApproval,this);

        chipApproval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewIncoming.setLayoutManager(linearLayoutManager);
                recyclerViewIncoming.setAdapter(incomingCorrespondenceAdapterApproval);
            }
        });
    }

    public class AddCorrespondenceDialog {

        public void showDialog(Activity activity, String msg) {
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
//            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            dialog.setContentView(R.layout.layout_for_add_outgoing_correspondence);
            ArrayAdapter<String> departmentadapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Deapartment));
            ArrayAdapter<String> sourceadapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Deapartment));
            ArrayAdapter<String> docthruadapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Document_Through));
            ArrayAdapter<String> doctypeadapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Document_Type));
            ArrayAdapter<String> priorityadapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Priority));

            Spinner spinnerDepartment = (Spinner)dialog.findViewById(R.id.department_spinner);
            Spinner spinnerSource= (Spinner)dialog.findViewById(R.id.source_spinner);
            Spinner spinnerDocuThru = (Spinner)dialog.findViewById(R.id.document_throu_spinner);
            Spinner spinnerDocuType = (Spinner)dialog.findViewById(R.id.document_typre_spinner);
            Spinner spinnerPriority = (Spinner)dialog.findViewById(R.id.priority_spinner);

            spinnerDepartment.setAdapter(departmentadapter);
            spinnerSource.setAdapter(sourceadapter);
            spinnerDocuThru.setAdapter(docthruadapter);
            spinnerDocuType.setAdapter(doctypeadapter);
            spinnerPriority.setAdapter(priorityadapter);

//            TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
//            text.setText(msg);

            MaterialButton dialogButton = (MaterialButton) dialog.findViewById(R.id.cancel_dialog_btn);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();

        }
    }
}