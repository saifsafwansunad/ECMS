package com.ecms.ndmecms;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ecms.ndmecms.Activity.CommittiMeetingFilesActivity;

import java.util.List;

public class AdminCommittee extends RecyclerView.Adapter<AdminCommittee.MahasiswaViewHolder> {

    private List<ComitteeResponse> comitteeResponses;
    public static String meetingDetails;
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
        holder.txtNpm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, CommitteeMeetings.class));
                meetingDetails=comitteeResponses.get(position).getCommitteeId();

            }
        });
        holder.txtNama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CommittiMeetingFilesActivity.class);
                intent.putExtra("folderPath", comitteeResponses.get(position).getFolderPath() );
                intent.putExtra("folderAddress", "Committees" );
                context.startActivity(intent);
            }
        });

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
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    itemView.getContext().startActivity(new Intent(itemView.getContext(), CommitteeMeetings.class));
//                }
//            });



        }
    }
}