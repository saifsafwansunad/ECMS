package com.ecms.ndmecms.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ecms.ndmecms.Adapters.AttendedMeetingsFragment;
import com.ecms.ndmecms.Adapters.ToAttendMeetingsAdapter;
import com.ecms.ndmecms.AdminCommittee;
import com.ecms.ndmecms.ApiClient;
import com.ecms.ndmecms.ApiRequests.ToAttendMeetingRequest;
import com.ecms.ndmecms.ApiResponse.ToAttendMeetingResponse;
import com.ecms.ndmecms.CommiteMeetingsAdapter;
import com.ecms.ndmecms.CommiteeMeetingModel;
import com.ecms.ndmecms.PreferenceUtils;
import com.ecms.ndmecms.R;

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

public class UpcomingMeetingsFragment1 extends Fragment {
    //AttendedMeetingsFragment and Upcoming meetings recycler implementations are reciprocal
    public static int countMeetings,attendedMeetings;

    RecyclerView recyclerViewToAttend;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UpcomingMeetingsFragment1() {
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

        View view=inflater.inflate(R.layout.upcoming_meetings1, container, false);

        recyclerViewToAttend = view.findViewById(R.id.upmeetings_recyclerview);
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



        Call<List<CommiteeMeetingModel>> loginResponseCall = ApiClient.getUserService().toPaticularCommitee(AdminCommittee.meetingDetails);


        try {
            loginResponseCall.enqueue(new Callback<List<CommiteeMeetingModel>>() {
                @Override
                public void onResponse(Call<List<CommiteeMeetingModel>> call, Response<List<CommiteeMeetingModel>> response) {

                    if (response.isSuccessful())
                        //   Toast.makeText(getContext(), "meetings success", Toast.LENGTH_SHORT).show();


                        if (response.body() != null && response.body().size() > 0) {
                            // tvNoMeetings.setVisibility(View.GONE);
                            recyclerViewToAttend.setVisibility(View.VISIBLE);
                            List<CommiteeMeetingModel> meetingsList = response.body();
                            Collections.sort(meetingsList, Collections.reverseOrder());
                            List <CommiteeMeetingModel> upcomingmeetings = new ArrayList<>();

                            List<CommiteeMeetingModel> list;
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
                            int list_size=meetingsList.size();
                            for (int i = 0; i < list_size; i++) {

                                String date_startDate= meetingsList.get(i).getStartDate();
                                try {
                                    Date date1=new SimpleDateFormat("MM/dd/yyyy").parse(date_startDate);

                                    if(date1.after(currentdate) | date1.compareTo(currentdate) == 0){
                                        countMeetings++;
                                    }else  upcomingmeetings.add(meetingsList.get(i));

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                            }
                            attendedMeetings=meetingsList.size()-countMeetings;
//                            Collections.sort(upcomingmeetings, Collections.reverseOrder());

                            recyclerViewToAttend.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recyclerViewToAttend.setAdapter(new CommiteMeetingsAdapter(getActivity(),upcomingmeetings));
//                            Log.d("key of the message", "appointments are.... " + response.body());

                        }
                        else {
                            recyclerViewToAttend.setVisibility(View.GONE);
                            //tvNoMeetings.setVisibility(View.VISIBLE);
                        }
                }


                @Override
                public void onFailure(Call<List<CommiteeMeetingModel>> call, Throwable t) {

//                    Toast.makeText(getContext(), "Server Error " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                }
            });

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

}
