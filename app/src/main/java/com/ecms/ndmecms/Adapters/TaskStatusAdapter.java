package com.ecms.ndmecms.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecms.ndmecms.Models.TaskStatusObjects;
import com.ecms.ndmecms.R;

import java.util.ArrayList;

public class TaskStatusAdapter extends RecyclerView.Adapter<TaskStatusAdapter.ViewHolder> {
    private ArrayList<TaskStatusObjects> taskStatusObjects;
    ImageView imageView;
    private Context context;


    public TaskStatusAdapter(ArrayList<TaskStatusObjects> taskStatusObjects, Context context) {
        this.taskStatusObjects = taskStatusObjects;
        this.context = context;
    }


    @NonNull
    @Override
    public TaskStatusAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_for_task_status, parent, false);

        return new TaskStatusAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskStatusAdapter.ViewHolder holder, int position) {

holder.textViewAssigned.setText(taskStatusObjects.get(position).getTaskAgginedText());
holder.textViewPercentage.setText(taskStatusObjects.get(position).getTaskPErcentageText());

    }


    @Override
    public int getItemCount() {
        return taskStatusObjects.size();
    }

//    public void removeItem(int position) {
//        cartObjects.remove(position);
//        notifyItemRemoved(position);
//    }
//    public void restoreItem(String item, int position) {
//        cartObjects.add(position,item );
//        notifyItemInserted(position);
//    }

    //    public ArrayList<CartObjects> getData() {
//        return cartObjects;
//    }
    class ViewHolder extends RecyclerView.ViewHolder {


        TextView textViewAssigned, textViewPercentage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAssigned = (TextView) itemView.findViewById(R.id.task_status_assigned_textview);
            textViewPercentage = (TextView) itemView.findViewById(R.id.task_status_percentage_textview);



        }
    }
}
