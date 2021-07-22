package com.example.ecms;

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

import com.example.ecms.Adapters.IncomingCorrespondenceAdapter;
import com.example.ecms.Adapters.ResolutionCorrespondenceAdapter;
import com.example.ecms.Models.IncomingCorrepondenceObjects;
import com.example.ecms.Models.ResolutionCorrespondenceObject;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

public class ResolutionCorrespondenceActivity extends AppCompatActivity {

    androidx.appcompat.widget.Toolbar toolbar;
    ChipGroup chipGroup;
    Chip chipInProgress,chipApproval;
    RecyclerView recyclerViewResolution;
    ArrayList<ResolutionCorrespondenceObject> ResolutionCorrespondenceObjectsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resolution_correspondence);

        toolbar=(Toolbar) findViewById(R.id.Resolution_toolbar);
        chipInProgress=(Chip)findViewById(R.id.inprogress_chip);
        chipApproval=(Chip)findViewById(R.id.approval_chip);
        ImageView imageViewAdd=(ImageView)findViewById(R.id.Resolution_imageview);

        imageViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResolutionCorrespondenceActivity.AddCorrespondenceDialog addCorrespondenceDialog=new ResolutionCorrespondenceActivity.AddCorrespondenceDialog();
                addCorrespondenceDialog.showDialog(ResolutionCorrespondenceActivity.this,"");
            }
        });


        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        toolbar.setTitle("Resolution Correspondence");
        recyclerViewResolution=(RecyclerView)findViewById(R.id.ResolutionCor_recyclerview);

        ResolutionCorrespondenceObjectsArrayList=new ArrayList<>();

        ResolutionCorrespondenceObjectsArrayList.add(new ResolutionCorrespondenceObject("232","DM01","Training1","393","Council Meeting","HIGH", "mazwitc@nkanglad"));
        ResolutionCorrespondenceObjectsArrayList.add(new ResolutionCorrespondenceObject("232","DM01","Training1","393","Council Meeting","HIGH", "mazwitc@nkanglad"));
        ResolutionCorrespondenceObjectsArrayList.add(new ResolutionCorrespondenceObject("232","DM01","Training1","393","Council Meeting","HIGH", "mazwitc@nkanglad"));
        ResolutionCorrespondenceObjectsArrayList.add(new ResolutionCorrespondenceObject("232","DM01","Training1","393","Council Meeting","HIGH", "mazwitc@nkanglad"));


        ResolutionCorrespondenceAdapter resolutionCorrespondenceAdapter=new ResolutionCorrespondenceAdapter(ResolutionCorrespondenceObjectsArrayList,ResolutionCorrespondenceActivity.this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerViewResolution.setLayoutManager(linearLayoutManager);
        recyclerViewResolution.setAdapter(resolutionCorrespondenceAdapter);


//        chipInProgress.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                recyclerViewResolution.setLayoutManager(linearLayoutManager);
//                recyclerViewResolution.setAdapter(resolutionCorrespondenceAdapter);
//            }
//        });
    }

    public class AddCorrespondenceDialog {

        public void showDialog(Activity activity, String msg) {
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
//            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            dialog.setContentView(R.layout.layout_for_add_resolution_register);
            ArrayAdapter<String> departmentadapter = new ArrayAdapter<String>(ResolutionCorrespondenceActivity.this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Deapartment));
            ArrayAdapter<String> sourceadapter = new ArrayAdapter<String>(ResolutionCorrespondenceActivity.this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Deapartment));
            ArrayAdapter<String> meetingTypeAdapter = new ArrayAdapter<String>(ResolutionCorrespondenceActivity.this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Meeting_Type));
            ArrayAdapter<String> priorityadapter = new ArrayAdapter<String>(ResolutionCorrespondenceActivity.this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Priority));

            Spinner spinnerDepartment = (Spinner)dialog.findViewById(R.id.department_spinner);
            Spinner spinnerSource= (Spinner)dialog.findViewById(R.id.source_spinner);
            Spinner spinnerMeetingType= (Spinner)dialog.findViewById(R.id.meeting_type_spinner);

            Spinner spinnerPriority = (Spinner)dialog.findViewById(R.id.priority_spinner);

            spinnerDepartment.setAdapter(departmentadapter);
            spinnerSource.setAdapter(sourceadapter);
            spinnerMeetingType.setAdapter(meetingTypeAdapter);
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