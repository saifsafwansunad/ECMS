package com.example.ecms;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ecms.ApiResponse.ToAttendMeetingResponse;
import com.example.ecms.ui.ReadyApproveAdapter;
import com.example.ecms.ui.ReadytoapproveMOdel;

import java.util.ArrayList;
import java.util.List;

public class CommiteMeetingsAdapter extends RecyclerView.Adapter<CommiteMeetingsAdapter.MahasiswaViewHolder> {
    public static CommiteeMeetingModel meetingDetails;

    private List<CommiteeMeetingModel> dataList;
    public static String meetingType,Title,Agenda,Description,StartDate,ApproveComment,ApprovedDAte,IsMsMeeting,MsTeamMeetingId;

    Activity context;

   // private ArrayList<CommiteeMeetingModel> dataList;

  /*  public CommiteMeetingsAdapter(ArrayList<CommiteeMeetingModel> dataList) {
        this.dataList = dataList;
    }*/

    public CommiteMeetingsAdapter(CommitteeMeetings committeeMeetings, List<CommiteeMeetingModel> commiteeMeetingModels) {
        this.dataList=commiteeMeetingModels;
        this.context=committeeMeetings;
    }

    @Override
    public CommiteMeetingsAdapter.MahasiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_comite, parent, false);
        return new CommiteMeetingsAdapter.MahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommiteMeetingsAdapter.MahasiswaViewHolder holder, int position) {
        holder.txtNama.setText(dataList.get(position).getTitle());
        holder.txtNpm.setText(dataList.get(position).getDescription());
        holder.txtNoHp.setText(dataList.get(position).getAgenda());
        holder.txtmessageto.setText(dataList.get(position).getStartDate());
        meetingType=dataList.get(position).getMeetingType();
        Title=dataList.get(position).getTitle();
        Agenda=dataList.get(position).getAgenda();
        Description=dataList.get(position).getDescription();
        StartDate=dataList.get(position).getStartDate();
        ApprovedDAte=dataList.get(position).getStartDate();
        MsTeamMeetingId=dataList.get(position).getMSTeamMeetingID();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meetingDetails = dataList.get(position);
                Intent intent=new Intent(context, Readyapprove_viewmeeting.class);

                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class MahasiswaViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNama, txtNpm, txtNoHp,txtmessageto;

        public MahasiswaViewHolder(View itemView) {

            super(itemView);
            txtNama = itemView.findViewById(R.id.Title);
            txtNpm = itemView.findViewById(R.id.meeting_type);
            txtNoHp = itemView.findViewById(R.id.agenda);
            txtmessageto=itemView.findViewById(R.id.date_time);
         /*   itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    meetingDetails = toattendMeetingsModels.get(position);

                    itemView.getContext().startActivity(new Intent(itemView.getContext(), Readyapprove_viewmeeting.class));
                }
            });*/


        }
    }
}