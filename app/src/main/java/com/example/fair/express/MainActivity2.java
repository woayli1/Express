package com.example.fair.express;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity2 extends Activity {
    Intent intent;
    String names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_main2);
        names = intent.getStringExtra("aa");
    }

    public void Buttononclick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.imageButton:
                //intent = new Intent(this, User.class);  //用户界面
                //intent.putExtra("ss", "0");
                // startActivity(intent);
                break;
            case R.id.receivebutton:
                intent = new Intent(this, ReceiveOrder.class);  //接单界面
                intent.putExtra("ss", "1");
                intent.putExtra("aa", names);
                startActivity(intent);
                break;
            case R.id.sendbutton:
                intent = new Intent(this, SendActivity.class); //发单界面
                intent.putExtra("ss", "2");
                intent.putExtra("aa", names);
                startActivity(intent);
                break;
            case R.id.details1:
                intent = new Intent(this, ReceiveOrder.class); //已发布订单 详情 界面
                intent.putExtra("ss", "3");
                intent.putExtra("aa", names);
                startActivity(intent);
                break;
            case R.id.details2:
                intent = new Intent(this, ReceiveOrder.class); //已接收订单 详情 界面
                intent.putExtra("ss", "4");
                intent.putExtra("aa", names);
                startActivity(intent);
                break;
        }
    }
}
