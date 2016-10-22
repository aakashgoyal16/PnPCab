package com.example.android.pnpcab;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Ride extends AppCompatActivity {
    String car = "", source = "", status = "booked";
    TextView e1, e2, e3;
    Calendar calander;
    static String result;

    SimpleDateFormat simpledateformat;
    String Date, des = "", req = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride);
        car = getIntent().getExtras().getString("car");
        source = getIntent().getExtras().getString("source");

        Toast.makeText(Ride.this, car + source, Toast.LENGTH_LONG).show();
        e1 = (TextView) findViewById(R.id.time1);
        e2 = (TextView) findViewById(R.id.car1);
        e3 = (TextView) findViewById(R.id.source1);
        calander = Calendar.getInstance();
        simpledateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date = simpledateformat.format(calander.getTime());
        e1.setText("BOOKING DATE/TIME " + Date);
        e2.setText("CAR SELECTED " + car);
        e3.setText("SOURCE " + source);
    }

    public void ridela(View v) {
        EditText f1 = (EditText) findViewById(R.id.des1);
        EditText f2 = (EditText) findViewById(R.id.req);

        des = f1.getText().toString();
        req = f2.getText().toString();

        boolean status=true;
        if(status) {
            result="";
            RegisterAsyn obj = new RegisterAsyn();
            obj.execute();

        }


    }

    public class RegisterAsyn extends AsyncTask<Void, Void, Void> //used for background operations like connectivity,uploading,downloading ,long tasks

    {

        @Override
        protected Void doInBackground(Void... params) {
            reg(Ride.this, des, source, Date, car, status);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(Ride.this, "reached here", Toast.LENGTH_LONG).show();
            Toast.makeText(Ride.this, result, Toast.LENGTH_LONG).show();
        }
    }

    static void reg(Context c, String des, String source, String Date, String car, String status, String req) {
        String serverURL = "http://aakashgoyal.96.lt/bookride2.php";
        Map<String, String> p = new HashMap<String, String>();
        p.put("source", source);
        //  p.put("lname",l);
        p.put("des", des);
        p.put("time", Date);
        p.put("car", car);
        p.put("status", status);
        p.put("req", req);
        //p.put("qual",qual);
        //p.put("age",age);
        //p.put("exp", exp);
        post(serverURL, p);
    }

    public static void post(String serverurl, Map<String, String> params) {
        URL u = null;
        try {

            u = new URL(serverurl);

        } catch (MalformedURLException e) {

        }

        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> p = it.next();
            sb.append(p.getKey()).append("=").append(p.getValue());
            if (it.hasNext()) {
                sb.append('&');
            }
        }

        String body = sb.toString();
        byte[] bytes = body.getBytes();
        HttpURLConnection uc = null;
        try {

            uc = (HttpURLConnection) u.openConnection();

            uc.setDoOutput(true);

            //uc.setUseCaches(false);
            uc.setFixedLengthStreamingMode(bytes.length);
            uc.setRequestMethod("POST");
            uc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            OutputStream out = uc.getOutputStream();

            out.write(bytes);
            out.close();
            int status = uc.getResponseCode();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(uc.getInputStream()));


            result = bufferedReader.readLine();


            if (status != 200) {
                Log.d("Invalid request code", "status is " + status);
            }

        } catch (Exception e1) {
            Log.d("error", "" + e1.getMessage());
        }

        return;

    }
}