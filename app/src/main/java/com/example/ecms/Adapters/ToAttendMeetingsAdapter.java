package com.example.ecms.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecms.Models.ManageModel;
import com.example.ecms.Models.ToattendMeetingsModel;
import com.example.ecms.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ToAttendMeetingsAdapter extends RecyclerView.Adapter<ToAttendMeetingsAdapter.ViewHolder> {
    private ArrayList<ToattendMeetingsModel> toattendMeetingsModels;
    public ToAttendMeetingsAdapter(ArrayList<ToattendMeetingsModel> dataList) {
        this.toattendMeetingsModels = dataList;
    }

    @NonNull
    @NotNull
    @Override
    public ToAttendMeetingsAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_for_atttendmeetings_list, parent, false);
        return new ToAttendMeetingsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ToAttendMeetingsAdapter.ViewHolder holder, int position) {
        holder.textViewTitle.setText(toattendMeetingsModels.get(position).getTitle());
        holder.textViewmeetingType.setText(toattendMeetingsModels.get(position).getMeetingtype());
        holder.textViewAgendatoAttend.setText(toattendMeetingsModels.get(position).getAgenda());
        holder.textViewDate.setText(toattendMeetingsModels.get(position).getStartDate());
        holder.textViewIsMSTeam.setText(toattendMeetingsModels.get(position).getIsMSTeamMeeting());

    }

    @Override
    public int getItemCount() {
        return (toattendMeetingsModels != null) ? toattendMeetingsModels.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle, textViewmeetingType, textViewAgendatoAttend,textViewDate,textViewIsMSTeam;

        public ViewHolder(View itemView) {

            super(itemView);
            textViewTitle = itemView.findViewById(R.id.title_toattend);
            textViewmeetingType = itemView.findViewById(R.id.meeting_type_toattend);
            textViewAgendatoAttend = itemView.findViewById(R.id.agenda_toattend);
            textViewDate=itemView.findViewById(R.id.date_time_toattend);
            textViewIsMSTeam=itemView.findViewById(R.id.isMSteam__toattend_textview);




        }
    }
}
