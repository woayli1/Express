package com.gc.express;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.gc.express.Messagelist;

import java.util.ArrayList;


/**
 * Created by Fair on 2017/4/21.
 */

public class OrderAdapter extends BaseAdapter {
    private Context context = null;
    ArrayList company;
    ArrayList sss;
    public Button order;

    Intent intent;

    public OrderAdapter(Context context) {
        this.context = context;
        company = ReceiveOrder.expresscompany;
        sss = ReceiveOrder.ss;
    }


    @Override
    public int getCount() {
        return company.size();
    }

    @Override
    public Object getItem(int i) {
        return company.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view1 = layoutInflater.inflate(R.layout.messagelist, null);

        order = (Button) view1.findViewById(R.id.order);
        TextView textView = (TextView) view1.findViewById(R.id.textView9);
        textView.setText(company.get(i).toString());
        final String sting = textView.getText().toString();
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sc = sss.get(0).toString();
                String ss = sss.get(1).toString();
                intent = new Intent(context, Messagelist.class);//接单界面
                intent.putExtra("string", sting);
                intent.putExtra("aa", sc);
                intent.putExtra("ss", ss);
                context.startActivity(intent);
            }
        });
        return view1;
    }
}


