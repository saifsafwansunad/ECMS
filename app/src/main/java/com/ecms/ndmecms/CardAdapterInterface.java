package com.ecms.ndmecms;

import androidx.cardview.widget.CardView;

public interface CardAdapterInterface {

    int MAX_ELEVATION_FACTOR = 8;

    float getBaseElevation();

    CardView getCardViewAt(int position);

    int getCount();
}