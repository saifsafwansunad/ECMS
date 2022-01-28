package com.ecms.ndmecms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ecms.ndmecms.Activity.CommittiMeetingFilesActivity;
import com.ecms.ndmecms.Adapters.MeetingAttachmentAdapter;
import com.ecms.ndmecms.Adapters.ToAttendMeetingsAdapter;
import com.ecms.ndmecms.ApiResponse.MeetingAttachment;
import com.ecms.ndmecms.ApiResponse.ToAttendMeetingResponse;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class AttendMeetingDetailsActivity extends AppCompatActivity {
    AlertDialog.Builder builder;
    ToAttendMeetingResponse meeting;
    TextView title_toattend,meeting_type_toattend, agenda_toattend, meet_detail_description, date_time_toattend, isMSteam__toattend_textview, meeting_id_textview
            ,MSTeamMeetingJoinUrl_textview, MSTeamMeetingWebLink_textview,title,mt_downloadAll;
    RecyclerView meeting_details_attachment_recyclerview;
    ImageView backarrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meeting_attend_details_act);
        title=findViewById(R.id.title);

        title.setText("Meeting Details");

        builder = new AlertDialog.Builder(this);

      /*  getSupportActionBar().hide();
        Toolbar toolbarMeetingsdetail = (Toolbar)findViewById(R.id.meetngs_details_toolbar);
        toolbarMeetingsdetail.setTitle("Meeting To Attend");*/
        meeting = ToAttendMeetingsAdapter.meetingDetails;
//        ToAttendMeetingResponse meeting = getIntent().getParcelableExtra("Meetings");
//        Toast.makeText(this, meeting.getMeetingId(), Toast.LENGTH_SHORT).show();
        title_toattend = findViewById(R.id.title_toattend);
        meeting_type_toattend = findViewById(R.id.meeting_type_toattend);
        agenda_toattend = findViewById(R.id.agenda_toattend);
        meet_detail_description = findViewById(R.id.meet_detail_description);
        date_time_toattend = findViewById(R.id.date_time_toattend);
        isMSteam__toattend_textview = findViewById(R.id.isMSteam__toattend_textview);
        meeting_id_textview = findViewById(R.id.meeting_id_textview);
        MSTeamMeetingJoinUrl_textview = findViewById(R.id.MSTeamMeetingJoinUrl_textview);
        MSTeamMeetingWebLink_textview = findViewById(R.id.MSTeamMeetingWebLink_textview);
        meeting_details_attachment_recyclerview = findViewById(R.id.meeting_details_attachment_recyclerview);
        mt_downloadAll = findViewById(R.id.mt_downloadAll);
        backarrow=findViewById(R.id.imgBackArrow);


        MSTeamMeetingJoinUrl_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = meeting.getmSTeamMeetingJoinUrl();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
               // Toast.makeText(AttendMeetingDetailsActivity.this, "clicked", Toast.LENGTH_SHORT).show();
            }
        });

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title_toattend.setText(meeting.getTitle());
        meeting_type_toattend.setText(meeting.getMeetingType());
        agenda_toattend.setText(meeting.getAgenda());
        meet_detail_description.setText(meeting.getDescription());
        date_time_toattend.setText(meeting.getStartDate());
        isMSteam__toattend_textview.setText(meeting.getIsMSTeamMeeting());
        meeting_id_textview.setText(meeting.getmSTeamMeetingID());
        MSTeamMeetingJoinUrl_textview.setText(meeting.getmSTeamMeetingJoinUrl());
        MSTeamMeetingWebLink_textview.setText(meeting.getmSTeamMeetingWebLink());



        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(AttendMeetingDetailsActivity.this);

        meeting_details_attachment_recyclerview.setLayoutManager(layoutManager);
        List<MeetingAttachment> meetingAttachments;

        if(meeting.getMeetingAttachments() instanceof JsonPrimitive){
//            YourModelForData object = YourDataComponentForObject(data);
            // Do anything with Object
            meetingAttachments = new ArrayList<>();
        } else {
            meetingAttachments = YourDataComponentForArray(meeting.getMeetingAttachments());
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

        meeting_details_attachment_recyclerview.setAdapter(new MeetingAttachmentAdapter(AttendMeetingDetailsActivity.this, meetingAttachments));
//



    }

    private void downloadAll(List<MeetingAttachment> meetingAttachments){
        Toast.makeText(getApplication(), "Downloading Started", Toast.LENGTH_SHORT).show();
        for (MeetingAttachment attachment:meetingAttachments
        ) {
            String uri = attachment.FileUrl;

            try{

                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(AttendMeetingDetailsActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
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

    public static List<MeetingAttachment> convertObjectToList(Object obj) {
        List<MeetingAttachment> list = new ArrayList<>();
        if (obj.getClass().isArray()) {
            list = Arrays.asList((MeetingAttachment[])obj);
        } else if (obj instanceof Collection) {
            list = new ArrayList<MeetingAttachment>((Collection<MeetingAttachment>)obj);
        }
        return list;
    }
//    public YourModelForData YourDataComponentForObject(JsonElement data) {
//        Type type = new TypeToken<YourModelForData>() {
//        }.getType();
//        YourModelForData item = new Gson().fromJson(data, type);
//    }

    public List<MeetingAttachment> YourDataComponentForArray(JsonElement data) {
        Type type = new TypeToken<List<MeetingAttachment>>() {
        }.getType();
        List<MeetingAttachment> items = new Gson().fromJson(data, type);

        return  items;
    }
}