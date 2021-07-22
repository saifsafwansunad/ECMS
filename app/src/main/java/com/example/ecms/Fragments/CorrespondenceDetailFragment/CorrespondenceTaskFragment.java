package com.example.ecms.Fragments.CorrespondenceDetailFragment;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.ecms.Adapters.IncomingCorrespondenceAdapter;
import com.example.ecms.IncomingCorrespondenceActivity;
import com.example.ecms.Models.IncomingCorrepondenceObjects;
import com.example.ecms.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class CorrespondenceTaskFragment extends Fragment {

    RecyclerView recyclerViewCorrespondenceTask;
    ArrayList<IncomingCorrepondenceObjects> incomingCorrepondenceObjectsArrayList;

    public CorrespondenceTaskFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_correspondence_task, container, false);


        recyclerViewCorrespondenceTask=(RecyclerView)view.findViewById(R.id.correspondence_task_recyclerview);
        Button BtnAssignTask=(Button) view.findViewById(R.id.assign_task_btn);

        BtnAssignTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CorrespondenceTaskFragment.AddCorrespondenceDialog addCorrespondenceDialog=new CorrespondenceTaskFragment.AddCorrespondenceDialog();
                addCorrespondenceDialog.showDialog(getActivity(),"");
            }
        });

        incomingCorrepondenceObjectsArrayList=new ArrayList<>();
        incomingCorrepondenceObjectsArrayList.add(new IncomingCorrepondenceObjects("345","10/12/2020","Updating of GRAP","Trevor Seagone","HIGH","recordsmanager"));
//        toolbar.setTitleTextColor(Color.parseColor("#000"));
        incomingCorrepondenceObjectsArrayList.add(new IncomingCorrepondenceObjects("345","10/12/2020","Updating of GRAP","Trevor Seagone","HIGH","recordsmanager"));
        incomingCorrepondenceObjectsArrayList.add(new IncomingCorrepondenceObjects("345","10/12/2020","Updating of GRAP","Trevor Seagone","HIGH","recordsmanager"));
        incomingCorrepondenceObjectsArrayList.add(new IncomingCorrepondenceObjects("345","10/12/2020","Updating of GRAP","Trevor Seagone","HIGH","recordsmanager"));
        incomingCorrepondenceObjectsArrayList.add(new IncomingCorrepondenceObjects("345","10/12/2020","Updating of GRAP","Trevor Seagone","HIGH","recordsmanager"));
        incomingCorrepondenceObjectsArrayList.add(new IncomingCorrepondenceObjects("345","10/12/2020","Updating of GRAP","Trevor Seagone","HIGH","recordsmanager"));

        IncomingCorrespondenceAdapter incomingCorrespondenceAdapter=new IncomingCorrespondenceAdapter(incomingCorrepondenceObjectsArrayList, getActivity());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerViewCorrespondenceTask.setLayoutManager(linearLayoutManager);
        recyclerViewCorrespondenceTask.setAdapter(incomingCorrespondenceAdapter);


        return view;
    }

    public class AddCorrespondenceDialog {

        public void showDialog(Activity activity, String msg) {
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
//            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            dialog.setContentView(R.layout.layout_for_assign_task);

            ArrayAdapter<String> priorityadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Priority));

            Spinner spinnerDepartment = (Spinner)dialog.findViewById(R.id.department_spinner);
            Spinner spinnerSource= (Spinner)dialog.findViewById(R.id.source_spinner);
            Spinner spinnerDocuThru = (Spinner)dialog.findViewById(R.id.document_throu_spinner);
            Spinner spinnerDocuType = (Spinner)dialog.findViewById(R.id.document_typre_spinner);
            Spinner spinnerPriority = (Spinner)dialog.findViewById(R.id.priority_spinner);

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