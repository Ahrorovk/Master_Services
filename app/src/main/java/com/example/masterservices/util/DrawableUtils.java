package com.example.masterservices.util;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class DrawableUtils {
    public static Drawable getDrawableFromString(Context context, String drawableString) {
        if (drawableString.startsWith("@drawable/")) {
            String resourceName = drawableString.substring("@drawable/".length());
            int resourceId = context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
            if (resourceId != 0) {
                return context.getDrawable(resourceId);
            }
        }
        return null;
    }
}
