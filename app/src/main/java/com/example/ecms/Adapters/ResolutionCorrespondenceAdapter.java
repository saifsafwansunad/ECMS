package com.example.ecms.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecms.CorrespondenceDetailsActivity;
import com.example.ecms.Models.IncomingCorrepondenceObjects;
import com.example.ecms.Models.ResolutionCorrespondenceObject;
import com.example.ecms.Models.TaskStatusObjects;
import com.example.ecms.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class ResolutionCorrespondenceAdapter extends RecyclerView.Adapter<ResolutionCorrespondenceAdapter.ViewHolder> {

    private ArrayList<ResolutionCorrespondenceObject> ResolutionCorrespondenceObjects;
    ImageView imageView;
    private Context context;


    public ResolutionCorrespondenceAdapter(ArrayList<ResolutionCorrespondenceObject> ResolutionCorrespondenceObjects, Context context) {
        this.ResolutionCorrespondenceObjects = ResolutionCorrespondenceObjects;
        this.context = context;
    }



    @NonNull
    @Override
    public ResolutionCorrespondenceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_for_resolution_correspondence,parent,false);

        return new ResolutionCorrespondenceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResolutionCorrespondenceAdapter.ViewHolder holder, int position) {

//        final String item = String.valueOf(orderDetailsObjects.get(position));
        holder.textViewAutoIndex.setText(ResolutionCorrespondenceObjects.get(position).getAutoIndexNumberText());
        holder.textViewIndex.setText(ResolutionCorrespondenceObjects.get(position).getIndexTNumberText());
        holder.textViewTitle.setText(ResolutionCorrespondenceObjects.get(position).getTitleText());
        holder.textViewResolutionNumber.setText(ResolutionCorrespondenceObjects.get(position).getResolutionNumber());
        holder.textViewMeetingType.setText(ResolutionCorrespondenceObjects.get(position).getMeetingType());
        holder.textViewPriority.setText(ResolutionCorrespondenceObjects.get(position).getPriorityText());
        holder.textViewCreatedBy.setText(ResolutionCorrespondenceObjects.get(position).getCreatedByText());
        if (position%2==0){
            holder.linearLayoutList.setBackground(context.getResources().getDrawable(R.drawable.list_bg_white));

        }
        else {
            holder.linearLayoutList.setBackground(context.getResources().getDrawable(R.drawable.list_bg_grey));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, CorrespondenceDetailsActivity.class);
                context.startActivity(intent);
            }
        });
        holder.materialButtonTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResolutionCorrespondenceAdapter.ViewDialog alert = new ResolutionCorrespondenceAdapter.ViewDialog();
                alert.showDialog(context, "");    }
        });
    }





    @Override
    public int getItemCount() {
        return ResolutionCorrespondenceObjects.size();
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


        TextView textViewAutoIndex,textViewIndex,textViewTitle,textViewPriority,textViewCreatedBy, textViewResolutionNumber, textViewMeetingType;
        LinearLayout linearLayoutList;
        MaterialButton materialButtonTask;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAutoIndex=(TextView)itemView.findViewById(R.id.autoindexno_textview);
            textViewIndex=(TextView)itemView.findViewById(R.id.index_textview);
            textViewTitle=(TextView)itemView.findViewById(R.id.title_textview);
            textViewPriority=(TextView)itemView.findViewById(R.id.prirotiy_textview);
            textViewCreatedBy=(TextView)itemView.findViewById(R.id.createdby_textview);
            textViewResolutionNumber=(TextView)itemView.findViewById(R.id.resolution_number_textview);
            textViewMeetingType=(TextView)itemView.findViewById(R.id.meeting_type_textview);
            linearLayoutList=(LinearLayout)itemView.findViewById(R.id.list_linearlayout);
            materialButtonTask=(MaterialButton)itemView.findViewById(R.id.task_button);






        }
    }


    public class ViewDialog {

        public void showDialog(Context activity, String msg){
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.task_status_dialog);
            ArrayList<TaskStatusObjects> taskStatusObjects;
            RecyclerView recyclerViewTask=(RecyclerView)dialog.findViewById(R.id.taskstatus_recyclerview);
            taskStatusObjects=new ArrayList<>();

            taskStatusObjects.add(new TaskStatusObjects("DMF","23"));
            taskStatusObjects.add(new TaskStatusObjects("DMF","23"));
            taskStatusObjects.add(new TaskStatusObjects("DMF","23"));

            TaskStatusAdapter taskStatusAdapter=new TaskStatusAdapter(taskStatusObjects, context);
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context,RecyclerView.VERTICAL,false);
            recyclerViewTask.setLayoutManager(linearLayoutManager);
            recyclerViewTask.setAdapter(taskStatusAdapter);

            Button dialogButton = (Button) dialog.findViewById(R.id.dialog_button);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();

        }
    }
}
