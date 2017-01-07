package com.example.ulan.osm;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SplashActivity extends AppCompatActivity {

    DataHelper dataHelper;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        dataHelper=new DataHelper(this);
        progressBar=(ProgressBar) findViewById(R.id.progress);


    }

    public void onClick(View view) {
        progressBar.setVisibility(View.VISIBLE);
        try {
            dataHelper.deleteRoute();
            new ParseTask().execute();
        }catch (Exception e){

        }
    }

    public void onClick1(View view) {
        startActivity(new Intent(SplashActivity.this,MainActivity.class));
    }

    public class ParseTask extends AsyncTask<Void, Void, String> {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String jsonResult = "";

        @Override
        protected String doInBackground(Void... params) {

            try {

                URL url = new URL("https://routes-of-bishkek.herokuapp.com/api/v1/route/?format=json&offset=149");

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuilder builder = new StringBuilder();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }

                jsonResult = builder.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return jsonResult;
        }

        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);


            JSONObject dataJsonObject;
            String secondName;
            Log.e("TAGGG",json);
            try {
                dataJsonObject = new JSONObject(json);
                JSONArray menus = dataJsonObject.getJSONArray("objects");


                for (int i = 0; i < menus.length(); i++) {
                    JSONObject menu = menus.getJSONObject(i);
                    Log.e("TAG",menu.getDouble("lat")+"    "+menu.getDouble("longt")+"    "+menu.getString("price")+"    "+menu.getString("loc"));

                    dataHelper.addRoute(menu.getDouble("lat"),menu.getDouble("longt"),menu.getString("price"),menu.getString("loc"));



                }




            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.e("TAG_1","NORM");
            progressBar.setVisibility(View.GONE);


        }
    }


}