package com.cst2335.finalproject.preferences;


import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferences {

    public static void save(Context context, String country, String from, String to) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPreferences", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("country",country);
        myEdit.putString("from", from);
        myEdit.putString("to", to);
        myEdit.apply();
    }

    public static String getCountry(Context context){
        SharedPreferences sh = context.getSharedPreferences("MySharedPreferences", MODE_PRIVATE);
        return sh.getString("country", "");
    }

    public static String getFromDate(Context context){
        SharedPreferences sh = context.getSharedPreferences("MySharedPreferences", MODE_PRIVATE);
        return sh.getString("from", "");
    }

    public static String getToDate(Context context){
        SharedPreferences sh = context.getSharedPreferences("MySharedPreferences", MODE_PRIVATE);
        return sh.getString("to", "");
    }
}

