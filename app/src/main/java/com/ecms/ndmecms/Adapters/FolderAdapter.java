package com.ecms.ndmecms.Adapters;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecms.ndmecms.Activity.CommittiMeetingFilesActivity;
import com.ecms.ndmecms.Models.FolderModel;
import com.ecms.ndmecms.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.ViewHolder> {

    private List<FolderModel> folderList;
    Activity context;

    public FolderAdapter(List<FolderModel> folderList, Activity context) {
        this.folderList = folderList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_for_path, parent, false);
        return new FolderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.folderName.setText(folderList.get(position).getFoldername());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<FolderModel> out = new ArrayList<>();
                for(int i = 0; i <= position; i++) {
                    out.add(folderList.get(i));
                }
                ((CommittiMeetingFilesActivity) v.getContext()).onFolderPathCalled(out);

            }
        });
    }



    @Override
    public int getItemCount() {
        return (folderList != null) ? folderList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView folderName;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            folderName = itemView.findViewById(R.id.path_name);
        }
    }
}