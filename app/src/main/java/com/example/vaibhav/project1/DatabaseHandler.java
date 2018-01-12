package com.example.vaibhav.project1;

/**
 * Created by SIVANGI on 12-01-2018.
 */

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "LoginAPP";
    private static final String TABLE_NAME = "user";
    private static final String NAME = "Name";  //the value specified will be considered as table column name
    private static final String EMAIL = "Email";
    private static final String PHONE = "Phone";
    private static final String PASS = "Password";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Category table create query
        String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + NAME + " TEXT," + EMAIL + " TEXT PRIMARY KEY," + PHONE + "TEXT," + PASS + " TEXT)";

        db.execSQL(CREATE_ITEM_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }


    public boolean verify(String email, String pass) {

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from " + TABLE_NAME + " where Email='" + email + "' and Password='" + pass + "'";
        Cursor c = db.rawQuery(query, null);
        try {
            if (c.moveToFirst()) {

                Log.d("SName", c.getString(0));
                Log.d("SEmail", c.getString(1));
                Log.d("SPhone", c.getString(2));
                Log.d("SPass", c.getString(3));
                c.close();
                db.close();
                return true;
            }
        } catch (Exception e) {
            return false;
        }
     /*  else{
            Log.d("Name",c.getString(0));
            Log.d("Email",c.getString(1));
            Log.d("Phone",c.getString(2));
            Log.d("Pass",c.getString(3));
            c.close();
            db.close();
            return false;
        }*/


        return true;
    }

    public int register(String nm, String em, String ph, String ps) {

        SQLiteDatabase db = this.getWritableDatabase();
        /*ContentValues values = new ContentValues();
        values.put(NAME,nm);
        values.put(EMAIL,em);
        values.put(PHONE,ph);
        values.put(PASS,ps);

        db.insert(TABLE_NAME,null,values);*/
        String query = "insert into " + TABLE_NAME + " values('" + nm + "','" + em + "','" + ph + "','" + ps + "')";
        db.execSQL(query);
        db.close();
        return 1;
    }
}
