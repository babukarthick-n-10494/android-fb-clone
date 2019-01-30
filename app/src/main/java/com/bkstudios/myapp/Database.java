package com.bkstudios.myapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database  extends SQLiteOpenHelper {
    public Database(Context context) {
        super(context, "fb.db", null, 1);
    }
    String createQuery = "CREATE TABLE "+ FB.TABLE_NAME +"("+FB.id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+FB.post +" TEXT)";
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createQuery);

  }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
