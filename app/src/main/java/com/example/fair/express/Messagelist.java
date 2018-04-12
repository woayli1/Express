package com.example.fair.express;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/5/17.
 */

public class Messagelist extends Activity {

    SQLiteDatabase db;
    private TextView nametext;
    private TextView phonetext;
    private TextView company;
    private TextView idtext;
    private TextView address;
    private TextView time;
    private TextView textView11;
    private TextView textView1;
    private String s;
    private String sc;
    private String ss;
    private Button button;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chakan);
        db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        nametext = (TextView) findViewById(R.id.nametext); //发布者姓名
        phonetext = (TextView) findViewById(R.id.phonetext);//发布者电话
        company = (TextView) findViewById(R.id.company);//物流公司
        idtext = (TextView) findViewById(R.id.idtext);//取货号
        address = (TextView) findViewById(R.id.address);//宿舍楼号
        time = (TextView) findViewById(R.id.time);//送达时间
        textView11 = (TextView) findViewById(R.id.textView11); //费用
        textView1 = (TextView) findViewById(R.id.nametex);//接单者姓名
        button = (Button) findViewById(R.id.button4);
        button2 = (Button) findViewById(R.id.button3);
        button3 = (Button) findViewById(R.id.button5);
        Intent intent = getIntent();
        s = intent.getStringExtra("string");
        sc = intent.getStringExtra("aa");
        ss = intent.getStringExtra("ss");
        if (ss.equals("3")) {
            button2.setVisibility(View.GONE);
            button3.setVisibility(View.GONE);
            orderdetail(s);
            chaxun(s);
        }
        if (ss.equals("1")) {
            button.setVisibility(View.GONE);
            button3.setVisibility(View.GONE);
            orderdetail(s);
        }
        if (ss.equals("4")) {
            button.setVisibility(View.GONE);
            button2.setVisibility(View.GONE);
            orderdetail(s);
        }
    }

    public void orderdetail(String string) {

        Cursor cursor = db.rawQuery("select * from ord where tp ='" + string + "'", null);
        if (cursor.moveToFirst()) {
            nametext.setText(cursor.getString(cursor.getColumnIndex("rpu_id")).toString());
            phonetext.setText(cursor.getString(cursor.getColumnIndex("phon")).toString());
            company.setText(cursor.getString(cursor.getColumnIndex("tp")).toString());
            idtext.setText(cursor.getString(cursor.getColumnIndex("idt")).toString());
            address.setText(cursor.getString(cursor.getColumnIndex("rp")).toString());
            time.setText(cursor.getString(cursor.getColumnIndex("ti")).toString());
            textView11.setText(cursor.getString(cursor.getColumnIndex("cost")).toString());
            textView1.setText(cursor.getString(cursor.getColumnIndex("rcu_id")).toString());
        }
    }

    public void chaxun(String sting) {
        db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("select os from ord where tp ='" + sting + "'", null);
        if (cursor.moveToFirst()) {
            if (cursor.getString(cursor.getColumnIndex("os")).toString().trim().equals("2")) {
                Toast.makeText(this, "该订单已确认收货", Toast.LENGTH_SHORT).show();
                button.setEnabled(false);
            }
        }
    }

    public void sure(View view) {
        db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("update ord set os = '" + 2 + "' where tp ='" + s + "'");
        Toast.makeText(this, "确认收货成功", Toast.LENGTH_SHORT).show();
        button.setEnabled(false);
    }

    public void acc(View view) {
        db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("update ord set os = '" + 3 + "' where tp ='" + s + "'");
        db.execSQL("update ord set rcu_id = '" + sc + "' where tp='" + s + "'");
        Toast.makeText(this, "接单成功", Toast.LENGTH_SHORT).show();
        button2.setEnabled(false);
    }

    public void acd(View view) {
        db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("update ord set os = '" + 2 + "' where tp ='" + s + "'");
        Toast.makeText(this, "确认送达成功", Toast.LENGTH_SHORT).show();
        button3.setEnabled(false);
    }
}
