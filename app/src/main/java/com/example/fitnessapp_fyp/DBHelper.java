
package com.example.fitnessapp_fyp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.android.exoplayer2.util.Log;

import java.util.Random;

public class DBHelper extends SQLiteOpenHelper {
    private SharedPreferences sharedPreferences;

    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
        sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT)");
        MyDB.execSQL("CREATE TABLE video_watching (username TEXT, watch_count INTEGER, PRIMARY KEY (username), FOREIGN KEY (username) REFERENCES users(username) ON DELETE CASCADE)");

        Cursor cursor = MyDB.rawQuery("SELECT username FROM users", null);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex("username"));
            ContentValues contentValues = new ContentValues();
            contentValues.put("username", username);
            contentValues.put("watch_count", 0);
            MyDB.insertWithOnConflict("video_watching", null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
        }
        cursor.close();


    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists video_watching");
    }

    public Boolean insertData(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);
        if (result != -1) {
            // Insert the username into the video_watching table with an initial count of 0
            ContentValues videoContentValues = new ContentValues();
            videoContentValues.put("username", username);
            videoContentValues.put("watch_count", 0);
            long videoResult = MyDB.insertWithOnConflict("video_watching", null, videoContentValues, SQLiteDatabase.CONFLICT_IGNORE);
            return videoResult != -1;
        }

        return false;


    }

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        return cursor.getCount() > 0;
    }

    public Boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[]{username, password});
        if (cursor.getCount() > 0) {
            // Insert the username into the video_watching table with an initial count of 0
            ContentValues contentValues = new ContentValues();
            contentValues.put("username", username);
            contentValues.put("watch_count", 0);
            MyDB.insertWithOnConflict("video_watching", null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
            return true;
        }
        return false;
    }

    public int incrementCountForUser(String username, Context context) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        String[] whereArgs = {username};
        ContentValues cv = new ContentValues();

        int count = getCountForUser(username,context);
        int increment = new Random().nextInt(6) + 20; // generate random number between 20-25
        int newCount = count + increment;

        cv.put("watch_count", newCount);
        MyDB.update("video_watching", cv, "username = ?", whereArgs);
        Log.d("MyTag", "Updated count for user " + username + ": " + newCount);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(username + "_count", newCount);
        editor.apply();
        return newCount;
    }


    @SuppressLint("Range")
    public int getCountForUser(String username, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        int count = sharedPreferences.getInt(username + "_count", 0);
        Log.d("MyTag", "Retrieved count for user " + username + ": " + count);
        return count;


    }
    }
