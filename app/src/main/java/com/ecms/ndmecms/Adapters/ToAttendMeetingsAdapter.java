package com.ecms.ndmecms.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecms.ndmecms.ApiResponse.ToAttendMeetingResponse;
import com.ecms.ndmecms.AttendMeetingDetailsActivity;
import com.ecms.ndmecms.R;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ToAttendMeetingsAdapter extends RecyclerView.Adapter<ToAttendMeetingsAdapter.ViewHolder> {
    private List<ToAttendMeetingResponse> toattendMeetingsModels;
    public static ToAttendMeetingResponse meetingDetails;
    Context context;

    public ToAttendMeetingsAdapter(Context context,List<ToAttendMeetingResponse> dataList) {
        this.toattendMeetingsModels = dataList;
        this.context = context;
    }



    @NonNull
    @NotNull
    @Override
    public ToAttendMeetingsAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.meeting, parent, false);
        return new ToAttendMeetingsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ToAttendMeetingsAdapter.ViewHolder holder, int position) {


        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy  hh:mm aa");
        String date = format.format(Date.parse(toattendMeetingsModels.get(position).getStartDate()));

        holder.textViewTitle.setText(toattendMeetingsModels.get(position).getTitle());
        holder.textViewmeetingType.setText(toattendMeetingsModels.get(position).getMeetingType());
        holder.textViewAgendatoAttend.setText(toattendMeetingsModels.get(position).getAgenda());
        holder.textViewDate.setText(date);
        holder.textViewIsMSTeam.setText(toattendMeetingsModels.get(position).getIsMSTeamMeeting());

        holder.viewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meetingDetails = toattendMeetingsModels.get(position);
                Intent intent=new Intent(context, AttendMeetingDetailsActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra("Meetings", (Parcelable) toattendMeetingsModels.get(position));
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return (toattendMeetingsModels != null) ? toattendMeetingsModels.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle, textViewmeetingType, textViewAgendatoAttend,textViewDate,textViewIsMSTeam;
        private TextView viewDetails;
        public ViewHolder(View itemView) {

            super(itemView);
            textViewTitle = itemView.findViewById(R.id.title_toattend);
            textViewmeetingType = itemView.findViewById(R.id.meeting_type_toattend);
            textViewAgendatoAttend = itemView.findViewById(R.id.agenda_toattend);
            textViewDate=itemView.findViewById(R.id.date_time_toattend);
            textViewIsMSTeam=itemView.findViewById(R.id.isMSteam__toattend_textview);
            viewDetails=itemView.findViewById(R.id.viewDetails);




        }
    }
}
