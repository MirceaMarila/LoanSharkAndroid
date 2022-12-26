package com.example.loansharkfe.service.implementations;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesService {

    private final Context activity_context;
    private SharedPreferences prefs;

    public SharedPreferencesService(Context activity_context) {
        this.activity_context = activity_context;
    }

    public void postSharedPreferences(String key, String value){
        SharedPreferences.Editor edit;
        prefs=activity_context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        edit=prefs.edit();
        edit.putString(key, value);
        edit.apply();
    }

    public String getSharedPreferences(String key){
        prefs=activity_context.getSharedPreferences("myPrefs",Context.MODE_PRIVATE);
        return prefs.getString(key,"");
    }

    public void deleteSharedPreferences(String key){
        prefs.edit().remove(key).apply();
    }

}
