package com.example.android.pnpcab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Beg extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beg);
    }
public void beg(View v)
{
    Intent i=new Intent(Beg.this,ProfiePage.class);
    startActivity(i);

}
}
