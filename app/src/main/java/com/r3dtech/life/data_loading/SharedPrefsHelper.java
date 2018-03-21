package com.r3dtech.life.data_loading;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;
import java.io.Serializable;

public class SharedPrefsHelper implements SerializableDataHelper {
    private String SHARED_PREF_TAG;
    private Context context;

    public SharedPrefsHelper(String SHARED_PREF_TAG, Context context) {
        this.SHARED_PREF_TAG = SHARED_PREF_TAG;
        this.context = context;
    }

    public void write(Serializable obj, String tag) throws IOException{
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREF_TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        String data = SerializableDataHelper.serialize(obj);
        editor.putString(tag, data);
        editor.apply();
    }

    public Object read(String tag) throws IOException {
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREF_TAG, Context.MODE_PRIVATE);
        try {
            return SerializableDataHelper.deserialize(pref.getString(tag, ""));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't read object with tag "+tag+e.getMessage());
        }
    }
}
