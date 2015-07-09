package com.plusbits.social.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Marc on 09/07/2015.
 */
public class FontsUtils {
    public static void setFont(String fontName, TextView textView, Context context){
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/" + fontName + ".ttf");
        textView.setTypeface(tf);
    }
}
