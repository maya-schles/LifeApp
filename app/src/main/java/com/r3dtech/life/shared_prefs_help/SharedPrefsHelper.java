package com.r3dtech.life.shared_prefs_help;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

public class SharedPrefsHelper {
    private String SHARED_PREF_TAG;
    private Context context;

    public SharedPrefsHelper(String SHARED_PREF_TAG, Context context) {
        this.SHARED_PREF_TAG = SHARED_PREF_TAG;
        this.context = context;
    }

    public static String serialize(Serializable object) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(object);
        String res = new String(Base64.getEncoder().encode(bos.toByteArray()));
        os.close();
        return res;
    }

    public static Object deserialize(String data) throws IOException, ClassNotFoundException {
        if (data.equals("")) {
            return null;
        }
        byte[] bytes = data.getBytes();
        ByteArrayInputStream bis = new ByteArrayInputStream(Base64.getDecoder().decode(bytes));
        ObjectInputStream oInputStream = new ObjectInputStream(bis);
        Object res = oInputStream.readObject();
        oInputStream.close();
        return res;
    }

    public void write(Serializable obj, String tag) throws IOException{
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREF_TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        String data = serialize(obj);
        editor.putString(tag, data);
        editor.apply();
    }

    public Object read(String tag) throws IOException, ClassNotFoundException{
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREF_TAG, Context.MODE_PRIVATE);
        return deserialize(pref.getString(tag, ""));
    }
}
