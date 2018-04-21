package com.r3dtech.life.data_loading;

import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public interface SerializableDataHelper {
    static String serialize(Serializable object) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(object);
        //String res = new String(Base64.getEncoder().encode(bos.toByteArray()));
        String res = Base64.encodeToString(bos.toByteArray(), Base64.DEFAULT);
        os.close();
        return res;
    }

    static Object deserialize(String data) throws IOException, ClassNotFoundException {
        if (data.equals("")) {
            return null;
        }
        byte[] bytes = data.getBytes();
        //ByteArrayInputStream bis = new ByteArrayInputStream(Base64.getDecoder().decode(bytes));
        ByteArrayInputStream bis = new ByteArrayInputStream(Base64.decode(data, Base64.DEFAULT));
        ObjectInputStream oInputStream = new ObjectInputStream(bis);
        Object res = oInputStream.readObject();
        oInputStream.close();
        return res;
    }

    void write(Serializable object, String tag) throws IOException;
    Object read(String tag) throws IOException;
}
