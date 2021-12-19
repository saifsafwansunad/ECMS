package com.ecms.ndmecms.calandar;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecms.ndmecms.Adapters.ToAttendMeetingsAdapter;
import com.ecms.ndmecms.ApiResponse.ToAttendMeetingResponse;
import com.ecms.ndmecms.AttendMeetingDetailsActivity;
import com.ecms.ndmecms.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EventRecyclerAdapter extends RecyclerView.Adapter<EventRecyclerAdapter.MyViewHolder> {

    Context context;
    List<ToAttendMeetingResponse> arrayList;

    public EventRecyclerAdapter(Context context, List<ToAttendMeetingResponse> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_events_rowlayout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventRecyclerAdapter.MyViewHolder holder, int position) {
        ToAttendMeetingResponse events = arrayList.get(position);
        holder.Event.setText(events.getTitle());
//        Toast.makeText(context, events.getStartDate(), Toast.LENGTH_SHORT).show();
        Log.d("timevaiane" ,events.getStartDate());
        holder.Time.setText(events.getAgenda());
        holder.Date.setText(events.getStartDate());
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(convertStringToDate(events.getStartDate()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToAttendMeetingsAdapter.meetingDetails = arrayList.get(position);
                Intent intent=new Intent(context, AttendMeetingDetailsActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra("Meetings", (Parcelable) toattendMeetingsModels.get(position));
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Event,Date,Time;
        Button delete;
        ImageButton setAlarm;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Event = itemView.findViewById(R.id.eventname);
            Date = itemView.findViewById(R.id.eventdate);
            Time   = itemView.findViewById(R.id.rowEvenTime);
//            setAlarm = itemView.findViewById(R.id.alarmmeBtn);
        }
    }

    private Date convertStringToDate(String dateInString){
        java.text.SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm aa", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(dateInString);

        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private Date convertStringToTime(String dateInString){
        java.text.SimpleDateFormat format = new SimpleDateFormat("kk:mm", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(dateInString);

        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return date;
    }



}
