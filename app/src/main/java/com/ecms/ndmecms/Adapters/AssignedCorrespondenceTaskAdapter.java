package com.ecms.ndmecms.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecms.ndmecms.FusionTestTitleActivity;
import com.ecms.ndmecms.Models.CorrespondenceTaskObject;
import com.ecms.ndmecms.R;

import java.util.ArrayList;

public class AssignedCorrespondenceTaskAdapter extends RecyclerView.Adapter<AssignedCorrespondenceTaskAdapter.ViewHolder> {

    private ArrayList<CorrespondenceTaskObject> correspondenceTaskObjectArrayList;

    private Context context;

    public AssignedCorrespondenceTaskAdapter(ArrayList<CorrespondenceTaskObject> correspondenceTaskObjectArrayList, Context context) {
        this.correspondenceTaskObjectArrayList = correspondenceTaskObjectArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_for_assigned_correspondence_task,parent,false);

        return new AssignedCorrespondenceTaskAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  AssignedCorrespondenceTaskAdapter.ViewHolder holder, int position) {

        //        final String item = String.valueOf(orderDetailsObjects.get(position));
        holder.textViewTitle.setText(correspondenceTaskObjectArrayList.get(position).getTitleText());
        holder.tvCorrespondence.setText(correspondenceTaskObjectArrayList.get(position).getCorrespondenceText());
        holder.tvTargetDate.setText(correspondenceTaskObjectArrayList.get(position).getTargetDateText());
        holder.tvExtendedDays.setText(correspondenceTaskObjectArrayList.get(position).getExtendedDaysText());
        holder.tvAssignedTo.setText(correspondenceTaskObjectArrayList.get(position).getAssignedToText());
        holder.tvCompletedPercentage.setText(correspondenceTaskObjectArrayList.get(position).getCompletedPercentageText());
        if (position%2==0){
            holder.linearLayoutList.setBackground(context.getResources().getDrawable(R.drawable.list_bg_white));

        }
        else {
            holder.linearLayoutList.setBackground(context.getResources().getDrawable(R.drawable.list_bg_grey));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, FusionTestTitleActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return correspondenceTaskObjectArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle,tvCorrespondence,tvTargetDate,tvExtendedDays, tvAssignedTo, tvCompletedPercentage;
        LinearLayout linearLayoutList;


        public ViewHolder(@NonNull  View itemView) {
            super(itemView);

            textViewTitle=(TextView)itemView.findViewById(R.id.title_textview);
            tvCorrespondence=(TextView)itemView.findViewById(R.id.correspondence_textview);
            tvTargetDate=(TextView)itemView.findViewById(R.id.target_date_textview);
            tvExtendedDays=(TextView)itemView.findViewById(R.id.extended_days_textview);
            tvAssignedTo=(TextView)itemView.findViewById(R.id.assigned_to_textview);
            tvCompletedPercentage=(TextView)itemView.findViewById(R.id.complete_percentage_textview);
            linearLayoutList=(LinearLayout)itemView.findViewById(R.id.list_linearlayout);
        }
    }
}
