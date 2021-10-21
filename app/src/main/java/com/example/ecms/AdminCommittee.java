package com.example.ecms;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ecms.ApiResponse.ToAttendMeetingResponse;
import com.example.ecms.Models.Admin;

import java.util.ArrayList;
import java.util.List;

public class AdminCommittee extends RecyclerView.Adapter<AdminCommittee.MahasiswaViewHolder> {

    private List<ComitteeResponse> comitteeResponses;
    public static ComitteeResponse meetingDetails;
    Activity context;


    public AdminCommittee(CommitteeManagement context, List<ComitteeResponse> dataList) {
        this.comitteeResponses = dataList;
        this.context = context;
    }

    @Override
    public AdminCommittee.MahasiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_commitee, parent, false);
        return new AdminCommittee.MahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdminCommittee.MahasiswaViewHolder holder, int position) {
        holder.txtNama.setText(comitteeResponses.get(position).getCommitteeName());
        holder.txtNpm.setText("Show Meetings");
        }

    @Override
    public int getItemCount() {
        return (comitteeResponses != null) ? comitteeResponses.size() : 0;
    }

    public class MahasiswaViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNama, txtNpm;

        public MahasiswaViewHolder(View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.broader);
            txtNpm = itemView.findViewById(R.id.show);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemView.getContext().startActivity(new Intent(itemView.getContext(), CommitteeMeetings.class));
                }
            });



        }
    }
}