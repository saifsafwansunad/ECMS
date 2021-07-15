package com.example.ecms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.ecms.Adapters.PublicAdapter;
import com.example.ecms.Models.PublicModels;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PublicActivity extends AppCompatActivity {
RecyclerView recyclerViewPublic;

ArrayList<PublicModels> publicModelsArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public);
        recyclerViewPublic=(RecyclerView)findViewById(R.id.recyclerivew_public);
publicModelsArrayList=new ArrayList<>();
publicModelsArrayList.add(new PublicModels("Folder 1","2 items"));
        publicModelsArrayList.add(new PublicModels("Folder 2","2 items"));
        publicModelsArrayList.add(new PublicModels("Folder 3","2 items"));
        publicModelsArrayList.add(new PublicModels("Folder 4","2 items"));
        publicModelsArrayList.add(new PublicModels("Folder 5","2 items"));
        publicModelsArrayList.add(new PublicModels("Folder 6","2 items"));
        publicModelsArrayList.add(new PublicModels("Folder 7","2 items"));
        publicModelsArrayList.add(new PublicModels("Folder 8","2 items"));
        publicModelsArrayList.add(new PublicModels("Folder 9","2 items"));

        PublicAdapter publicAdapter=new PublicAdapter(publicModelsArrayList,this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerViewPublic.setLayoutManager(linearLayoutManager);
        recyclerViewPublic.setAdapter(publicAdapter);

        File mediaStorageDir = new File(Environment.getExternalStorageDirectory(), "MyDirName3");

//        if (!mediaStorageDir.exists()) {
//            if (!mediaStorageDir.mkdirs()) {
//                Log.d("App", "failed to create directory");
//            }
//        }
    }
    private File getChildrenFolder(String path) {
        File dir = this.getFilesDir();
        List<String> dirs = new ArrayList<String>(Arrays.<String>asList(path.split("/")));
        for(int i = 0; i < dirs.size(); ++i) {
            dir = new File(dir, dirs.get(i)); //Getting a file within the dir.
            if(!dir.exists()) {
                dir.mkdir();
            }
        }
        return dir;
    }
}