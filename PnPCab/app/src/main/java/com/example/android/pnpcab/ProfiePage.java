package com.example.android.pnpcab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ProfiePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profie_page);
    }
    public void loc(View v)
    {
        Intent i=new Intent(ProfiePage.this,CurrentLocation.class);
        startActivity(i);

    }
    public void view1(View v)
    {
        Intent i=new Intent(ProfiePage.this,ViewRides.class);
        startActivity(i);

    }
    public void upd(View v)
    {
        Intent i=new Intent(ProfiePage.this,Upd.class);
        startActivity(i);

    }


}
