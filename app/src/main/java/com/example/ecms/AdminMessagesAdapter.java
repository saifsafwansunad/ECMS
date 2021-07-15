package com.example.ecms;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ecms.Models.Admin;

import java.util.ArrayList;

public class AdminMessagesAdapter extends RecyclerView.Adapter<AdminMessagesAdapter.MahasiswaViewHolder> {


    private ArrayList<Admin> dataList;

    public AdminMessagesAdapter(ArrayList<Admin> dataList) {
        this.dataList = dataList;
    }

    @Override
    public MahasiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_admin, parent, false);
        return new MahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MahasiswaViewHolder holder, int position) {
        holder.txtNama.setText(dataList.get(position).getID());
        holder.txtNpm.setText(dataList.get(position).getMessageto());
        holder.txtNoHp.setText(dataList.get(position).getPriority());
        holder.txtmessageto.setText(dataList.get(position).getMessageto());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class MahasiswaViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNama, txtNpm, txtNoHp,txtmessageto;

        public MahasiswaViewHolder(View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.id);
            txtNpm = itemView.findViewById(R.id.title_label);
            txtNoHp = itemView.findViewById(R.id.priority_label);
            txtmessageto=itemView.findViewById(R.id.messageto_label);
        }
    }
}