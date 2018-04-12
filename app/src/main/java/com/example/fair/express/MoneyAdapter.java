package com.example.fair.express;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Fair on 2017/4/16.
 */

public class MoneyAdapter extends BaseAdapter {

    Context context;
    String[] price;

    public MoneyAdapter(Context context) {
        this.context = context;
        //一定要加context
        price = context.getResources().getStringArray(R.array.money);
    }

    @Override
    public int getCount() {
        return price.length;
    }

    @Override
    public Object getItem(int i) {
        return price[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.moneylist, null);
        TextView textView = (TextView) view1.findViewById(R.id.label);
        textView.setText(price[i]);
        return view1;
    }
}
