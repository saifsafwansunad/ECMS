package com.example.ecms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ecms.Fragments.myDialog;

public class Readyapprove_viewmeeting extends AppCompatActivity implements View.OnClickListener, approvemeeting.DialogListener {
private Button approve,reject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readyapprove_viewmeeting);
        setTitle("View Meetings");
        approve=findViewById(R.id.approve);
        reject=findViewById(R.id.reject);

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RejectMeeting dialogFragment = new RejectMeeting();


                Bundle bundle = new Bundle();
                bundle.putBoolean("notAlertDialog", true);

                dialogFragment.setArguments(bundle);

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);


                dialogFragment.show(ft, "dialog");

            }
        });

        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                approvemeeting dialogFragment = new approvemeeting();


                Bundle bundle = new Bundle();
                bundle.putBoolean("notAlertDialog", true);

                dialogFragment.setArguments(bundle);

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);


                dialogFragment.show(ft, "dialog");

            }
        });




    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onFinishEditDialog(String inputText) {

    }
}