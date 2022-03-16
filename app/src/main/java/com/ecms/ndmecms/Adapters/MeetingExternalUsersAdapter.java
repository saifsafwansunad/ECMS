package com.ecms.ndmecms.Adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecms.ndmecms.ApiResponse.MeetingExternalUsers;
import com.ecms.ndmecms.MeetingAttendee;
import com.ecms.ndmecms.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MeetingExternalUsersAdapter extends RecyclerView.Adapter<MeetingExternalUsersAdapter.ViewHolder> {

    Activity context;
    List<MeetingExternalUsers> meetingExternalUsers;

    public MeetingExternalUsersAdapter(Activity context, List<MeetingExternalUsers> meetingAttachments) {
        this.context = context;
        this.meetingExternalUsers = meetingAttachments;

    }

    @NonNull
    @NotNull
    @Override
    public MeetingExternalUsersAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_for_meeting_external_user, parent, false);
        return new MeetingExternalUsersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MeetingExternalUsersAdapter.ViewHolder holder, int position) {

//        Log.d("commitee", meetingExternalUsers.get(position).getPersonName());
//        Toast.makeText(context, meetingExternalUsers.size(), Toast.LENGTH_SHORT).show();

        holder.personName.setText(meetingExternalUsers.get(position).getPersonName());
        holder.personEmail.setText(meetingExternalUsers.get(position).getPersonEmail());
        holder.personMobile.setText(meetingExternalUsers.get(position).getPersonMobile());
//        holder.personName.setText("hello");

    }

    @Override
    public int getItemCount() {
        return (meetingExternalUsers != null) ? meetingExternalUsers.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView personName,personEmail,personMobile;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            personName = itemView.findViewById(R.id.personName);
            personEmail = itemView.findViewById(R.id.personEmail);
            personMobile = itemView.findViewById(R.id.personMobile);




        }
    }
}
