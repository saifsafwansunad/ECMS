package com.ecms.ndmecms.Adapters;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ecms.ndmecms.Activity.OrganisationCalendarActivity;
import com.ecms.ndmecms.Activity.YourCalendarActivity;
import com.ecms.ndmecms.ApiClient;
import com.ecms.ndmecms.ApiRequests.ToAttendMeetingRequest;
import com.ecms.ndmecms.ApiResponse.ToAttendMeetingResponse;
import com.ecms.ndmecms.DetectConnection;
import com.ecms.ndmecms.MeetingActions_new;
import com.ecms.ndmecms.MeetingsFragment;
import com.ecms.ndmecms.PreferenceUtils;
import com.ecms.ndmecms.R;
import com.google.android.material.snackbar.Snackbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttendedMeetingsFragment extends Fragment {
    public static int countMeetings,attendedMeetings;

    RecyclerView recyclerViewToAttend;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AttendedMeetingsFragment() {
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
    public static AttendedMeetingsFragment newInstance(String param1, String param2) {
        AttendedMeetingsFragment fragment = new AttendedMeetingsFragment();
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

        View view=inflater.inflate(R.layout.attened_meetings, container, false);

        recyclerViewToAttend = view.findViewById(R.id.at_meetings_recyclerview);
        toAttendMeetingsCall();



        return view;
    }


    public final void toAttendMeetingsCall() {
        ToAttendMeetingRequest toAttendMeetingRequest = new ToAttendMeetingRequest();
        try{
            if(PreferenceUtils.getUid(getContext())!=null){
                toAttendMeetingRequest.setuId(PreferenceUtils.getUid(getContext()));
            }

        }catch (Exception e){
            e.printStackTrace();
        }



            Call<List<ToAttendMeetingResponse>> loginResponseCall = ApiClient.getUserService().toAttendMeeting(toAttendMeetingRequest.getuId());


            try {
                loginResponseCall.enqueue(new Callback<List<ToAttendMeetingResponse>>() {
                    @Override
                    public void onResponse(Call<List<ToAttendMeetingResponse>> call, Response<List<ToAttendMeetingResponse>> response) {

                        if (response.isSuccessful())
                            Toast.makeText(getContext(), "meetings success", Toast.LENGTH_SHORT).show();


                        if (response.body() != null && response.body().size() > 0) {
                            // tvNoMeetings.setVisibility(View.GONE);
                            recyclerViewToAttend.setVisibility(View.VISIBLE);
                            List<ToAttendMeetingResponse> meetingsList = response.body();
                            Collections.sort(meetingsList, Collections.reverseOrder());
                            List<ToAttendMeetingResponse> list;
                            if (meetingsList instanceof List)
                                list = (List)meetingsList;
                            else
                                list = new ArrayList(meetingsList);

                            //attended meeting needed to be sorted out from the list
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
                                       // list.remove(i);
                                    }

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                //     userLoginResponse = meetingsList.get(i);
                                //   data = dataArrayList.get(i).getId();
                                //here we need to take countmeetings value and send it to mainactivty for showing there
                            }
                            attendedMeetings=meetingsList.size()-countMeetings;

                            recyclerViewToAttend.setLayoutManager(new LinearLayoutManager(getContext()));
                            recyclerViewToAttend.setAdapter(new ToAttendMeetingsAdapter(getContext(),list));
                            Log.d("key of the message", "attended are for frag.... " + meetingsList.size()+"");

                        }
                        else {
                            recyclerViewToAttend.setVisibility(View.GONE);
                            //tvNoMeetings.setVisibility(View.VISIBLE);

                        }





                    }


                    @Override
                    public void onFailure(Call<List<ToAttendMeetingResponse>> call, Throwable t) {

                        Toast.makeText(getContext(), "Server Error " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                    }
                });

            } catch (Exception e) {

                e.printStackTrace();
            }
        }


}