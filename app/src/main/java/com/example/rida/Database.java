package com.example.rida;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    public static final String Database_Name="register.db";
    public static final String Table_Name="Users";
    public static final String col1="ID";
    public static final String col2="First_Name";
    public static final String col3="Last_Name";
    public static final String col4="Password";
    public static final String col5="Email";
    public static final String col6="Phone";




    public Database(Context context) {
        super(context, Database_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +Table_Name+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT, First_Name TEXT, Last_Name TEXT, Password TEXT, Email TEXT, Phone TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " +Database_Name);//Drop older table if exists
        onCreate(db);

    }
}
