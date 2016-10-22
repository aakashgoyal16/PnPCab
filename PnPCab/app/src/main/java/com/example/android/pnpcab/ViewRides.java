package com.example.android.pnpcab;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViewRides extends AppCompatActivity {
    String msg = "",x;
    JSONArray arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rides);
    }
    public void rides(View v)
    {
        myTask obj=new myTask();
        obj.execute();
    }
    public class myTask extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... params) {

            try {
                String url = "http://aakashgoyal.96.lt/rides.php";
                MyJsonParser j = new MyJsonParser();
                JSONObject obj = j.getJson(url);
                x=obj.toString();
                JSONArray a = obj.getJSONArray("details");


                //String msg;
                for (int i = 0; i < a.length(); i++) {
                    JSONObject o = a.getJSONObject(i);
                    msg =msg+ "\n"+ o.getString("day");
                }



            } catch (JSONException e) {

            }
            return x;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(ViewRides.this,"reached post execute",Toast.LENGTH_LONG).show();
            String res=s;
            //  TextView l1=(TextView)findViewById(R.id.l1);
            //l1.setText(s);
            List<gs> data=new ArrayList<>();


            try
            {
                JSONObject jsonObj = new JSONObject(res);
                arr = jsonObj.getJSONArray("details");
                for (int i=0;i<arr.length();i++)
                {
                    JSONObject o = arr.getJSONObject(i);
                    String day = o.getString("day");
                    String source = o.getString("source");
                    String car = o.getString("car");
                    String status = o.getString("status");


                    gs obj=new gs();
                    obj.setDay("DAY :"+day);
                    obj.setSource("SOURCE :"+source);
                    obj.setCar("CAR :"+car);
                    obj.setStatus("STATUS :"+status);

                    data.add(obj);
                    //  i++;
                }
                adapter1 c1=new adapter1(data,R.layout.list_item,ViewRides.this);
                ListView l1=(ListView)findViewById(R.id.lisview);
                l1.setAdapter(c1);

                //    Toast.makeText(CheckAd.this,"reached post execute"+msg+x,Toast.LENGTH_LONG).show();


            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }

        }}


}
