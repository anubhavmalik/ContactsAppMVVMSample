package com.example.myapplication.utils;

import android.util.Log;

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
    private static SharedPrefsUtils sharedPrefsUtils;
    private static String json = "";
    private static String messageJson = "";
    private static ArrayList<Message> messageArrayList = new ArrayList<>();
    private Gson gson = new Gson();

    public static JsonParseHelper getInstance() {
        if (jsonParseHelper == null) {
            jsonParseHelper = new JsonParseHelper();
            sharedPrefsUtils = new SharedPrefsUtils();
        }
        return jsonParseHelper;
    }

    public ArrayList<Message> getMessagesListFromJson() {
        return new ArrayList<>();
    }

    public ArrayList<Contact> getContactsListFromJson() {
        ArrayList<Contact> contactArrayList = new ArrayList<>();
        if (json.isEmpty()) {
            json = loadJSONFromAsset(true);
        }
        Gson gson = new Gson();
        if (json != null) {
            if (!json.isEmpty()) {
                try {
                    JSONArray jsonArray = new JSONArray(json);
                    for (int i = 0; i < jsonArray.length(); i++) {
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

    public String loadJSONFromAsset(boolean getContactsList) {
        String json = "";
        if (getContactsList) {
            try {
                InputStream is;
                is = MyApplication.getNonUiContext().getAssets().open("contacts.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                json = new String(buffer, "UTF-8");
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
        } else {
            json = sharedPrefsUtils.getStringPreference(MyApplication.getNonUiContext(), AppConstants.MESSAGES_KEY);
            if(json ==null){
                json="";
            }
        }
        return json;
    }

    public Contact getSpecificContactFromJson(int senderId) {
        ArrayList<Contact> list = getContactsListFromJson();
        for (Contact contact : list) {
            if (senderId == contact.getId()) {
                return contact;
            }
        }
        return null;
    }

    public void addJsonTextMessageToContact(Contact contact, String body, long timestamp) {
        if(getMessagesArrayList().size()==0) {
            messageArrayList = getMessagesArrayList();
        }
        messageArrayList.add(new Message(contact, body, timestamp));
        sharedPrefsUtils.setStringPreference(MyApplication.getNonUiContext(), AppConstants.MESSAGES_KEY, gson.toJson(messageArrayList));
    }

    public ArrayList<Message> getMessagesArrayList() {
        if (messageJson.isEmpty()) {
            messageJson = loadJSONFromAsset(false);
        }
        try {
            messageArrayList.clear();
            JSONArray jsonArray = new JSONArray(messageJson);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Log.d("TTTT", "Object added to list item : " + jsonObject.toString());
                messageArrayList.add(gson.fromJson(jsonObject.toString(), Message.class));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return messageArrayList;
    }
}
