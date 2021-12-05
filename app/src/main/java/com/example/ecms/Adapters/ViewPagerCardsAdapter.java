package com.example.ecms.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.icu.lang.UCharacter;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.ecms.ApiClient;
import com.example.ecms.ApiRequests.ToAttendMeetingRequest;
import com.example.ecms.ApiResponse.ToAttendMeetingResponse;
import com.example.ecms.CardAdapterInterface;
import com.example.ecms.CardItems;
import com.example.ecms.IncomingCorrespondenceActionActivity;
import com.example.ecms.IncomingCorrespondenceActivity;
import com.example.ecms.LoginActivity;
import com.example.ecms.MainActivity;
import com.example.ecms.PreferenceUtils;
import com.example.ecms.R;
import com.example.ecms.ToAttendMeetingActivity;
import com.example.ecms.ui.MyService;
import com.example.ecms.ui.home.HomeFragment;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewPagerCardsAdapter extends PagerAdapter implements CardAdapterInterface {
    public static int countMeetings,attendedMeetings;
    //PIE CHART
    private int[] yData;
    private String[] xData = {"Need to attend", "attended" };
    PieChart pieChart;
    Description description;

    TextView correspondencename;
    private List<CardView> mViews;
    private List<CardItems> mData;
    private float mBaseElevation;
    String[] values = new String[] { "apple", "banana", "orange" };


    Context context;
    int lastPosition = -1;

    public ViewPagerCardsAdapter(Context context) {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
        this.context = context;
    }



    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.layout_for_cardviewpager, container, false);

        toAttendMeetingsCall();
        //correspondencename=view.findViewById(R.id.corespondence_name);
       /* String[] strArray3 = {"Incoming Correspondence","Outgoing Correspondence","Resolution Regiseter","Meetings"};
        for (int i = 0; i < strArray3.length; i++) {
            correspondencename.setText(strArray3[i]);
        }*/








        Log.d("countMeeting", String.valueOf(countMeetings));
        container.addView(view);
        bind(mData.get(position), view);
       CardView cardView = (CardView) view.findViewById(R.id.main_cardview);
TextView textViewName=(TextView)view.findViewById(R.id.corespondence_name_home_viewpager);
        TextView meetings_nu=(TextView)view.findViewById(R.id.number_meetings);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                boolean bolValue = (LoginActivity.checkValue());
                if(bolValue){
                    if (position==0){
                        textViewName.setText("Meeting");
                        meetings_nu.setText(String.valueOf(countMeetings));

                        //pie chart implementation for recordsmanager
                        pieChart = (PieChart)view.findViewById(R.id.idPieChart);

                        // pieChart.setDescription("Sales by employee (In Thousands $)");
                        // pieChart.setRotationEnabled(true);
                        // pieChart.setUsePercentValues(true);
                        pieChart.setHoleColor(Color.GREEN);
                        //pieChart.setCenterTextColor(Color.BLACK);
                        pieChart.setHoleRadius(0);
                        pieChart.setTransparentCircleAlpha(0);
                        pieChart.setRotationEnabled(false);

                        pieChart.setDescription(description);

                        LegendEntry l1=new LegendEntry("Attended Meetings", Legend.LegendForm.DEFAULT,10f,2f,null,Color.rgb(20,6,104) );
                        LegendEntry l2=new LegendEntry("Upcoming Meetings", Legend.LegendForm.CIRCLE,10f,2f,null, Color.rgb(220,220,220));

                        Legend l=pieChart.getLegend();
//                        l.setOrientation(Legend.LegendOrientation.VERTICAL);
                        l.setCustom(new LegendEntry[]{l1,l2});




                        // pieChart.setCenterText("Super Cool Chart");
                        //pieChart.setCenterTextSize(10);
                        //pieChart.setDrawEntryLabels(true);
                        //pieChart.setEntryLabelTextSize(20);
                        //More options just check out the documentation!

                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //Do something after 100ms

                                addDataSet();
                            }
                        }, 10000);



/*
                        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                            @Override
                            public void onValueSelected(Entry e, Highlight h) {



                                Toast.makeText(context.getApplicationContext(), "Employee " + yData + "\n" + "Sales: $" + yData + "K", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onNothingSelected() {

                            }
                        });
*/

                    }
                }
                else {
                    if (position == 0) {
                        textViewName.setText("Meeting");
                        meetings_nu.setText(String.valueOf(countMeetings));
                        pieChart = (PieChart)view.findViewById(R.id.idPieChart);

                        // pieChart.setDescription("Sales by employee (In Thousands $)");
                        // pieChart.setRotationEnabled(true);
                       // pieChart.setUsePercentValues(true);
                        pieChart.setHoleColor(Color.GREEN);

                        pieChart.setRotationEnabled(false);
                        //pieChart.setCenterTextColor(Color.BLACK);
                        pieChart.setHoleRadius(0);
                        pieChart.setTransparentCircleAlpha(0);
                        pieChart.setDescription(description);
//
//                        int [] color={ Color.rgb(100,221,23), Color.rgb(128,0,128),
//                                                  };

                        LegendEntry l1=new LegendEntry("Attended Meetings", Legend.LegendForm.DEFAULT,10f,2f,null,Color.rgb(20,6,104) );
                        LegendEntry l2=new LegendEntry("Upcoming Meetings", Legend.LegendForm.CIRCLE,10f,2f,null, Color.rgb(220,220,220));

                        Legend l=pieChart.getLegend();
//                        l.setOrientation(Legend.LegendOrientation.VERTICAL);
                        l.setCustom(new LegendEntry[]{l1,l2});





                        // pieChart.setCenterText("Super Cool Chart");
                        //pieChart.setCenterTextSize(10);
                        //pieChart.setDrawEntryLabels(true);
                        //pieChart.setEntryLabelTextSize(20);
                        //More options just check out the documentation!

                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //Do something after 100ms

                                addDataSet();
                            }
                        }, 10000);

/*
                        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                            @Override
                            public void onValueSelected(Entry e, Highlight h) {



                                Toast.makeText(context.getApplicationContext(), "Employee " + yData + "\n" + "Sales: $" + yData + "K", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onNothingSelected() {

                            }
                        });
*/

                    }


                    if (position == 1) {
                        textViewName.setText("Resolution Register");

                    }
                    if (position == 2) {
                        textViewName.setText("Incoming Correspondence");

                    }
                    if (position == 3) {
                        textViewName.setText("Outgoing Correspondence");

                    }
                }
            }
        }, 3000);


       // ProgressBar pieChart = view.findViewById(R.id.stats_progressbar);

//linearLayoutCard.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//        if (position==0){
//            linearLayoutCard.setBackgroundColor(Color.parseColor("#7e8ce0"));
//        }
//        if (position==1){
//            linearLayoutCard.setBackgroundColor(Color.parseColor("#4cd964"));
//
//        }
//        if (position==2){
//            linearLayoutCard.setBackgroundColor(Color.parseColor("#7e8ce0"));
//
//        }
//    }
//});
//        TextView numberOfCals =view.findViewById(R.id.number_of_calories);
     //   addConsumed(pieChart);
       // addBurned(pieChart);
//        addConsumed2(pieChart);

//        setAnimation(view,position);
        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(context, ItemsListActivity.class);
//                context.startActivity(intent);
            }
        });
        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mViews.set(position, cardView);


        return view;
    }

    private void addDataSet() {

        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        try{
            for(int i = 0; i < yData.length; i++){
                yEntrys.add(new PieEntry(yData[i] , i));



            }

            for(int i = 1; i < xData.length; i++){
                xEntrys.add(xData[i]);

            }
        }catch (Exception e){

        }
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
//                Log.d(TAG, "onValueSelected: Value select from chart.");
//                Log.d(TAG, "onValueSelected: " + e.toString());
//                Log.d(TAG, "onValueSelected: " + h.toString());

               Intent intent=new Intent(context,ToAttendMeetingActivity.class);
               context.startActivity(intent);
            }

            @Override
            public void onNothingSelected() {

            }
        });



        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Meeting Actions");

        pieDataSet.setSliceSpace(2);

        pieDataSet.setValueTextSize(14);

        pieDataSet.setValueTextSize(12);


        //add colors to dataset
//        ArrayList<Integer> colors = new ArrayList<>();
        int [] color={  Color.rgb(220,220,220),Color.rgb(20,66,104),
        };

        pieDataSet.setColors(color);
        pieDataSet.setValueTextColor(Color.WHITE);

        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        // legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        //create pie data object




        PieData pieData = new PieData(pieDataSet);
        pieData.setValueFormatter(new DefaultValueFormatter(0));
        pieChart.setData(pieData);

        pieChart.invalidate();
    }

    //    private void setAnimation(View viewToAnimate, int position)
//    {
//        // If the bound view wasn't previously displayed on screen, it's animated
//
//        if (position > lastPosition)
//        {
////            FlingAnimation animation = new FlingAnimation(viewToAnimate, DynamicAnimation.SCROLL_X);
//            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slid_from_right);
//            animation.setDuration(1000);
//            viewToAnimate.startAnimation(animation);
//            lastPosition = position;
//        }
//    }
    public void addCardItemS(CardItems item) {
        mViews.add(null);
        mData.add(item);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }

    private void bind(CardItems item, View view) {
//        ImageView sliderImage = (ImageView) view.findViewById(R.id.slider_imageview);
//        TextView contentTextView = (TextView) view.findViewById(R.id.slider_textview);

//        Picasso.get().load(item.getTitle()).into(sliderImage);
//        contentTextView.setText(item.getText());
    }
//    public void addBurned(View v) {
//
//
//        // Update the old value:
//        calsBurned = 18;
//        updateChart();
//    }
//
//    public void addConsumed(View v) {
//
//
//        // Update the old value:
//        calsConsumed = 6;
//        updateChart();
//    }

//    public void addConsumed2(View v) {
//
//
//        // Update the old value:
//        calsConsumed2 = 4;
//        updateChart();
//    }

//    public void updateChart() {
//        // Update the text in a center of the chart:
////        numberOfCals.setText(String.valueOf(calsBurned) + " / " + calsConsumed);
//
//        // Calculate the slice size and update the pie chart:
//        double d = (double) calsBurned / (double) calsConsumed;
//        int progress = (int) (d * 100);
////        pieChart.setProgress(progress);
//    }

    public final void toAttendMeetingsCall() {

        ToAttendMeetingRequest toAttendMeetingRequest = new ToAttendMeetingRequest();
        toAttendMeetingRequest.setuId(PreferenceUtils.getUid(context.getApplicationContext()));

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


                             yData= new int[]{countMeetings, attendedMeetings};


                            Log.d("count meetings", "are" + countMeetings);
                            Log.d("attendedMeetings", " attendedMeetings are" + attendedMeetings);

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


