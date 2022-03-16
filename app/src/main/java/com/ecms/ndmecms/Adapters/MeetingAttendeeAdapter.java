package com.ecms.ndmecms.Adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecms.ndmecms.ApiResponse.MeetingAttachment;
import com.ecms.ndmecms.MeetingAttendee;
import com.ecms.ndmecms.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MeetingAttendeeAdapter extends RecyclerView.Adapter<MeetingAttendeeAdapter.ViewHolder>{

    Activity context;
    List<MeetingAttendee> meetingAttendees;

    public MeetingAttendeeAdapter(Activity context, List<MeetingAttendee> meetingAttachments) {
        this.context = context;
        this.meetingAttendees = meetingAttachments;
    }

    @NonNull
    @NotNull
    @Override
    public MeetingAttendeeAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_for_attendee, parent, false);
        return new MeetingAttendeeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MeetingAttendeeAdapter.ViewHolder holder, int position) {

//        Log.d("commitee", meetingAttendees.get(position).getAttendee());

        holder.attendee.setText(meetingAttendees.get(position).getAttendee());
        holder.attendee_comment.setText(meetingAttendees.get(position).getComments());
        holder.isAttended.setText(meetingAttendees.get(position).getIsAttended());

    }

    @Override
    public int getItemCount() {
        return (meetingAttendees != null) ? meetingAttendees.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView attendee,attendee_comment,isAttended;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            attendee = itemView.findViewById(R.id.attendee);
            attendee_comment = itemView.findViewById(R.id.attendee_comment);
            isAttended = itemView.findViewById(R.id.isAttended);




        }
    }
}
