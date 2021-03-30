package com.aya.ringcalendarapplication;

import android.content.Context;
import android.util.TypedValue;

public class Utils {

    public static float getDP(Context context, float value) {
        return TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, value,
                context.getResources().getDisplayMetrics() );
    }


}
