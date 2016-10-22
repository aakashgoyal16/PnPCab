package com.example.android.pnpcab;

import android.content.Context;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by HP on 22-10-2016.
 */
public class adapter1 extends BaseAdapter {
    List<gs> data;
    int rlayout;
    Context c;

    public adapter1(List<gs> data, int rlayout, Context c) {
        this.data = data;
        this.rlayout = rlayout;
        this.c = c;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public android.view.View getView(int position, android.view.View convertView, ViewGroup parent)
    {
        convertView = LayoutInflater.from(c).inflate(rlayout,null);
        TextView t1=(TextView)convertView.findViewById(R.id.q1);
        TextView t2=(TextView)convertView.findViewById(R.id.q2);
        TextView t3=(TextView)convertView.findViewById(R.id.q3);
        TextView t4=(TextView)convertView.findViewById(R.id.q4);

        t1.setText(data.get(position).getDay());
        t2.setText(data.get(position).getSource());
        t3.setText(data.get(position).getCar());
        t4.setText(data.get(position).getStatus());

        return convertView;

    }
}
