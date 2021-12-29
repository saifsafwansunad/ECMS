package com.ecms.ndmecms.Adapters;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.ecms.ndmecms.Activity.CommittiMeetingFilesActivity;
import com.ecms.ndmecms.ApiResponse.MeetingAttachment;
import com.ecms.ndmecms.DatabaseHelperClass;
import com.ecms.ndmecms.Models.EmployeeModelClass;
import com.ecms.ndmecms.R;
import com.google.android.material.button.MaterialButton;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static android.content.Context.DOWNLOAD_SERVICE;

public class MeetingAttachmentAdapter extends RecyclerView.Adapter<MeetingAttachmentAdapter.ViewHolder> {

    Activity context;
    List<MeetingAttachment> meetingAttachments;

    public MeetingAttachmentAdapter(Activity context, List<MeetingAttachment> meetingAttachments) {
        this.context = context;
        this.meetingAttachments = meetingAttachments;
    }

    @NonNull
    @NotNull
    @Override
    public MeetingAttachmentAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_for_attachments, parent, false);
        return new MeetingAttachmentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MeetingAttachmentAdapter.ViewHolder holder, int position) {
        if(!meetingAttachments.isEmpty()){
        holder.attachment_url_textview.setText(meetingAttachments.get(position).getFileUrl());
        holder.attachment_description_textview.setText(meetingAttachments.get(position).getDescription());}


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Downloaddialog downloaddialog=new Downloaddialog();
                downloaddialog.showDialog(context,meetingAttachments.get(position).getFileUrl());

              /*  final String website = meetingAttachments.get(position).getFileUrl();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/viewerng/viewer?url="+website));
                context.startActivity(browserIntent);*/
            }
        });

    }

    @Override
    public int getItemCount() {
        return (meetingAttachments != null) ? meetingAttachments.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView attachment_url_textview,attachment_description_textview;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            attachment_url_textview = itemView.findViewById(R.id.attachment_url_textview);
            attachment_description_textview = itemView.findViewById(R.id.attachment_description_textview);




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
                    if(ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
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

                        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                            ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
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
