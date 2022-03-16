package com.ecms.ndmecms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.ecms.ndmecms.Activity.ReportActivity;
import com.ecms.ndmecms.Adapters.MeetingAttachmentAdapter;
import com.ecms.ndmecms.Adapters.MeetingAttendeeAdapter;
import com.ecms.ndmecms.Adapters.MeetingExternalUsersAdapter;
import com.ecms.ndmecms.ApiResponse.MeetingAttachment;
import com.ecms.ndmecms.ApiResponse.MeetingExternalUsers;
import com.ecms.ndmecms.ui.ViewEmployeeActivity;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Readyapprove_viewmeeting extends AppCompatActivity implements View.OnClickListener, approvemeeting.DialogListener {
//private Button approve,reject;
AlertDialog.Builder builder;
TextView layout_title,meetingtype,agenda,description,startDate,approveComment,approveDate,isMsteam,msTeamMeetingId,titletoolbar,mt_downloadAll;
CommiteeMeetingModel meetingModel;
    ImageView backarrow,dots, offline;
    RecyclerView meeting_details_attachment_recyclerview,meetingAttendees_recyclerview,meetingExternalUsers_recyclerview;


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
        meeting_details_attachment_recyclerview = findViewById(R.id.meeting_details_attachment_recyclerview);
        meetingAttendees_recyclerview = findViewById(R.id.meetingAttendees_recyclerview);
        meetingExternalUsers_recyclerview = findViewById(R.id.meetingExternalUsers_recyclerview);
        mt_downloadAll = findViewById(R.id.mt_downloadAll1);
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

        builder = new AlertDialog.Builder(this);

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
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Readyapprove_viewmeeting.this);

        meeting_details_attachment_recyclerview.setLayoutManager(layoutManager);
        List<MeetingAttachment> meetingAttachments;

        if(meetingModel.getMeetingAttachments() instanceof JsonPrimitive){
//            YourModelForData object = YourDataComponentForObject(data);
            // Do anything with Object
            meetingAttachments = new ArrayList<>();
        } else {
            meetingAttachments = YourDataComponentForArray(meetingModel.getMeetingAttachments());
            mt_downloadAll.setVisibility(View.VISIBLE);
            // Do anything with array
        }

        mt_downloadAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(AttendMeetingDetailsActivity.this, "DownloadAll", Toast.LENGTH_SHORT).show();
                //Setting message manually and performing action on button click
                builder.setMessage("Do you want to download all the files ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
//                                finish();
                                dialog.cancel();
                                downloadAll(meetingAttachments);
//                                Toast.makeText(getApplicationContext(),"you choose yes action for alertbox",
//                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
//                                Toast.makeText(getApplicationContext(),"you choose no action for alertbox",
//                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
//                alert.setTitle("AlertDownload");
                //2. now setup to change color of the button
                alert.setOnShowListener( new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface arg0) {
                        alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.dark_green));
                        alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.dark_green));
                    }
                });

                alert.show();

            }
        });

        meeting_details_attachment_recyclerview.setAdapter(new MeetingAttachmentAdapter(Readyapprove_viewmeeting.this, meetingAttachments));

        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(Readyapprove_viewmeeting.this);
        meetingAttendees_recyclerview.setLayoutManager(layoutManager1);
        List<MeetingAttendee> meetingAttendees;

        if(meetingModel.getMeetingAttendees() instanceof JsonPrimitive){
//            YourModelForData object = YourDataComponentForObject(data);
            // Do anything with Object
            meetingAttendees = new ArrayList<>();
        } else {
            meetingAttendees = YourDataComponentForArrayMA(meetingModel.getMeetingAttendees());
//            mt_downloadAll.setVisibility(View.VISIBLE);
            // Do anything with array
        }

        meetingAttendees_recyclerview.setAdapter(new MeetingAttendeeAdapter(Readyapprove_viewmeeting.this, meetingAttendees));

        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(Readyapprove_viewmeeting.this);
        meetingExternalUsers_recyclerview.setLayoutManager(layoutManager2);
        List<MeetingExternalUsers> meetingExternalUsers;

        if(meetingModel.getMeetingExternalUsers() instanceof JsonPrimitive){
//            YourModelForData object = YourDataComponentForObject(data);
            // Do anything with Object
            meetingExternalUsers = new ArrayList<>();
        } else {
            meetingExternalUsers = YourDataComponentForArrayMEU(meetingModel.getMeetingExternalUsers());
//            mt_downloadAll.setVisibility(View.VISIBLE);
            // Do anything with array

        }

//        for (MeetingAttendee e: meetingAttendees ){
//            Log.d("committee"+1, e.getAttendee());
//        }
//
//        for (MeetingExternalUsers eee: meetingExternalUsers){
//            Log.d("committee"+"i", eee.getPersonName());
//        }
//
//        List<MeetingExternalUsers> lis = new ArrayList<>();
//        lis.add(new MeetingExternalUsers("1", "pp", "pp","pp"));
//        lis.add(new MeetingExternalUsers("1", "pp", "pp","pp"));
//        lis.add(new MeetingExternalUsers("1", "pp", "pp","pp"));
//        lis.add(new MeetingExternalUsers("1", "pp", "pp","pp"));


        meetingExternalUsers_recyclerview.setAdapter(new MeetingExternalUsersAdapter(Readyapprove_viewmeeting.this, meetingExternalUsers));



    }




    @Override
    public void onClick(View v) {

    }

    @Override
    public void onFinishEditDialog(String inputText) {

    }

    private void downloadAll(List<MeetingAttachment> meetingAttachments){
        Toast.makeText(getApplication(), "Downloading Started", Toast.LENGTH_SHORT).show();
        for (MeetingAttachment attachment:meetingAttachments
        ) {
            String uri = attachment.FileUrl;

            try{

                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(Readyapprove_viewmeeting.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    // this will request for permission when permission is not true
                }else{
                    // Download code here
                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(uri));
                    String title = URLUtil.guessFileName(uri, null, null);
                    Log.d("DownloadTest", Uri.parse(uri).toString());
                    Log.d("titlename", title);


                    request.setTitle(title);
                    request.setDescription("Downloading File please wait.....");
                    String cookie = CookieManager.getInstance().getCookie(uri);
                    request.addRequestHeader("cookie", cookie);
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, title);


                    DownloadManager downloadManager = (DownloadManager)getApplication().getSystemService(DOWNLOAD_SERVICE);
                    downloadManager.enqueue(request);


                }


            }catch (Exception e){
                Toast.makeText(getApplication(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public List<MeetingAttachment> YourDataComponentForArray(JsonElement data) {
        Type type = new TypeToken<List<MeetingAttachment>>() {
        }.getType();
        List<MeetingAttachment> items = new Gson().fromJson(data, type);

        return  items;
    }

    private List<MeetingAttendee> YourDataComponentForArrayMA(JsonElement data) {

        Type type = new TypeToken<List<MeetingAttendee>>() {
        }.getType();
        List<MeetingAttendee> items = new Gson().fromJson(data, type);

        return  items;
    }

    private List<MeetingExternalUsers> YourDataComponentForArrayMEU(JsonElement data) {

        Type type = new TypeToken<List<MeetingExternalUsers>>() {
        }.getType();
        List<MeetingExternalUsers> items = new Gson().fromJson(data, type);

        return  items;
    }
}