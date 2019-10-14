package com.example.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CustomerDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Customers.db";
    public static final String TABLE_NAME = "customer_table";
    public static final String COL_1 = "EMAIL";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "PASSWORD";
    public static final String COL_4 = "TIER";
    public static final String COL_5 = "NUM_POINTS_AVAILABLE";


    public CustomerDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(EMAIL TEXT, NAME TEXT, PASSWORD TEXT, TIER TEXT, NUM_POINTS_AVAILABLE INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME );
        onCreate(db);

    }

    // adding customers

    public boolean addCustomer(String email, String name, String password, String tier, int numPointsAvailable){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_1, email);
        values.put(COL_2, name);
        values.put(COL_3, password);
        values.put(COL_4, tier);
        values.put(COL_5, numPointsAvailable);

        Long result = db.insert(TABLE_NAME, null, values);
        if (result == -1) {
            return false;
        }
            else {
                return true;
                }
            }
        }
