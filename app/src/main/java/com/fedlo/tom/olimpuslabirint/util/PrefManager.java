package com.fedlo.tom.olimpuslabirint.util;

import android.content.Context;
import android.content.SharedPreferences;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;


public class PrefManager {

    public static PrefManager instance;

    private final String PREF_NAME = "olimpuslabirint";
    private final String BALANCE = "balance";
    private final String HEART = "heart";
    private final String BET = "bet";
    private final String isOnce = "one";
    private final String isOncebet = "onebet";
    private final String CURRENT_LEVEL = "level";
    private final String CURRENT_LEVELB = "level1";
    private final String MUTE = "mure";
    private final String SCORE = "score";
    private final String TIME = "time";

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private PrefManager(Context context) {
        pref = context.getSharedPreferences(PREF_NAME, 0);
        editor = pref.edit();
    }

    public static void init(Context context) {
        instance = new PrefManager(context);
    }

    public void saveBalance(int balance) {
        editor.putInt(BALANCE, balance);
        editor.commit();
    }

    public int getBalance() {
        return pref.getInt(BALANCE, 0);
    }

    public void saveHeart(int heart){
        editor.putInt(HEART, heart);
        editor.commit();
    }



    public int getHeart() {
        return pref.getInt(HEART, 0);
    }

    public void saveBet(int bet) {
        editor.putInt(BET, bet);
        editor.commit();
    }

    public int getBet() {
        return pref.getInt(BET, 0);
    }


    public void saveLevel(int level) {
        editor.putInt(CURRENT_LEVEL, level);
        editor.commit();
    }

    public int getLevel() {
        return pref.getInt(CURRENT_LEVEL, 1);
    }

    public void saveOnceLevel(boolean b) {
        editor.putBoolean(CURRENT_LEVELB, b);
        editor.commit();
    }

    public Boolean getOnceLevel() {
        return pref.getBoolean(CURRENT_LEVELB, true);
    }

    public void saveOnce(boolean b) {
        editor.putBoolean(isOnce, b);
        editor.commit();
    }

    public Boolean getOnce() {
        return pref.getBoolean(isOnce, true);
    }

    public void saveOnceBalance(boolean b) {
        editor.putBoolean(isOncebet, b);
        editor.commit();
    }

    public Boolean getOnceBalance() {
        return pref.getBoolean(isOncebet, true);
    }

    public void saveisMute(boolean b) {
        editor.putBoolean(MUTE, b);
        editor.commit();
    }

    public Boolean getIsMute() {
        return pref.getBoolean(MUTE, true);
    }


    public void saveList(ArrayList<Integer> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString("SAVED_LIST", json);
        editor.commit();
    }

    public ArrayList<Integer> getList() {
        Gson gson = new Gson();
        String json = pref.getString("SAVED_LIST",  null);
        Type type = new TypeToken<ArrayList<Integer>>() {}.getType();
        return gson.fromJson(String.valueOf(json), type);
    }

    public void saveScore(int level) {
        editor.putInt(SCORE, level);
        editor.commit();
    }

    public int getScore() {
        return pref.getInt(SCORE, 1);
    }

    public void saveTime(int time) {
        editor.putInt(TIME, time);
        editor.commit();
    }

    public int getTime() {
        return pref.getInt(TIME, 1);
    }
}
