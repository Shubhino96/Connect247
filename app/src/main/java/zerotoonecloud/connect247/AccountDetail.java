package com.example.shubhangi.connect247;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class AccountDetail extends AppCompatActivity {
    TextView namusr;
    TextView usremail;
    private String urlsign_up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_detail);
        namusr=(TextView)findViewById(R.id.name);
        usremail=(TextView)findViewById(R.id.email);

    }
    private class DispDetail extends AsyncTask<Void,Void,Map<String,String>>
    {

        @Override
        protected Map<String, String> doInBackground(Void... params) {
            Map<String,String> amp=new HashMap<>();
            ApiUser au=new ApiUser();
            amp=au.fetchData(urlsign_up);
            return amp;
        }

        @Override
        protected void onPostExecute(Map<String, String> StringMap) {
            String nm=StringMap.get("Name");
            String em=StringMap.get("Email");
            namusr.setText(nm);
            usremail.setText(em);
            super.onPostExecute(StringMap);

        }
    }
}
