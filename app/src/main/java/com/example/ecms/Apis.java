package com.example.ecms;

import com.example.ecms.ApiResponse.CommitteeFilesResponse;
import com.example.ecms.ApiResponse.ReportResponse;
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

    @GET("YearPlanner.ashx")
    Call<List<ToAttendMeetingResponse>> yearsPlanner();

    @GET("MeetingsByCommitteeId.ashx?")
    Call<List<CommiteeMeetingModel>> toPaticularCommitee(@Query("cmtid") String empid);

    @GET("YearPlanner.ashx")
    Call<List<ToAttendMeetingResponse>> yearsplannerCalender();

    @GET("Committees.ashx")
    Call<List<ComitteeResponse>> tocommitee(@Query("empid") String empid);

    @GET("App_Handler/FileSystem1.ashx")
    Call<CommitteeFilesResponse> CFList(@Query("CFName") String CFName,
                                        @Query("$format") String format);

    @GET("PasswordReset.ashx?")
    Call<List<PasswordResp>> changePassword(@Query("empid") String uid,
                                            @Query("newpwd") String upwd);

    @GET("ContactAdmin.ashx")
    Call<List<ReportResponse>> reportIssue(@Query("empid") String uid,
                                           @Query("contacttype") String contacttype,
                                           @Query("contactsubject") String contactsubject,
                                           @Query("contactmessage") String contactmessage);
}
