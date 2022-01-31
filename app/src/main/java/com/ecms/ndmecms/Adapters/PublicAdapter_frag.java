package com.ecms.ndmecms.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecms.ndmecms.Activity.CommittiMeetingFilesActivity;
import com.ecms.ndmecms.ApiResponse.CommitteeFilesResponse;
import com.ecms.ndmecms.PublicFragment;
import com.ecms.ndmecms.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PublicAdapter_frag extends RecyclerView.Adapter<PublicAdapter_frag.ViewHolder> {

    private List<CommitteeFilesResponse.FileSy> folderList;
    Context context;
    private int count= 0;
    PublicFragment publicFragment;

    public PublicAdapter_frag(List<CommitteeFilesResponse.FileSy> folderList, Context context,PublicFragment fragment) {
        this.folderList = folderList;
        this.context = context;
        publicFragment=fragment;

    }

    @NonNull
    @NotNull
    @Override
    public PublicAdapter_frag.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_for_committee_folder, parent, false);
        return new PublicAdapter_frag.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PublicAdapter_frag.ViewHolder holder, int position) {
        holder.folderName.setText(folderList.get(position).getName());
        if(folderList.get(position).getName().endsWith(".docx")){
            holder.file_icon.setImageDrawable(context.getDrawable(R.drawable.icon_docx));
            holder.file_icon.setMaxHeight(20);
            holder.file_icon.setMaxWidth(20);

        }else if(folderList.get(position).getName().endsWith(".pdf")){
            holder.file_icon.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_picture_as_pdf_24));
            holder.file_icon.setMaxHeight(20);
            holder.file_icon.setMaxWidth(20);
        }else if(folderList.get(position).getName().endsWith(".xls") || folderList.get(position).getName().endsWith(".zip")){
            holder.file_icon.setImageDrawable(context.getDrawable(R.drawable.icon_file));
            holder.file_icon.setMaxHeight(20);
            holder.file_icon.setMaxWidth(20);

        }
        else {
            holder.file_icon.setImageDrawable(context.getDrawable(R.drawable.bfolder_icon));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(folderList.get(position).getName().endsWith(".pdf"))
                {
//                    Toast.makeText(context, folderList.get(position).getName(), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(context, folderList.get(position).getValue(), Toast.LENGTH_SHORT).show();
                    publicFragment.onClickDownload(context,folderList.get(position).getName());

                }else if(folderList.get(position).getName().endsWith(".docx")){
//                    Toast.makeText(context, folderList.get(position).getName(), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(context, folderList.get(position).getValue(), Toast.LENGTH_SHORT).show();
                    publicFragment.onClickDownload(context,folderList.get(position).getName());

                }else if(folderList.get(position).getName().endsWith(".xls") || folderList.get(position).getName().endsWith(".zip")){
//                    Toast.makeText(context, folderList.get(position).getName(), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(context, folderList.get(position).getValue(), Toast.LENGTH_SHORT).show();
                    publicFragment.onClickDownload(context,folderList.get(position).getName());

                }else if(count == 0){
                    publicFragment.onClickCalled(folderList.get(position).getName());
                    count =+1;
                }
//                else{
////                    Toast.makeText(context, "you clicked " + String.valueOf(count) + " times" , Toast.LENGTH_SHORT).show();
//                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return (folderList != null) ? folderList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView folderName;
        private ImageView file_icon;
        private Drawable docx, pdf, file, folderIC;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            folderName = itemView.findViewById(R.id.folder_name);
            file_icon = itemView.findViewById(R.id.file_icon);
//            docx = itemView.getResources().getDrawable(R.drawable.icon_docx);
//            pdf = itemView.getResources().getDrawable(R.drawable.ic_baseline_picture_as_pdf_24);
//            file = itemView.getResources().getDrawable(R.drawable.icon_file);
//            folderIC = itemView.getResources().getDrawable(R.drawable.ic_baseline_folder_24);
        }
    }
}
