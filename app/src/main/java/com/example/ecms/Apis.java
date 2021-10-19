package com.example.ecms;

import com.example.ecms.ApiResponse.ToAttendMeetingResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Apis {

    @GET("login.ashx?")
    Call<List<UserLoginResponse>> userLogin(@Query("uname") String uname,
                                            @Query("upwd") String upwd);

    @GET("MeetingsByEmpId.ashx")
    Call<List<ToAttendMeetingResponse>> toAttendMeeting(@Query("empid") String empid);

}
