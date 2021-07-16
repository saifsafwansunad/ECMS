package com.example.ecms.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.example.ecms.AdminMessagesAdapter;
import com.example.ecms.Models.Admin;
import com.example.ecms.R;
import com.example.ecms.Readyapprove_viewmeeting;

import java.util.ArrayList;

public class ReadyApproveAdapter extends RecyclerView.Adapter<ReadyApproveAdapter.MahasiswaViewHolder> {


    private ArrayList<ReadytoapproveMOdel> dataList;

    public ReadyApproveAdapter(ArrayList<ReadytoapproveMOdel> dataList) {
        this.dataList = dataList;
    }

    @Override
    public ReadyApproveAdapter.MahasiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_readytoapprove, parent, false);
        return new ReadyApproveAdapter.MahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReadyApproveAdapter.MahasiswaViewHolder holder, int position) {
        holder.txtNama.setText(dataList.get(position).getTitle());
        holder.txtNpm.setText(dataList.get(position).getMeetingtype());
        holder.txtNoHp.setText(dataList.get(position).getAgenda());
        holder.txtmessageto.setText(dataList.get(position).getDate());
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemView.getContext().startActivity(new Intent(itemView.getContext(), Readyapprove_viewmeeting.class));
                }
            });


        }
    }
}