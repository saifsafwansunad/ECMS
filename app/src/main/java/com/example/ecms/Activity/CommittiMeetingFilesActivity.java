package com.example.ecms.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import android.widget.TextView;
import android.widget.Toast;
import android.net.Uri;

import com.example.ecms.Adapters.CommitteeFilesAdapter;
import com.example.ecms.Adapters.ToAttendMeetingsAdapter;
import com.example.ecms.ApiClient;
import com.example.ecms.ApiRequests.CommitteeFilesRequest;
import com.example.ecms.ApiRequests.ToAttendMeetingRequest;
import com.example.ecms.ApiResponse.CommitteeFilesResponse;
import com.example.ecms.ApiResponse.ToAttendMeetingResponse;
import com.example.ecms.DetectConnection;
import com.example.ecms.Fragments.CorrespondenceDetailFragment.CorrespondenceDetailsFragment;
import com.example.ecms.PreferenceUtils;
import com.example.ecms.R;
import com.example.ecms.ToAttendMeetingActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;

public class CommittiMeetingFilesActivity extends AppCompatActivity {

    String folderPath;
    private String folderAddress = "Committees";
    RecyclerView cf_folder_rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_committi_meeting_files);
        cf_folder_rv = findViewById(R.id.cf_folder_rv);

        Intent intent = getIntent();

         folderPath = intent.getStringExtra("folderPath");
        folderAddress = folderAddress+ "/" + folderPath;

        getSupportActionBar().hide();
        Toolbar toolbartoAttend=(Toolbar)findViewById(R.id.commitee_folder_toolbar);
        toolbartoAttend.setTitle("Committees/" + folderPath);

        committifolders();
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
            Call<CommitteeFilesResponse> folderResponseCall = ApiClient.getUserService2().CFList(committeeFilesRequest.getCFname());


            try {
                folderResponseCall.enqueue(new Callback<CommitteeFilesResponse>() {
                    @Override
                    public void onResponse(Call<CommitteeFilesResponse> call, Response<CommitteeFilesResponse> response) {

                        if (response.isSuccessful()) {
//        Toast.makeText(AppointmentsActivity.this, "appointments got", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                            if (response.body() != null) {
                                cf_folder_rv.setVisibility(View.VISIBLE);
                                CommitteeFilesResponse Files = response.body();
                                List<CommitteeFilesResponse.FileSy> folderList = Files.getFileSys();
                                cf_folder_rv.setLayoutManager(new LinearLayoutManager(CommittiMeetingFilesActivity.this));
                                cf_folder_rv.setAdapter(new CommitteeFilesAdapter(folderList, CommittiMeetingFilesActivity.this));
                                //Log.d("key of the message", "appointments are.... " + response.body());
                            }
                            else {
                                Toast.makeText(CommittiMeetingFilesActivity.this, "No Files", Toast.LENGTH_SHORT).show();

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
//        Toast.makeText(this, folderAddress, Toast.LENGTH_SHORT).show();
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
        }else{
//            Toast.makeText(this, "You cant go back right now", Toast.LENGTH_SHORT).show();
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
                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(uri));
                    String title = URLUtil.guessFileName(uri, null, null);
                    Log.d("DownloadTest", Uri.parse(uri).toString());
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
            });

            dialog.show();

        }
    }
}