package com.ecms.ndmecms.ui;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ecms.ndmecms.Adapters.PublicAdapter_frag;
import com.ecms.ndmecms.Adapters.PublicFolderAdapter;
import com.ecms.ndmecms.ApiClient;
import com.ecms.ndmecms.ApiRequests.CommitteeFilesRequest;
import com.ecms.ndmecms.ApiResponse.CommitteeFilesResponse;
import com.ecms.ndmecms.DatabaseHelperClass;
import com.ecms.ndmecms.MeetingsFragment;
import com.ecms.ndmecms.Models.EmployeeModelClass;
import com.ecms.ndmecms.Models.FolderModel;
import com.ecms.ndmecms.PublicFragment;
import com.ecms.ndmecms.R;
import com.google.android.material.button.MaterialButton;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.DOWNLOAD_SERVICE;

public class PrivateFragment extends Fragment {

    String folderPath;
    private String folderAddress;
    RecyclerView cf_folder_rv,cf_folder_path_rv;
    TextView title;
    ImageView backarrow;
    List<FolderModel> folderTrack = new ArrayList<FolderModel>();
    PrivateFolderAdapter folderAdapter;

    ImageView meeting;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PrivateFragment() {


        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Sent.
     */
    // TODO: Rename and change types and number of parameters
    public static PrivateFragment newInstance(String param1, String param2) {
        PrivateFragment fragment = new PrivateFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.privatefragment,
                container, false);

        cf_folder_rv = view.findViewById(R.id.cf_folder_rv);
        cf_folder_path_rv = view.findViewById(R.id.cf_folder_path_rv);

        /*Intent intent = getIntent();

        folderPath = intent.getStringExtra("folderPath");
        folderAddress = intent.getStringExtra("folderAddress");*/
        folderAddress = ""+ "/" + "/PRIVATE SECTOR";

        //  getSupportActionBar().hide();
//        Toolbar toolbartoAttend=(Toolbar)findViewById(R.id.commitee_folder_toolbar);
//        toolbartoAttend.setTitle(folderAddress);
        title=view.findViewById(R.id.title);
        backarrow=view.findViewById(R.id.imgBackArrow);

        title.setText(folderAddress);

        committifolders();
        folderTrack.add(new FolderModel(folderAddress, folderAddress));
        folderAdapter = new PrivateFolderAdapter(folderTrack, getContext(),PrivateFragment.this);
        cf_folder_path_rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        cf_folder_path_rv.setAdapter(folderAdapter);

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(folderAddress.equals("//PRIVATE SECTOR")) {

                    requireActivity().onBackPressed();
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

/*
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Toast.makeText(getContext(), "back pressed", Toast.LENGTH_SHORT).show();

                // And when you want to go back based on your condition
                if(folderAddress.equals("/PUBLIC SECTOR")) {


                }else
                {
                    this.setEnabled(false);
                    requireActivity().onBackPressed();
//            Toast.makeText(this, "You cant go back right now", Toast.LENGTH_SHORT).show();
                    folderTrack.remove(folderTrack.size()-1);
                    folderAdapter.notifyDataSetChanged();
                    int lastIndexBackSlash = folderAddress.lastIndexOf('/');
                    folderAddress = folderAddress.substring(0, lastIndexBackSlash);
//            Toast.makeText(this, folderAddress, Toast.LENGTH_SHORT).show();
                    committifolders();
                }

            }
        };
*/

        //requireActivity().getOnBackPressedDispatcher().addCallback((LifecycleOwner) getContext(), callback);


        //  FloatingActionButton button = (FloatingActionButton) view.findViewById(R.id.floating_action_button);
/*        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                myDialog dialogFragment = new myDialog();
                dialogFragment.show(getActivity().getSupportFragmentManager(),"");
                Bundle bundle = new Bundle();
                bundle.putBoolean("notAlertDialog", true);

                dialogFragment.setArguments(bundle);            }
        });*/ return view;    }

    public void onClickCalled(String folderName) {
        // Call another acitivty here and pass some arguments to it.

        folderAddress = folderAddress + "/" + folderName;
        folderTrack.add(new FolderModel(folderName, folderAddress));
        folderAdapter.notifyDataSetChanged();
//        Toast.makeText(this, folderAddress, Toast.LENGTH_SHORT).show();
        committifolders();

    }

    public void onClickDownload(Context context, String fileName) {
        // Call another acitivty here and pass some arguments to it.
//        Toast.makeText(this, fileName, Toast.LENGTH_SHORT).show();
        String Uri = "http://102.133.164.64/" + folderAddress + "/" + fileName ;
//        Toast.makeText(this, Uri, Toast.LENGTH_SHORT).show();
        Log.d("DownloadTest", Uri);
        PrivateFragment.Downloaddialog downloaddialog = new PrivateFragment.Downloaddialog();
        downloaddialog.showDialog((Activity) context, Uri);

    }


    private final void committifolders() {

        CommitteeFilesRequest committeeFilesRequest = new CommitteeFilesRequest();
        committeeFilesRequest.setCFname(folderAddress);
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please Wait....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        // View contextView = findViewById(android.R.id.content);

    /*    if (!DetectConnection.checkInternetConnection(getContext())) {
            progressDialog.dismiss();
            Snackbar snackbar = Snackbar.make(contextView, "Sorry, No Internet", Snackbar.LENGTH_LONG);
            snackbar.setAction("Retry", new PublicFragment.TryAgainListener());

            snackbar.show();
        } else {*/
        Call<CommitteeFilesResponse> folderResponseCall = ApiClient.getUserService2().CFList(committeeFilesRequest.getCFname(), "json");


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
                            cf_folder_rv.setLayoutManager(new LinearLayoutManager(getContext()));
                            cf_folder_rv.setAdapter(new PrivateAdapter_frag(folderList, getContext(),PrivateFragment.this));
                            //Log.d("key of the message", "appointments are.... " + response.body());
                        }
                        else {
                            Toast.makeText(getContext(), "No Files", Toast.LENGTH_SHORT).show();

                        }

                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), "Folder fetch Failed", Toast.LENGTH_LONG).show();


                    }

                }


                @Override
                public void onFailure(Call<CommitteeFilesResponse> call, Throwable t) {

                    progressDialog.dismiss();
//                        Toast.makeText(CommittiMeetingFilesActivity.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    Toast.makeText(getContext(), "No Files in the directory", Toast.LENGTH_LONG).show();
                    Log.d("Reason", t.getLocalizedMessage() );

                }
            });

        } catch (Exception e) {

            e.printStackTrace();
        }
        //}

    }

    public void  onFolderPathCalled(List<FolderModel>folderModelsList){
        folderTrack.clear();
        folderTrack.addAll(folderModelsList);
        folderAdapter.notifyDataSetChanged();
//        folderTrack = folderModelsList;
        folderAddress = folderTrack.get(folderTrack.size()-1).getFolderpath();

        committifolders();

    }

/*
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
*/


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
                    if(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions((Activity) getContext(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
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

                        if(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                            ActivityCompat.requestPermissions((Activity) getContext(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
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