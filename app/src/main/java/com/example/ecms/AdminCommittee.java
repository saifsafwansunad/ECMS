package com.example.ecms;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ecms.Models.Admin;

import java.util.ArrayList;

public class AdminCommittee extends RecyclerView.Adapter<AdminCommittee.MahasiswaViewHolder> {


    private ArrayList<Commitee> dataList;

    public AdminCommittee(ArrayList<Commitee> dataList) {
        this.dataList = dataList;
    }

    @Override
    public AdminCommittee.MahasiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_commitee, parent, false);
        return new AdminCommittee.MahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdminCommittee.MahasiswaViewHolder holder, int position) {
        holder.txtNama.setText(dataList.get(position).getBroader());
        holder.txtNpm.setText(dataList.get(position).getShow());
        }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
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