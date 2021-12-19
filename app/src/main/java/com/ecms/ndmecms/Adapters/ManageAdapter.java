package com.ecms.ndmecms.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ecms.ndmecms.Models.ManageModel;
import com.ecms.ndmecms.R;

import java.util.ArrayList;

public class ManageAdapter extends RecyclerView.Adapter<ManageAdapter.MahasiswaViewHolder> {


    private ArrayList<ManageModel> dataList;

    public ManageAdapter(ArrayList<ManageModel> dataList) {
        this.dataList = dataList;
    }

    @Override
    public ManageAdapter.MahasiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_manage, parent, false);
        return new ManageAdapter.MahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ManageAdapter.MahasiswaViewHolder holder, int position) {
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
        private TextView txtNama, txtNpm, txtNoHp,txtmessageto,created;

        public MahasiswaViewHolder(View itemView) {

            super(itemView);
            txtNama = itemView.findViewById(R.id.title_manage);
            txtNpm = itemView.findViewById(R.id.meeting_type);
            txtNoHp = itemView.findViewById(R.id.agenda);
            txtmessageto=itemView.findViewById(R.id.date_time);
            created=itemView.findViewById(R.id.created);




        }
    }
}