package com.example.android.pnpcab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Ride extends AppCompatActivity {
    String car="",source="",status="booked";
    TextView e1,e2,e3;
    Calendar calander;
  //  static String result;

    SimpleDateFormat simpledateformat;
    String Date,des="",req="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride);
        car=getIntent().getExtras().getString("car");
        source=getIntent().getExtras().getString("source");

        Toast.makeText(Ride.this,car+source,Toast.LENGTH_LONG).show();
        e1=(TextView)findViewById(R.id.time1);
        e2=(TextView)findViewById(R.id.car1);
        e3=(TextView)findViewById(R.id.source1);
        calander = Calendar.getInstance();
        simpledateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date = simpledateformat.format(calander.getTime());
        e1.setText("BOOKING DATE/TIME "+Date);
        e2.setText("CAR SELECTED "+car);
        e3.setText("SOURCE "+source);
    }
    public void ridela(View v)
    {
        EditText f1=(EditText)findViewById(R.id.des1);
        EditText f2=(EditText)findViewById(R.id.req);

        des=f1.getText().toString();
        req=f2.getText().toString();


    }
}
