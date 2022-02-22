package com.ecms.ndmecms;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ecms.ndmecms.Activity.OrganisationCalendarActivity;
import com.ecms.ndmecms.Activity.YourCalendarActivity;
import com.ecms.ndmecms.ApiRequests.ToAttendMeetingRequest;
import com.ecms.ndmecms.ApiResponse.ToAttendMeetingResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MeetingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeetingsFragment extends Fragment {
    public static int countMeetings,attendedMeetings;

    RelativeLayout getActivity,organisation_calendar,your_calendar;
    ImageView meeting,logo;
    TextView count_meet;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MeetingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Inbox.
     */
    // TODO: Rename and change types and number of parameters
    public static MeetingsFragment newInstance(String param1, String param2) {
        MeetingsFragment fragment = new MeetingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.inbox, container, false);

        count_meet=view.findViewById(R.id.attend_count);
       // toAttendMeetingsCall();

        meeting=(ImageView) view.findViewById(R.id.meeting_active);
        organisation_calendar=(RelativeLayout) view.findViewById(R.id.organisation_calendar);
        your_calendar=(RelativeLayout) view.findViewById(R.id.your_calendar);
        logo=view.findViewById(R.id.logo);
        logo.setAlpha(50);
        getActivity=view.findViewById(R.id.get_into);

        getActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MeetingActions_new.class);
                startActivity(intent);
            }
        });

        meeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MeetingActions_new.class);
                startActivity(intent);
            }
        });

        organisation_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OrganisationCalendarActivity.class);
                startActivity(intent);
            }
        });

        your_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), YourCalendarActivity .class);
                startActivity(intent);
            }
        });
        return view;
    }


    public final void toAttendMeetingsCall() {

        ToAttendMeetingRequest toAttendMeetingRequest = new ToAttendMeetingRequest();
        toAttendMeetingRequest.setuId(PreferenceUtils.getUid(getContext()));

        Log.d("key of the message", "size ");
//        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
//        Log.d("countMeeting1", toAttendMeetingRequest.getuId());




        Call<List<ToAttendMeetingResponse>> loginResponseCall = ApiClient.getUserService().toAttendMeeting(toAttendMeetingRequest.getuId());


        try {
            loginResponseCall.enqueue(new Callback<List<ToAttendMeetingResponse>>() {
                @Override
                public void onResponse(Call<List<ToAttendMeetingResponse>> call, Response<List<ToAttendMeetingResponse>> response) {

                    if (response.isSuccessful()) {
                        if (response.body() != null && response.body().size() > 0) {
                            List<ToAttendMeetingResponse> meetingsList = response.body();
                            int size=meetingsList.size();
                            Log.d("key of the message", "size " + size);
//                                Toast.makeText(MyService.this, "size " + size, Toast.LENGTH_SHORT).show();

                            Calendar c = Calendar.getInstance();
                            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm aa");
                            String getCurrentDateTime = sdf.format(c.getTime());
                            Date currentdate = null;
                            try {
                                currentdate = new SimpleDateFormat("MM/dd/yyyy").parse(getCurrentDateTime);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }


                            countMeetings=0;
                            attendedMeetings=0;
                            for (int i = 0; i < meetingsList.size(); i++) {
                                // if(meetingsList.get(i).startDate>=)
                                String date_startDate= meetingsList.get(i).startDate;
                                try {
                                    Date date1=new SimpleDateFormat("MM/dd/yyyy").parse(date_startDate);

                                    if(date1.after(currentdate) | date1.compareTo(currentdate) == 0){
                                        countMeetings++;
                                        Log.d("count meetings", "date1 is" + date1);


                                    }

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                //     userLoginResponse = meetingsList.get(i);

                                //   data = dataArrayList.get(i).getId();
                                //here we need to take countmeetings value and send it to mainactivty for showing there
                            }
                            attendedMeetings=meetingsList.size()-countMeetings;


                          //  yData= new int[]{countMeetings, attendedMeetings};


                            Log.d("count meetings", "are" + countMeetings);
                            Log.d("attendedMeetings", " attendedMeetings are" + attendedMeetings);
                            count_meet.setText(String.valueOf(countMeetings));

//                            Log.d("countMeeting2", String.valueOf(countMeetings));
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