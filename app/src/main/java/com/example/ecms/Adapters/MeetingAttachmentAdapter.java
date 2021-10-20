package com.example.ecms.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecms.ApiResponse.ToAttendMeetingResponse;
import com.example.ecms.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MeetingAttachmentAdapter extends RecyclerView.Adapter<MeetingAttachmentAdapter.ViewHolder> {

    Activity context;
    List<ToAttendMeetingResponse.MeetingAttachment> meetingAttachments;

    public MeetingAttachmentAdapter(Activity context, List<ToAttendMeetingResponse.MeetingAttachment> meetingAttachments) {
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
        holder.attachment_url_textview.setText(meetingAttachments.get(position).getFileUrl());
        holder.attachment_description_textview.setText(meetingAttachments.get(position).getDescription());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String website = meetingAttachments.get(position).getFileUrl();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(website));
                context.startActivity(browserIntent);
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
}
