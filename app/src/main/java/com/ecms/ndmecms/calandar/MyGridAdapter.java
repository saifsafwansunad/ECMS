package com.ecms.ndmecms.calandar;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ecms.ndmecms.ApiResponse.ToAttendMeetingResponse;
import com.ecms.ndmecms.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MyGridAdapter extends ArrayAdapter {
    List<Date> dates ;
    Calendar currentDate;
    LayoutInflater inflater;
    List<ToAttendMeetingResponse> eventsList;

    public MyGridAdapter(Context context, List<Date> dates, Calendar currentDate, List<ToAttendMeetingResponse> eventsList) {
        super(context, R.layout.single_cell_layout);
        this.dates = dates;
        this.currentDate = currentDate;
        inflater = LayoutInflater.from(context);
        this.eventsList=eventsList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Date monthDate = dates.get(position);
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(monthDate);
        int dayNo = dateCalendar.get(Calendar.DAY_OF_MONTH);
        int displayMonth = dateCalendar.get(Calendar.MONTH)+1;
        int displayYear = dateCalendar.get(Calendar.YEAR);
        int currentYear = currentDate.get(Calendar.YEAR);
        int currentMonth = currentDate.get(Calendar.MONTH)+1;
        Calendar today = Calendar.getInstance();
        int todayDate = today.get(Calendar.DAY_OF_MONTH);
        int todayMonth = today.get(Calendar.MONTH)+1;
        int todayYear = today.get(Calendar.YEAR);




        View view = convertView;
        if(view == null){

            view = inflater.inflate(R.layout.single_cell_layout,parent,false);
        }



        if (displayMonth == currentMonth && displayYear==currentYear){
            view.setBackgroundColor(getContext().getResources().getColor(R.color.white));
            if (displayMonth == todayMonth && displayYear==todayYear && dayNo == todayDate ){
                view.setBackgroundColor(getContext().getResources().getColor(R.color.light_yellow));

            }

        }
        else {
            view.setBackgroundColor(Color.parseColor("#cccccc"));
            view.setClickable(true);
        }

        TextView cellNumber = view.findViewById(R.id.calendat_day);
        TextView eventText = view.findViewById(R.id.event_id);
        cellNumber.setText(String.valueOf(dayNo));
        Calendar eventCalendar = Calendar.getInstance();
        ArrayList<String> arrayList = new ArrayList<>();
        if(eventsList == null){

        }else {
//            Log.d("calendarTroop", eventsList.get(0).getStartDate());
            for (int i  = 0;i < eventsList.size();i++){
                eventCalendar.setTime(convertStringToDate(eventsList.get(i).getStartDate()));

                if(dayNo == eventCalendar.get(Calendar.DAY_OF_MONTH) && displayMonth == eventCalendar.get(Calendar.MONTH)+1
                        && displayYear == eventCalendar.get(Calendar.YEAR)){
                    arrayList.add(eventsList.get(i).getTitle());
                    eventText.setText(arrayList.size()+" events");




                }



            }

        }





        return view;
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

    @Override
    public int getCount() {
        return dates.size();
    }

    @Override
    public int getPosition(@Nullable Object item) {
        return dates.indexOf(item);
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return dates.get(position);
    }
}
