package com.gc.express;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gc.express.MainActivity;

public class MainActivity3 extends Activity {
    public Intent intent;
    EditText nametext;
    EditText pwdtext;
    Button button;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zuche);
        nametext = (EditText) findViewById(R.id.nametext);
        pwdtext = (EditText) findViewById(R.id.pwdtext);
        button = (Button) findViewById(R.id.button);
        db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
    }

    public void bc(View view) {
        String name = nametext.getText().toString();
        String pwd = pwdtext.getText().toString();
        db.execSQL("insert into us values('" + name + "','" + pwd + "','" + 100 + "','" + 1 + "');");
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
