package com.ecms.ndmecms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.ecms.ndmecms.Activity.ReportActivity;
import com.ecms.ndmecms.ui.ViewEmployeeActivity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Readyapprove_viewmeeting extends AppCompatActivity implements View.OnClickListener, approvemeeting.DialogListener {
//private Button approve,reject;
TextView layout_title,meetingtype,agenda,description,startDate,approveComment,approveDate,isMsteam,msTeamMeetingId,titletoolbar;
CommiteeMeetingModel meetingModel;
    ImageView backarrow,dots, offline;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readyapprove_viewmeeting);
        setTitle("View Meetings");
//        approve=findViewById(R.id.approve);
//        reject=findViewById(R.id.reject);
        layout_title=findViewById(R.id.title_com);
        titletoolbar=findViewById(R.id.title);
        getSupportActionBar().hide();
        titletoolbar.setText("Meeting Details");
        meetingtype=findViewById(R.id.meeting_type);
        agenda=findViewById(R.id.agenda);
        description=findViewById(R.id.description);
        startDate=findViewById(R.id.start_date);
        approveComment=findViewById(R.id.approve_comment);
        approveDate=findViewById(R.id.approve_date);
        isMsteam=findViewById(R.id.ismeeting);
        msTeamMeetingId=findViewById(R.id.meeting_id);
        meetingModel=CommiteMeetingsAdapter.meetingDetails;
        Toast.makeText(this, meetingModel.getMeetingId(), Toast.LENGTH_SHORT).show();

        layout_title.setText(meetingModel.getTitle());
        meetingtype.setText(meetingModel.getMeetingType());
        agenda.setText(meetingModel.getAgenda());
        description.setText(meetingModel.getDescription());
        startDate.setText(meetingModel.getStartDate());
        approveDate.setText(meetingModel.getStartDate());
        msTeamMeetingId.setText(meetingModel.getMSTeamMeetingID());
        isMsteam.setText(meetingModel.getIsMSTeamMeeting());
        backarrow=findViewById(R.id.imgBackArrow);
        dots=findViewById(R.id.dots_report1);
        offline=findViewById(R.id.offline1);

        dots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), dots);

//                MenuPopupHelper menuHelper = new MenuPopupHelper(UserMessages.this, (MenuBuilder) popupMenu.getMenu(), dots);
//                menuHelper.setForceShowIcon(true);
//                menuHelper.show();

                /*  The below code in try catch is responsible to display icons*/
                try {
                    Field[] fields = popupMenu.getClass().getDeclaredFields();
                    for (Field field : fields) {
                        if ("mPopup".equals(field.getName())) {
                            field.setAccessible(true);
                            Object menuPopupHelper = field.get(popupMenu);
                            Class<?> classPopupHelper = Class.forName(menuPopupHelper.getClass().getName());
                            Method setForceIcons = classPopupHelper.getMethod("setForceShowIcon", boolean.class);
                            setForceIcons.invoke(menuPopupHelper, true);
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Inflating popup menu from popup_menu.xml file
                popupMenu.getMenuInflater().inflate(R.menu.main, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        // Toast message on menu item clicked
                        switch (menuItem.getItemId())
                        {


                            case R.id.profile_m:
                                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                                startActivity(intent);
                                return true;
                            case R.id.report:
                                Intent reportIntent = new Intent(getApplicationContext(), ReportActivity.class);
                                startActivity(reportIntent);
                                return true;
                            case R.id.action_privacy:
                                Intent privacyIntent = new Intent(getApplicationContext(), PrivacyPolicyActivity.class);
                                startActivity(privacyIntent);
                                return true;
                            case R.id.logout_m:
                                PreferenceUtils.savePassword(null, getApplicationContext());
                                PreferenceUtils.saveEmail(null, getApplicationContext());
                                PreferenceUtils.saveUid(null, getApplicationContext());
                                // MyService.count = 0;

                                //trying to stop the service
              /*  stopService = true;
                Intent stopIntent = new Intent(getApplicationContext(), MyService.class);
                stopIntent.putExtra("service", "yes");
                stopIntent.setAction("stopService");
                getApplicationContext().startService(stopIntent);*/
                                final ProgressDialog progressDoalog;
                                progressDoalog = new ProgressDialog(getApplicationContext());
                                progressDoalog.setMessage("Logging Out....");
                                progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                progressDoalog.show();

                                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                                startActivity(i);
                                finish();
                                return true;
                        }


                        return true;
                    }
                });
                // Showing the popup menu
                popupMenu.show();
            }
        });

        offline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewEmployeeActivity.class);
                startActivity(intent);
            }
        });


        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//
//        reject.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                RejectMeeting dialogFragment = new RejectMeeting();
//
//
//                Bundle bundle = new Bundle();
//                bundle.putBoolean("notAlertDialog", true);
//
//                dialogFragment.setArguments(bundle);
//
//                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
//                if (prev != null) {
//                    ft.remove(prev);
//                }
//                ft.addToBackStack(null);
//
//
//                dialogFragment.show(ft, "dialog");
//
//            }
//        });
//
//        approve.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                approvemeeting dialogFragment = new approvemeeting();
//
//
//                Bundle bundle = new Bundle();
//                bundle.putBoolean("notAlertDialog", true);
//
//                dialogFragment.setArguments(bundle);
//
//                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
//                if (prev != null) {
//                    ft.remove(prev);
//                }
//                ft.addToBackStack(null);
//
//
//                dialogFragment.show(ft, "dialog");
//
//            }
//        });




    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onFinishEditDialog(String inputText) {

    }
}