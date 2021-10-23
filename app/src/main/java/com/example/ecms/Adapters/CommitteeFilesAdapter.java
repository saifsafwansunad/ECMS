package com.example.ecms.Adapters;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecms.Activity.CommittiMeetingFilesActivity;
import com.example.ecms.ApiResponse.CommitteeFilesResponse;
import com.example.ecms.ApiResponse.ToAttendMeetingResponse;
import com.example.ecms.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;



public class CommitteeFilesAdapter extends RecyclerView.Adapter<CommitteeFilesAdapter.ViewHolder> {

    private List<CommitteeFilesResponse.FileSy> folderList;
    Activity context;
    private int count= 0;

    public CommitteeFilesAdapter(List<CommitteeFilesResponse.FileSy> folderList, Activity context) {
        this.folderList = folderList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_for_committee_folder, parent, false);
        return new CommitteeFilesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.folderName.setText(folderList.get(position).getName());
        if(folderList.get(position).getName().endsWith(".docx")){
            holder.file_icon.setImageDrawable(holder.docx);
            holder.file_icon.setMaxHeight(20);
            holder.file_icon.setMaxWidth(20);

        }else if(folderList.get(position).getName().endsWith(".pdf")){
            holder.file_icon.setImageDrawable(holder.pdf);
            holder.file_icon.setMaxHeight(20);
            holder.file_icon.setMaxWidth(20);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count == 0 && folderList.get(position).getName().endsWith(".pdf"))
                {
                    Toast.makeText(context, folderList.get(position).getName(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(context, folderList.get(position).getValue(), Toast.LENGTH_SHORT).show();
                }else if(count == 0 && folderList.get(position).getName().endsWith(".docx")){
                    Toast.makeText(context, folderList.get(position).getName(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(context, folderList.get(position).getValue(), Toast.LENGTH_SHORT).show();
                }else if(count == 0){
                    ((CommittiMeetingFilesActivity) v.getContext()).onClickCalled(folderList.get(position).getName());
                }
//                else{
////                    Toast.makeText(context, "you clicked " + String.valueOf(count) + " times" , Toast.LENGTH_SHORT).show();
//                }
                count =+1;

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
        private Drawable docx, pdf;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            folderName = itemView.findViewById(R.id.folder_name);
            file_icon = itemView.findViewById(R.id.file_icon);
            docx = itemView.getResources().getDrawable(R.drawable.icon_docx);
            pdf = itemView.getResources().getDrawable(R.drawable.ic_baseline_picture_as_pdf_24);
        }
    }
}
