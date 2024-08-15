package com.gc.express;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.gc.express.OrderAdapter;

import java.util.ArrayList;


public class ReceiveOrder extends Activity {
    public ListView orderlist;
    public static ArrayList<String> expresscompany;
    public static ArrayList<String> ss;
    private SQLiteDatabase db;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_order);  //已发布订单 详情 界面
        textView = (TextView) findViewById(R.id.textView5);
        Intent intent = getIntent();
        String aa = intent.getStringExtra("aa");  //登录者姓名
        String ssc = intent.getStringExtra("ss");  //判定进入的界面
        expresscompany = new ArrayList<String>();
        ss = new ArrayList<String>();
        ss.add(aa);
        ss.add(ssc);
        if (ssc.equals("1")) {   //接单界面
            textView.setText("待接订单");
            db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
            Cursor cursor = db.rawQuery("select tp from ord where ord_name !='" + aa + "'and os='" + 1 + "'", null);
            while (cursor.moveToNext()) {
                expresscompany.add(cursor.getString(cursor.getColumnIndex("tp")).toString());
            }
        }
        if (ssc.equals("3")) {  //自己已经发布订单界面
            textView.setText("已发布订单");
            db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
            Cursor cursor = db.rawQuery("select tp from ord where ord_name ='" + aa + "'", null);
            while (cursor.moveToNext()) {
                expresscompany.add(cursor.getString(cursor.getColumnIndex("tp")).toString());
            }
        }
        if (ssc.equals("4")) {   //自己已经接收订单界面
            textView.setText("已接订单");
            db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
            Cursor cursor = db.rawQuery("select tp from ord where rcu_id = '" + aa + "'", null);
            while (cursor.moveToNext()) {
                expresscompany.add(cursor.getString(cursor.getColumnIndex("tp")).toString());
            }
        }

        orderlist = (ListView) findViewById(R.id.orderlist2);
        OrderAdapter orderAdapter = new OrderAdapter(this);
        orderlist.setAdapter(orderAdapter);

    }
}
