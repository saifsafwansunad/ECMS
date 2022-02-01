package com.ecms.ndmecms.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecms.ndmecms.Adapters.PublicFolderAdapter;
import com.ecms.ndmecms.Models.FolderModel;
import com.ecms.ndmecms.PublicFragment;
import com.ecms.ndmecms.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PrivateFolderAdapter extends RecyclerView.Adapter<PrivateFolderAdapter.ViewHolder> {

    private List<FolderModel> folderList;
    Context context;
    PrivateFragment privateFragment;

    public PrivateFolderAdapter(List<FolderModel> folderList, Context context) {
        this.folderList = folderList;
        this.context = context;
    }

    public PrivateFolderAdapter(List<FolderModel> folderTrack, Context context, PrivateFragment fragment) {
        this.folderList = folderList;
        this.context = context;
        privateFragment=fragment;

    }

    @NonNull
    @NotNull
    @Override
    public PrivateFolderAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_for_path, parent, false);
        return new PrivateFolderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PrivateFolderAdapter.ViewHolder holder, int position) {
        holder.folderName.setText(folderList.get(position).getFoldername());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<FolderModel> out = new ArrayList<>();
                for(int i = 0; i <= position; i++) {
                    out.add(folderList.get(i));
                }
                privateFragment.onFolderPathCalled(out);

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

