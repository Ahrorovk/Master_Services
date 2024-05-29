package com.example.masterservices.util;


import android.content.Context;
import android.graphics.drawable.Drawable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    public String toMMDDYYYY(long date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm", Locale.getDefault());
        return sdf.format(new Date(date));
    }
}

