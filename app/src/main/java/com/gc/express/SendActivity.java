package com.gc.express;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.gc.express.MainActivity2;
import com.gc.express.MoneyAdapter;

public class SendActivity extends Activity {
    public EditText nametext;
    public EditText phonetext;
    public EditText company;
    public EditText idtext;
    public EditText address;
    public EditText time;
    SQLiteDatabase db;
    Spinner spinner;
    Intent intent;
    String ord_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        Intent intent = getIntent();
        ord_name = intent.getStringExtra("aa");
        nametext = (EditText) findViewById(R.id.nametext); //发布者姓名
        phonetext = (EditText) findViewById(R.id.phonetext);//发布者电话
        company = (EditText) findViewById(R.id.company);//物流公司
        idtext = (EditText) findViewById(R.id.idtext);//取货号
        address = (EditText) findViewById(R.id.address);//宿舍楼号
        time = (EditText) findViewById(R.id.time);//送达时间
        final String[] prices = getResources().getStringArray(R.array.money);
        MoneyAdapter moneyAdapter = new MoneyAdapter(this);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(moneyAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), prices[i], Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void Buttononclick(View view) {
        int id = view.getId();

        if (id == R.id.issue) {
            String name = nametext.getText().toString();
            String phone = phonetext.getText().toString();
            String comp = company.getText().toString();
            String idt = idtext.getText().toString();
            String ad = address.getText().toString();
            String ti = time.getText().toString();
            String mo = (String) spinner.getSelectedItem();

            db.execSQL("insert into ord values('" + ord_name + "','" + comp + "','" + ad + "','" + mo + "','" + ti + "','" + name + "','" + null + "','" + idt + "','" + phone + "','" + 1 + "');");
            Toast.makeText(this, "发布成功", Toast.LENGTH_SHORT).show();
            intent = new Intent(this, MainActivity2.class);
            intent.putExtra("aa", ord_name);
            db.close();
            startActivity(intent);
        }
    }
}
