package com.example.ecms.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.ecms.CardAdapterInterface;
import com.example.ecms.CardItems;
import com.example.ecms.IncomingCorrespondenceActionActivity;
import com.example.ecms.IncomingCorrespondenceActivity;
import com.example.ecms.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerCardsAdapter extends PagerAdapter implements CardAdapterInterface {

    int calsBurned = 0;
    int calsConsumed = 0;
    int calsConsumed2 = 0;
    private List<CardView> mViews;
    private List<CardItems> mData;
    private float mBaseElevation;
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
        container.addView(view);
        bind(mData.get(position), view);
       CardView cardView = (CardView) view.findViewById(R.id.main_cardview);
TextView textViewName=(TextView)view.findViewById(R.id.corespondence_name_home_viewpager);

if (position==0){
    textViewName.setText("Incoming Correspondence");
}
if (position==1){
    textViewName.setText("Outgoing Correspondence");

}
if (position==2){
    textViewName.setText("Resolution Register");

}
if (position==3){
    textViewName.setText("Meeting Action");

}

        ProgressBar pieChart = view.findViewById(R.id.stats_progressbar);
        final LinearLayout linearLayoutCard=(LinearLayout)view.findViewById(R.id.cardbg_linearlayout);
        linearLayoutCard.setOnClickListener(new View.OnClickListener() {
            @Override
    public void onClick(View view) {
        //Toast.makeText(context, "Cardview", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(context, IncomingCorrespondenceActionActivity.class);
        context.startActivity(intent);
    }
});
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
        addConsumed(pieChart);
        addBurned(pieChart);
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
    public void addBurned(View v) {


        // Update the old value:
        calsBurned = 18;
        updateChart();
    }

    public void addConsumed(View v) {


        // Update the old value:
        calsConsumed = 6;
        updateChart();
    }

//    public void addConsumed2(View v) {
//
//
//        // Update the old value:
//        calsConsumed2 = 4;
//        updateChart();
//    }

    public void updateChart() {
        // Update the text in a center of the chart:
//        numberOfCals.setText(String.valueOf(calsBurned) + " / " + calsConsumed);

        // Calculate the slice size and update the pie chart:
        double d = (double) calsBurned / (double) calsConsumed;
        int progress = (int) (d * 100);
//        pieChart.setProgress(progress);
    }

}


