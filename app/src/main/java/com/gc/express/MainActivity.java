package com.gc.express;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    EditText nametext;
    EditText psdtext;
    Button loginBtn;
    Button button2;
    SQLiteDatabase db;
    Intent intent;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("create table if not exists us(user_name Varchar,pwd Varchar,integration Varchar,status Varchar);");
        db.execSQL("create table if not exists ord(ord_name Varchar,tp Varchar,rp Varchar,cost Varchar,ti Varchar,rpu_id Varchar,rcu_id Varchar,idt Varchar,phon Varchar,os Varchar);");
        nametext = (EditText) findViewById(R.id.nametext);
        psdtext = (EditText) findViewById(R.id.psdtext);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        button2 = (Button) findViewById(R.id.button2);
    }

    //按钮被点击后触发的事件
    public void Btnclick(View view) {
        int id = view.getId();
        if (id == R.id.loginBtn) {
            name = nametext.getText().toString();
            String psd = psdtext.getText().toString();
            Cursor cursor = db.rawQuery("select * from us where user_name='" + name + "'and pwd='" + psd + "'", null);
            if (cursor.getCount() == 0) {
                Toast.makeText(this, "输入有误", Toast.LENGTH_SHORT).show();
                return;
            } else {
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, MainActivity2.class);
                intent.putExtra("aa", name);
            }
        } else if (id == R.id.button2) {
            intent = new Intent(this, MainActivity3.class);
        }
        db.close();
        startActivity(intent);
    }

}
