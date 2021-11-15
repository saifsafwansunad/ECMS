package com.example.ecms.calandar;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecms.ApiClient;
import com.example.ecms.ApiRequests.ToAttendMeetingRequest;
import com.example.ecms.ApiResponse.ToAttendMeetingResponse;
import com.example.ecms.PreferenceUtils;
import com.example.ecms.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalendarCustomView extends LinearLayout {

    ImageButton PreviouseButton,NextButton;
    TextView CurrentDate;
    GridView gridView;
    private static final int MAX_CALENDAR_Days = 42;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);
    SimpleDateFormat dateFormat2 = new SimpleDateFormat("MM/dd/yyyy",Locale.ENGLISH);
    SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM",Locale.ENGLISH);
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy",Locale.ENGLISH);
    Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
    Context context;
    List<ToAttendMeetingResponse> meetingsList = new ArrayList<>();
    List<ToAttendMeetingResponse> meetingsList2 = new ArrayList<>();

    public static final Hashtable<String, List<ToAttendMeetingResponse>> my_dict = new Hashtable<String, List<ToAttendMeetingResponse>>();
    public static final Hashtable<String, List<ToAttendMeetingResponse>> my_dictDay= new Hashtable<String, List<ToAttendMeetingResponse>>();
    List<Date> dateList = new ArrayList<>();
    AlertDialog alertDialog;
    MyGridAdapter adapter;
    int alarmYear, alarmMonth, alarmDay, alarmHour, alarmMinuit;

    public CalendarCustomView(Context context) {
        super(context);
    }

    public CalendarCustomView(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;

        IntializeUILayout();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                SetupCalendar();
            }
        }, 3000);

        PreviouseButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH,-1);
                SetupCalendar();

            }
        });

        NextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH,1);
                SetupCalendar();
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final String date = dateFormat2.format(dateList.get(position));
                if (my_dictDay.containsKey(date)) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setCancelable(true);
                    View showView = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_events_layout, null);
                    RecyclerView EventRV = (RecyclerView) showView.findViewById(R.id.EventsRV);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(showView.getContext());

                    EventRV.setLayoutManager(layoutManager);
                    EventRV.setHasFixedSize(true);

                    EventRecyclerAdapter eventRecyclerAdapter = new EventRecyclerAdapter(showView.getContext(), CollectEvent(date));
                    EventRV.setAdapter(eventRecyclerAdapter);
                    eventRecyclerAdapter.notifyDataSetChanged();
                    builder.setView(showView);
                    alertDialog = builder.create();
                    alertDialog.show();
                    alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            SetupCalendar();
                        }
                    });
                }
            }
        });

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String date = dateFormat2.format(dateList.get(position));

                if (my_dictDay.containsKey(date)) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setCancelable(true);
                    View showView = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_events_layout, null);
                    RecyclerView EventRV = (RecyclerView) showView.findViewById(R.id.EventsRV);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(showView.getContext());

                    EventRV.setLayoutManager(layoutManager);
                    EventRV.setHasFixedSize(true);

                    EventRecyclerAdapter eventRecyclerAdapter = new EventRecyclerAdapter(showView.getContext(), CollectEvent(date));
                    EventRV.setAdapter(eventRecyclerAdapter);
                    eventRecyclerAdapter.notifyDataSetChanged();
                    builder.setView(showView);
                    alertDialog = builder.create();
                    alertDialog.show();
                    alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            SetupCalendar();
                        }
                    });


//                    return true;
                }
                return true;
            }
        });
    }

    public CalendarCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private List<ToAttendMeetingResponse> CollectEvent(String date){
        Toast.makeText(context, date, Toast.LENGTH_SHORT).show();
        Toast.makeText(context, "Event Saved", Toast.LENGTH_SHORT).show();
        List<ToAttendMeetingResponse> meetingsListByDay = new ArrayList<>();
        meetingsListByDay = my_dictDay.get(date);
//        Log.d("calendarTroop3", "sizequeen" + meetingsList2.size());
        if(my_dictDay.containsKey(date)){
            Log.d("calendarTroop3", "sizequeen" + meetingsListByDay.size());
            Log.d("calendarTroop31", "sizequeen" + my_dictDay.get(date).size());
        }

        return meetingsListByDay;
    }


    private void IntializeUILayout(){

        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.calendar_layout,this);
        PreviouseButton = view.findViewById(R.id.previousBtn);
        NextButton = view.findViewById(R.id.nextBtn);
        CurrentDate = view.findViewById(R.id.current_Date);
        gridView = view.findViewById(R.id.gridview);
        toAttendMeetingCall();


    }

    private void SetupCalendar(){
        String StartDate = simpleDateFormat.format(calendar.getTime());
        CurrentDate.setText(StartDate);
        dateList.clear();
        Calendar monthCalendar = (Calendar)calendar.clone();
        monthCalendar.set(Calendar.DAY_OF_MONTH,1);
        int FirstDayOfMonth = monthCalendar.get(Calendar.DAY_OF_WEEK)-1;
        monthCalendar.add(Calendar.DAY_OF_MONTH,-FirstDayOfMonth);


        COllectEventsPerMonth(monthFormat.format(calendar.getTime()),yearFormat.format(calendar.getTime()));


        while (dateList.size() < MAX_CALENDAR_Days){
            dateList.add(monthCalendar.getTime());
            monthCalendar.add(Calendar.DAY_OF_MONTH,1);

        }
//        Log.d("today", String.valueOf(calendar.get(Calendar.DATE)));
        adapter = new MyGridAdapter(context,dateList,calendar,meetingsList2);
        gridView.setAdapter(adapter);


    }

    private void COllectEventsPerMonth(String Month,String Year){
//        eventsList.clear();
//        meetingsList2.clear();

//        dbOpenHelper = new DBOpenHelper(context);
//        Log.d("calendarTry", Month);
//        Log.d("calendarTry", Year);
//
//        SQLiteDatabase database = dbOpenHelper.getReadableDatabase();
//        Cursor cursor = dbOpenHelper.ReadEventpermonth(Month,Year,database);
//        while (cursor.moveToNext()){
//            String event = cursor.getString(cursor.getColumnIndex(DBStructure.EVENT));
//            String Time = cursor.getString(cursor.getColumnIndex(DBStructure.TIME));
//            String Date = cursor.getString(cursor.getColumnIndex(DBStructure.DATE));
//            String month = cursor.getString(cursor.getColumnIndex(DBStructure.MONTH));
//            String year = cursor.getString(cursor.getColumnIndex(DBStructure.YEAR));
//            Events events = new Events(event,Time,Date,month,year);
//            eventsList.add(events);
//
//        }
        meetingsList2 = my_dict.get(Month+Year);
//        Log.d("calendarTroop3", "sizequeen" + meetingsList2.size());
        if(my_dict.containsKey(Month+Year)){
            Log.d("calendarTroop3", "sizequeen" + meetingsList2.size());
            Log.d("calendarTroop31", "sizequeen" + my_dict.get(Month+Year).size());
        }
//        meetingsList2 = my_dict.get(Month+Year);
//        Log.d("calendarTroop3", "sizequeen" + meetingsList2.size());

    }
    public final void toAttendMeetingCall(){
        ToAttendMeetingRequest toAttendMeetingRequest = new ToAttendMeetingRequest();
        toAttendMeetingRequest.setuId(PreferenceUtils.getUid(context.getApplicationContext()));
        meetingsList.clear();
        my_dict.clear();
        my_dictDay.clear();
//ORganization Calender
        Call<List<ToAttendMeetingResponse>> loginResponseCall = ApiClient.getUserService().yearsplannerCalender(toAttendMeetingRequest.getuId());


        try {
            loginResponseCall.enqueue(new Callback<List<ToAttendMeetingResponse>>() {
                @Override
                public void onResponse(Call<List<ToAttendMeetingResponse>> call, Response<List<ToAttendMeetingResponse>> response) {

                    if (response.isSuccessful()) {
                        if (response.body() != null && response.body().size() > 0) {
                            meetingsList = response.body();
                            int size=meetingsList.size();
                            Log.d("calendarTry", "size " + size);
                            Toast.makeText(getContext(), "size " + size, Toast.LENGTH_SHORT).show();

                            Calendar c = Calendar.getInstance();
                            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm aa");
                            String getCurrentDateTime = sdf.format(c.getTime());
                            Date currentdate = new Date();

                            List<ToAttendMeetingResponse> meetings2 = new ArrayList<>();


                            for (int i = 0; i < meetingsList.size(); i++) {

                                // if(meetingsList.get(i).startDate>=)
                                String date_startDate= meetingsList.get(i).startDate;
                                try {
                                    Date date1=new SimpleDateFormat("MM/dd/yyyy HH:mm aa").parse(date_startDate);
                                    Log.d("calendarTroop1", monthFormat.format(date1.getTime())+yearFormat.format(date1.getTime()));
//                                    Log.d("calendarTry1", yearFormat.format(date1.getTime()));
                                    String MonthYear = monthFormat.format(date1.getTime())+yearFormat.format(date1.getTime());
                                    List<ToAttendMeetingResponse> meetings = new ArrayList<>();
                                    String MeetingDate = dateFormat2.format(date1.getTime());
//                                    if(meetings.isEmpty())
//                                    meetings.add(meetingsList.get(i));
//                                    my_dict.put(MonthYear, meetings);
//                                    Log.d("calendarTroop12", String.valueOf(meetings.size()));
//                                        Log.d("calendarTroop122", String.valueOf(my_dict.get(MonthYear).size()));

                                    if(my_dict.containsKey(MonthYear)){
                                        List<ToAttendMeetingResponse> appended = my_dict.get(MonthYear);
                                        appended.add(meetingsList.get(i));
                                        my_dict.remove(MonthYear);
                                        my_dict.put(MonthYear, appended);
                                        Log.d("calendarTroop12", String.valueOf(appended.size()));
                                        Log.d("calendarTroop122", String.valueOf(my_dict.get(MonthYear).size()));
//                                        Log.d("calendarTroop1", my_dict.get(MonthYear).get(0).getStartDate());
                                        Log.d("calendarTroop304", MonthYear);

                                    }else{
//                                        Log.d("calendarTroop22", String.valueOf(my_dict.get(MonthYear).size()));
                                        Log.d("calendarTroop305", MonthYear);
                                        List<ToAttendMeetingResponse> meet = new ArrayList<ToAttendMeetingResponse>();
                                        meet.add(meetingsList.get(i));
                                        my_dict.put(MonthYear, meet);
                                    }


                                    if(my_dictDay.containsKey(MeetingDate)){
                                        List<ToAttendMeetingResponse> appended = my_dictDay.get(MeetingDate);
                                        appended.add(meetingsList.get(i));
                                        my_dictDay.remove(MeetingDate);
                                        my_dictDay.put(MeetingDate, appended);

                                    }else{

                                        List<ToAttendMeetingResponse> meet = new ArrayList<ToAttendMeetingResponse>();
                                        meet.add(meetingsList.get(i));
                                        my_dictDay.put(MeetingDate, meet);
                                    }

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }


                            }



                            //upto here



                        }

                    }
                }




                @Override
                public void onFailure(Call<List<ToAttendMeetingResponse>> call, Throwable t) {

//                        Toast.makeText(MyService.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                }
            });

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
