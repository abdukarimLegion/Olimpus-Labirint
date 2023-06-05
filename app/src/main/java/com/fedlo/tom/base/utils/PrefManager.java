package com.fedlo.tom.base.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;


public class PrefManager {

    public static PrefManager instance;

    private final String PREF_NAME = "gambling_game";
    private final String BALANCE = "balance";
    private final String HEART = "heart";

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private PrefManager(Context context) {
        pref = context.getSharedPreferences(PREF_NAME, 0);
        editor = pref.edit();
    }

    public static void init(Context context) {
        instance = new PrefManager(context);
    }

    public void saveColor(int color) {
        editor.putInt(BALANCE, color);
        editor.commit();
    }

    public int getColor() {
        return pref.getInt(BALANCE, Color.parseColor("#404040"));
    }

    public void saveHeart(int heart){
        editor.putInt(HEART, heart);
        editor.commit();
    }

    public int getHeart() {
        return pref.getInt(HEART, 0);
    }


}
