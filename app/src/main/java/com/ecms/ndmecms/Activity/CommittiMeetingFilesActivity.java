package com.ecms.ndmecms.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.net.Uri;

import com.ecms.ndmecms.Adapters.CommitteeFilesAdapter;
import com.ecms.ndmecms.Adapters.FolderAdapter;
import com.ecms.ndmecms.ApiClient;
import com.ecms.ndmecms.ApiRequests.CommitteeFilesRequest;
import com.ecms.ndmecms.ApiResponse.CommitteeFilesResponse;
import com.ecms.ndmecms.DatabaseHelperClass;
import com.ecms.ndmecms.DetectConnection;
import com.ecms.ndmecms.Models.EmployeeModelClass;
import com.ecms.ndmecms.Models.FolderModel;
import com.ecms.ndmecms.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommittiMeetingFilesActivity extends AppCompatActivity {

    String folderPath;
    private String folderAddress;
    RecyclerView cf_folder_rv,cf_folder_path_rv;
    TextView title;
    ImageView backarrow;
    FrameLayout nofile;
    List<FolderModel> folderTrack = new ArrayList<FolderModel>();
    FolderAdapter folderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_committi_meeting_files);
        cf_folder_rv = findViewById(R.id.cf_folder_rv);
        cf_folder_path_rv = findViewById(R.id.cf_folder_path_rv);
        nofile = findViewById(R.id.no_files);

        Intent intent = getIntent();

         folderPath = intent.getStringExtra("folderPath");
        folderAddress = intent.getStringExtra("folderAddress");
        folderAddress = folderAddress+ "/" + folderPath;

        getSupportActionBar().hide();
//        Toolbar toolbartoAttend=(Toolbar)findViewById(R.id.commitee_folder_toolbar);
//        toolbartoAttend.setTitle(folderAddress);
        title=findViewById(R.id.title);
        backarrow=findViewById(R.id.imgBackArrow);

        title.setText(folderAddress);

        committifolders();
        folderTrack.add(new FolderModel(folderPath, folderAddress));
        folderAdapter = new FolderAdapter(folderTrack, CommittiMeetingFilesActivity.this);
        cf_folder_path_rv.setLayoutManager(new LinearLayoutManager(CommittiMeetingFilesActivity.this, LinearLayoutManager.HORIZONTAL, false));
        cf_folder_path_rv.setAdapter(folderAdapter);
        cf_folder_path_rv.smoothScrollToPosition(folderTrack.size()-1);
//        folderPathSet();

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(folderAddress.equals("Committees/" + folderPath)){
                    finish();
                }else if(folderAddress.equals("/PUBLIC SECTOR")) {

                    finish();
                }else if(folderAddress.equals("//PRIVATE SECTOR")) {

                    finish();
                }else
                {
//            Toast.makeText(this, "You cant go back right now", Toast.LENGTH_SHORT).show();
                    int lastIndexBackSlash = folderAddress.lastIndexOf('/');
                    folderTrack.remove(folderTrack.size()-1);
                    folderAdapter.notifyDataSetChanged();
                    folderAddress = folderAddress.substring(0, lastIndexBackSlash);
//            Toast.makeText(this, folderAddress, Toast.LENGTH_SHORT).show();
                    committifolders();
                }
            }
        });
    }

    private void folderPathSet() {

    }

    private final void committifolders() {

        CommitteeFilesRequest committeeFilesRequest = new CommitteeFilesRequest();
        committeeFilesRequest.setCFname(folderAddress);
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(CommittiMeetingFilesActivity.this);
        progressDialog.setMessage("Please Wait....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        View contextView = findViewById(android.R.id.content);

        if (!DetectConnection.checkInternetConnection(this)) {
            progressDialog.dismiss();
            Snackbar snackbar = Snackbar.make(contextView, "Sorry, No Internet", Snackbar.LENGTH_LONG);
            snackbar.setAction("Retry", new CommittiMeetingFilesActivity.TryAgainListener());

            snackbar.show();
        } else {
            Call<CommitteeFilesResponse> folderResponseCall = ApiClient.getUserService2().CFList(committeeFilesRequest.getCFname(), "json");


            try {
                folderResponseCall.enqueue(new Callback<CommitteeFilesResponse>() {
                    @Override
                    public void onResponse(Call<CommitteeFilesResponse> call, Response<CommitteeFilesResponse> response) {

                        if (response.isSuccessful()) {
//        Toast.makeText(AppointmentsActivity.this, "appointments got", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                            if (response.body() != null) {
                                nofile.setVisibility(View.GONE);
                                cf_folder_rv.setVisibility(View.VISIBLE);
                                CommitteeFilesResponse Files = response.body();
                                List<CommitteeFilesResponse.FileSy> folderList = Files.getFileSys();
                                if(folderList.isEmpty()){
                                    cf_folder_rv.setVisibility(View.GONE);
                                    nofile.setVisibility(View.VISIBLE);
                                }else{
                                    nofile.setVisibility(View.GONE);
                                    cf_folder_rv.setVisibility(View.VISIBLE);
                                }
                                cf_folder_rv.setLayoutManager(new LinearLayoutManager(CommittiMeetingFilesActivity.this));
                                cf_folder_rv.setAdapter(new CommitteeFilesAdapter(folderList, CommittiMeetingFilesActivity.this));
//                                Log.d("key of the message", "appointments are.... " + response.body());
//                                Toast.makeText(CommittiMeetingFilesActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(CommittiMeetingFilesActivity.this, "No Files", Toast.LENGTH_SHORT).show();
                                cf_folder_rv.setVisibility(View.GONE);
                                nofile.setVisibility(View.VISIBLE);


                            }

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(CommittiMeetingFilesActivity.this, "Folder fetch Failed", Toast.LENGTH_LONG).show();


                        }

                    }


                    @Override
                    public void onFailure(Call<CommitteeFilesResponse> call, Throwable t) {

                        progressDialog.dismiss();
//                        Toast.makeText(CommittiMeetingFilesActivity.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        Toast.makeText(CommittiMeetingFilesActivity.this, "No Files in the directory", Toast.LENGTH_LONG).show();
                        Log.d("Reason", t.getLocalizedMessage() );

                    }
                });

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

    }

    public class TryAgainListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // Code to undo the user's last action
            View contextView = findViewById(android.R.id.content);
            // Make and display Snackbar
            if (!DetectConnection.checkInternetConnection(CommittiMeetingFilesActivity.this)) {
                Snackbar snackbar = Snackbar.make(contextView, "Sorry, you're offline", Snackbar.LENGTH_LONG);
                snackbar.setAction("Retry",new CommittiMeetingFilesActivity.TryAgainListener());
                snackbar.show();
            } else {

                Snackbar.make(contextView, "Internet Connected", Snackbar.LENGTH_SHORT)
                        .show();
            }

        }
    }

    public void onClickCalled(String folderName) {
        // Call another acitivty here and pass some arguments to it.

        folderAddress = folderAddress + "/" + folderName;
        folderTrack.add(new FolderModel(folderName, folderAddress));
        cf_folder_path_rv.smoothScrollToPosition(folderTrack.size()-1);
        folderAdapter.notifyDataSetChanged();
//        Toast.makeText(this, folderAddress, Toast.LENGTH_SHORT).show();
        committifolders();

    }

    public void  onFolderPathCalled(List<FolderModel>folderModelsList){
        folderTrack.clear();
        folderTrack.addAll(folderModelsList);
        cf_folder_path_rv.smoothScrollToPosition(folderTrack.size()-1);
        folderAdapter.notifyDataSetChanged();
//        folderTrack = folderModelsList;
        folderAddress = folderTrack.get(folderTrack.size()-1).getFolderpath();

        committifolders();

    }

    public void onClickDownload(Activity context,String fileName) {
        // Call another acitivty here and pass some arguments to it.
//        Toast.makeText(this, fileName, Toast.LENGTH_SHORT).show();
        String Uri = "http://102.133.164.64/" + folderAddress + "/" + fileName ;
//        Toast.makeText(this, Uri, Toast.LENGTH_SHORT).show();
        Log.d("DownloadTest", Uri);
        CommittiMeetingFilesActivity.Downloaddialog downloaddialog = new Downloaddialog();
        downloaddialog.showDialog(context, Uri);

    }


    @Override
    public void onBackPressed() {
//        String firstAddr = "Committees/" + folderPath;
//        Toast.makeText(this, firstAddr, Toast.LENGTH_SHORT).show();
        if(folderAddress.equals("Committees/" + folderPath)){
            super.onBackPressed();
        }else if(folderAddress.equals("/PUBLIC SECTOR")) {

            super.onBackPressed();
        }else if(folderAddress.equals("//PRIVATE SECTOR")) {

            super.onBackPressed();
        }else
        {
//            Toast.makeText(this, "You cant go back right now", Toast.LENGTH_SHORT).show();
            folderTrack.remove(folderTrack.size()-1);
            folderAdapter.notifyDataSetChanged();
            int lastIndexBackSlash = folderAddress.lastIndexOf('/');
            folderAddress = folderAddress.substring(0, lastIndexBackSlash);
//            Toast.makeText(this, folderAddress, Toast.LENGTH_SHORT).show();
            committifolders();
        }

    }

    public class Downloaddialog {

        private URL doc_url=null;
        private String file_name;


        public void showDialog(Activity activity, String uri) {
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            dialog.setContentView(R.layout.layout_for_download);

         //   TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
          //  text.setText(msg);
            MaterialButton dialogButton = (MaterialButton) dialog.findViewById(R.id.cancel_dialog_btn);
            MaterialButton downloadButton = (MaterialButton) dialog.findViewById(R.id.download_btn);
            MaterialButton offlinedownload = (MaterialButton) dialog.findViewById(R.id.offlinedownload_btn);
            MaterialButton previewButton = (MaterialButton) dialog.findViewById(R.id.preview_btn);

            try {
                doc_url=new URL(uri);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            file_name=doc_url.getPath();
            file_name=file_name.substring(file_name.lastIndexOf('/')+1);

            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            //offline download implementation
            offlinedownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(CommittiMeetingFilesActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                        // this will request for permission when permission is not true
                    }else{
                        // Download code here
                        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(uri));
                        String title = URLUtil.guessFileName(uri, null, null);
                        Log.d("DownloadTest", Uri.parse(uri).toString());
                        Log.d("titlename", title);
                        request.setTitle(title);
                        request.setDescription("offline Downloading ...");
                        String cookie = CookieManager.getInstance().getCookie(uri);
                        request.addRequestHeader("cookie", cookie);
                       // request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, title);


                        DownloadManager downloadManager = (DownloadManager)activity.getSystemService(DOWNLOAD_SERVICE);
                        downloadManager.enqueue(request);


                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                //Alternative if you don't know filename
                                String fileName = URLUtil.guessFileName(uri, null, MimeTypeMap.getFileExtensionFromUrl(uri));
                                Log.d("filename in downloads", fileName);
                                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),fileName); // Set Your File Name
                                String path=null;
                                if (file.exists()) {
                                    path=file.getAbsolutePath();
                                    Log.d("filepath in downloads", path);


                                }
                                DatabaseHelperClass databaseHelperClass = new DatabaseHelperClass(activity);
                                EmployeeModelClass employeeModelClass = new EmployeeModelClass(path,title);
                                databaseHelperClass.addEmployee(employeeModelClass);




                                Toast.makeText(activity, "saved offline", Toast.LENGTH_SHORT).show();
                            }
                        }, 3000);







                    }

                }
            });

            previewButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final String website = uri;
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/viewerng/viewer?url="+uri));
                    activity.startActivity(browserIntent);
                    /*Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    activity.startActivity(browserIntent);*/

                  /*  File file=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS+'/')+file_name);
                    Uri uri1= FileProvider.getUriForFile(CommittiMeetingFilesActivity.this,"com.example.ecms"+".provider",file);
                    Intent i=new Intent(Intent.ACTION_VIEW);
                    i.setDataAndType(uri1,"application/pdf");
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    startActivity(i);*/

                }
            });

            downloadButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{

                        if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                            ActivityCompat.requestPermissions(CommittiMeetingFilesActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
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


                            DownloadManager downloadManager = (DownloadManager)activity.getSystemService(DOWNLOAD_SERVICE);
                            downloadManager.enqueue(request);

                            Toast.makeText(activity, "Downloading Started", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }


                    }catch (Exception e){
                        Toast.makeText(activity, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

            dialog.show();

        }
    }
}