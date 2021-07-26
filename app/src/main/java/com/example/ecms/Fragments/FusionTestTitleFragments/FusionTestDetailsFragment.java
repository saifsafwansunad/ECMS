package com.example.ecms.Fragments.FusionTestTitleFragments;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.example.ecms.Fragments.CorrespondenceDetailFragment.CorrespondenceDetailsFragment;
import com.example.ecms.R;
import com.google.android.material.button.MaterialButton;

public class FusionTestDetailsFragment extends Fragment {

    ImageView imageViewCorrTask;



    public FusionTestDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fusion_test_details, container, false);

        imageViewCorrTask=(ImageView)view.findViewById(R.id.assigned_task_btn);

        imageViewCorrTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FusionTestDetailsFragment.AssignedCorrespondentTaskDialog assignedCorrespondentTaskDialog = new FusionTestDetailsFragment.AssignedCorrespondentTaskDialog();
                assignedCorrespondentTaskDialog.showDialog(getActivity(),"");
            }
        });

        return view;
    }

    public static class AssignedCorrespondentTaskDialog {

        public void showDialog(Activity activity, String msg) {
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
//            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            dialog.setContentView(R.layout.layout_for_assigned_correspondent_task);

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