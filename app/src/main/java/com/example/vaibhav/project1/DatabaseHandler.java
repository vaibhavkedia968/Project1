package com.example.vaibhav.project1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "LoginUser";
    private static final String TABLE_NAME = "user";
    private static final String NAME = "Name";
    private static final String USERNAME = "Username";
    private static final String PASS = "Password";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Category table create query
        String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_NAME +"("
                + NAME + " TEXT, " + USERNAME + " TEXT PRIMARY KEY, " + PASS + " TEXT)";
        Log.d("Table creating : ",CREATE_ITEM_TABLE);
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
    public int register(String nm,String unm,String pwd)
    {
        Log.d("REGISTERING ",unm);
        SQLiteDatabase db = this.getWritableDatabase();
        String query="SELECT * FROM "+TABLE_NAME+" WHERE "+USERNAME+"='"+unm+"'";
        Cursor c=db.rawQuery(query,null);
        try {
            if (c.moveToFirst()) {
                c.close();
                db.close();
                return 0;
            }
        }catch(SQLException e) {
            return 2;
        }
        ContentValues values = new ContentValues();
        values.put(NAME, nm);
        values.put(USERNAME, unm);
        values.put(PASS, pwd);
        db.insert(TABLE_NAME,null,values);
        db.close();
        Log.d("REGISTERED SUCCESSFULLY",unm);
        return 1;
    }
    public boolean verify(String unm, String p) {
        Log.d("VERIFYING ",unm);
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE "+USERNAME+"='" + unm + "' AND "+PASS+"='" + p + "'";//
        Cursor c = db.rawQuery(query, null);
        try {
            if (c.moveToFirst()) {
                c.close();
                db.close();
                Log.d("VERIFIED ",unm);
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }
}

