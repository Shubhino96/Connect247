package com.example.shubhangi.connect247;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by shubhangi on 22-10-2017.
 */

public class ApiUser {
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            jsonResponse = readFromStream(inputStream);
        } catch (IOException e) {
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                inputStream.close();
            }
        }
        Log.v("Shubhn",jsonResponse);
        return jsonResponse;
    }
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
    public Map<String,String>  fetchData(String requestUrl){
        URL url=null;
        try {
            url=new URL(requestUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
         String method_name;
         String user_name;
         String user_pass;
        String state;
        String city;
        String email;
        String aniversary_date; //YYYY/MM/DD
        String date_of_birth; //YYYY/MM/DD
        String blood_group;
        String otp;
        int married;  // 0-No, 1-Yes
        int user_type;


        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
        }
        Map<String,String> obj=new HashMap<>();
        try{

            JSONObject basejsonresponse = new JSONObject(jsonResponse);
            method_name=basejsonresponse.getString("method_name");
            user_name=basejsonresponse.getString("user_name");
            user_pass=basejsonresponse.getString("user_pass");
            state=basejsonresponse.getString("state");
            city=basejsonresponse.getString("city");
            email=basejsonresponse.getString("email");
            aniversary_date=basejsonresponse.getString("aniversary_date");
            date_of_birth=basejsonresponse.getString("date_of_birth");
            blood_group=basejsonresponse.getString("blood_group");
            otp=basejsonresponse.getString("otp");
            married=basejsonresponse.getInt("married");
            user_type=basejsonresponse.getInt("user_type");
            obj.put("Name",user_name);
            obj.put("Email",email);
            return obj;

        }
        //The value are assigned to these variables by parsing the json url.They can be used in the android code later.

        catch (JSONException e){

        }
        return obj;

    }
}
