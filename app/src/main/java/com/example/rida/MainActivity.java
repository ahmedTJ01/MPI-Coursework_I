package com.example.rida;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

public class MainActivity extends AppCompatActivity {

     SQLiteOpenHelper openHelper;
     SQLiteDatabase db;
     Button _register, _loginpage, _map;
     EditText _fname, _lname, _pass, _email, _phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openHelper=new Database(this);

        _register = (Button) findViewById(R.id.register);
        _fname = (EditText) findViewById(R.id.fname);
        _lname = (EditText) findViewById(R.id.lname);
        _pass = (EditText) findViewById((R.id.pass));
        _email = (EditText) findViewById(R.id.email);
        _phone = (EditText) findViewById(R.id.phone);
        _loginpage = (Button) findViewById(R.id.loginpage);
        _map = (Button) findViewById(R.id.map);

        _register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validate()) {
                    db = openHelper.getWritableDatabase();

                    String fn = _fname.getText().toString();
                    String ln = _lname.getText().toString();
                    String pas = _pass.getText().toString();
                    String emel = _email.getText().toString();
                    String pho = _phone.getText().toString();

                    insertdata(fn, ln, pas, emel, pho);
                    Toast.makeText(getApplicationContext(), "Registered Successffully", Toast.LENGTH_LONG).show();

                }
            }
            public boolean validate(){
                if(_fname.getText().toString().trim().length() <=0){
                    Toast.makeText(MainActivity.this, "Please Enter First Name", Toast.LENGTH_SHORT).show();
                    return true;
                }else if (_lname.getText().toString().trim().length() <=0){
                    Toast.makeText(MainActivity.this, "Please Enter Last Name", Toast.LENGTH_SHORT).show();
                    return true;
                }else if (_pass.getText().toString().trim().length() <=0){
                    Toast.makeText(MainActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return true;
                }else if (_email.getText().toString().trim().length() <=0){
                    Toast.makeText(MainActivity.this, "Please Enter E-Mail", Toast.LENGTH_SHORT).show();
                    return true;
                }else if (_phone.getText().toString().trim().length() <=0){
                    Toast.makeText(MainActivity.this, "Please Enter Phone Number", Toast.LENGTH_SHORT).show();
                    return true;
                }

                return false;
            }
        });
        _loginpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, login.class);
                startActivity(intent);

            }
        });
        _map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(in);
            }
        });


    }
    public void insertdata(String fn, String ln, String pas, String emel, String pho){
        ContentValues contentValues = new ContentValues();

        contentValues.put(Database.col2, fn);
        contentValues.put(Database.col3, ln);
        contentValues.put(Database.col4, pas);
        contentValues.put(Database.col5, emel);
        contentValues.put(Database.col6, pho);
        long id = db.insert(Database.Table_Name, null, contentValues);

    }
}
