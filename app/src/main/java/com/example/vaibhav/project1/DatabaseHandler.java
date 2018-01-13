package com.example.vaibhav.project1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "LoginUser";
    private static final String TABLE_NAME = "user";
    private static final String NAME = "Name";
    private static final String USERNAME = "Username";
    private static final String PASS = "Password";
    private static final String ACTIVE = "Active";
    private static final String TOTAL_QUIZ = "Total Quiz";
    private static final String TOTAL_SCORE = "Total Score";
    private static final String AVG_SCORE = "Average Score";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Category table create query
        String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_NAME +"("
                +NAME + " TEXT, "
                +USERNAME + " TEXT PRIMARY KEY, "
                +PASS + " TEXT, "
                +ACTIVE +" TEXT, "
                +TOTAL_QUIZ +" NUMBER, "
                +TOTAL_SCORE +" NUMBER, "
                +AVG_SCORE +" NUMBER)";
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
        values.put(USERNAME,unm);
        values.put(PASS, pwd);
        values.put(ACTIVE, "N");
        values.put(TOTAL_QUIZ, 0);
        values.put(TOTAL_SCORE, 0);
        values.put(AVG_SCORE, 0);
        db.insert(TABLE_NAME,null,values);
        db.close();
        Log.d("REGISTERED SUCCESSFULLY",unm);
        UserDetails.TABLE_NAME=unm;
        return 1;
    }
    public boolean verify(String unm, String p) {
        Log.d("VERIFYING ",unm);
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE "+USERNAME+"='" + unm + "' AND "+PASS+"='" + p + "'";
        Cursor c = db.rawQuery(query, null);
        try {
            if (c.moveToFirst()) {
                //db.execSQL("UPDATE "+TABLE_NAME+" SET "+ACTIVE+" = 'Y' WHERE "+USERNAME+" = "+unm);
                ContentValues values = new ContentValues();
                values.put(ACTIVE, "Y");
                db.update(TABLE_NAME,values," USERNAME = ?",new String[]{unm});
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
    public String activeUser()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE "+ACTIVE+"='Y'";
        Cursor c = db.rawQuery(query, null);
        try {
            if (c.moveToFirst()) {
                c.close();
                db.close();
                Log.d("Active user : ",c.getString(2));
                return c.getString(2);
            }
            else
                return "";
        } catch (SQLException e) {
            return "";
        }
    }
}

