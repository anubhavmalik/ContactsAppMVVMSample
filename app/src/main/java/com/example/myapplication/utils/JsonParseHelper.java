package com.example.myapplication.utils;

import com.example.myapplication.app.MyApplication;
import com.example.myapplication.data.models.Contact;
import com.example.myapplication.data.models.Message;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class JsonParseHelper {

    private static JsonParseHelper jsonParseHelper;

    public static JsonParseHelper getInstance(){
        if (jsonParseHelper==null){
            jsonParseHelper = new JsonParseHelper();
        }
        return jsonParseHelper;
    }

    public ArrayList<Message> getMessagesListFromJson(){
        return new ArrayList<>();
    }
    public ArrayList<Contact> getContactsListFromJson() {
        ArrayList<Contact> contactArrayList = new ArrayList<>();
        String json = loadJSONFromAsset();
        Gson gson = new Gson();
        if (json != null) {
            if (!json.isEmpty()) {
                try {
                    JSONArray jsonArray = new JSONArray(json);
                    for(int i = 0 ; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        contactArrayList.add(gson.fromJson(jsonObject.toString(), Contact.class));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return contactArrayList;
    }

    public String loadJSONFromAsset() {
        String json = "";
        try {
            InputStream is = MyApplication.getNonUiContext().getAssets().open("contacts.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
