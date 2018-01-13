package com.example.vaibhav.project1;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserDetails extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserDetails";
    public static String TABLE_NAME = "User";
    private static final String DATE = "Date";
    private static final String CATEGORY = "Category";
    private static final String SCORE= "Score";
    private static final String TIME_TAKEN= "Time taken";

    public UserDetails(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Category table create query
        String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_NAME +"("
                + DATE + " TEXT, " + CATEGORY + " TEXT, " + SCORE + " TEXT,"+TIME_TAKEN+"TEXT)";
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
}