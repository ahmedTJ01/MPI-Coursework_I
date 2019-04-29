package com.example.rida;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class login extends AppCompatActivity {
        SQLiteDatabase db;
        SQLiteOpenHelper openHelper;
        Button _btnlogin;
        Button _signup;
        EditText _email, _password;
        Cursor cursor;
        LoginButton _login_button;
        CallbackManager calbackManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);

        _login_button = (LoginButton) findViewById(R.id.login_button);
        calbackManager = CallbackManager.Factory.create();
        _login_button.registerCallback(calbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                    Intent inte = new Intent(login.this, MapsActivity.class);
                    startActivity(inte);
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Login with facebook cancelled!", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onError(FacebookException error) {

            }
        });


        openHelper = new Database(this);
        db=openHelper.getReadableDatabase();
        _btnlogin = (Button) findViewById(R.id.btnlogin);
        _email = (EditText) findViewById(R.id.email);
        _password = (EditText) findViewById(R.id.password);


        _btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validate()) {
                String emel = _email.getText().toString();
                String pass = _password.getText().toString();

                cursor = db.rawQuery("SELECT * FROM " +Database.Table_Name+ " WHERE " +Database.col5+ " =? AND " +Database.col4+ " =? ", new String[]{emel, pass} );
                if(cursor != null){
                    if(cursor.getCount()>0){
                        //cursor.moveToNext();
                        Toast.makeText(getApplicationContext(), "Logged in Successfull", Toast.LENGTH_LONG).show();
                        //AFTER LOGIN, IT WILL CALL THIS METHOD TO OPEN MAP
                        Intent intent = new Intent(login.this, MapsActivity.class);
                        startActivity(intent);


                    }else {
                        Toast.makeText(getApplicationContext(), "Please Enter Correct Credentials ", Toast.LENGTH_LONG).show();
                    }
                }
            }
            }

            public boolean validate(){
                if(_email.getText().toString().trim().length() <=0){
                    Toast.makeText(login.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return true;
                }else if (_password.getText().toString().trim().length() <=0){
                    Toast.makeText(login.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return true;
                }

                return false;
            }
        });

        _signup = (Button) findViewById(R.id.signup);
       _signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent inten = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(inten);

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        calbackManager.onActivityResult(requestCode,resultCode,data);
    }
}



