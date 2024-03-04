package com.example.fitnessapp_fyp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)

public class DBHelperTest {
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    @After
    public void tearDown() {
        database.close();
        dbHelper.close();
    }

    @SuppressLint("Range")
    @Test
    public void insertData_Success() {
        String username = "testuser";
        String password = "password";
        Boolean result = dbHelper.insertData(username, password);
        assertTrue(result);

        // Verify that the user was added to the users table
        Cursor cursor = database.rawQuery("SELECT * FROM users WHERE username = ?", new String[]{username});
        assertTrue(cursor.moveToFirst());
        assertEquals(password, cursor.getString(cursor.getColumnIndex("password")));
        cursor.close();

        // Verify that the user was added to the video_watching table
        cursor = database.rawQuery("SELECT * FROM video_watching WHERE username = ?", new String[]{username});
        assertTrue(cursor.moveToFirst());
        assertEquals(0, cursor.getInt(cursor.getColumnIndex("watch_count")));
        cursor.close();
    }

    @Test
    public void checkusername_Exists() {
        String username = "testuser";
        String password = "password";
        dbHelper.insertData(username, password);
        Boolean result = dbHelper.checkusername(username);
        assertTrue(result);
    }

    @Test
    public void checkusername_NotExists() {
        String username = "testuser";
        Boolean result = dbHelper.checkusername(username);
        assertFalse(result);
    }

    @Test
    public void testDatabaseWith1000Users() {
        int expectedCount = 1000;
        // Insert 1000 users into the database
        for (int i = 1; i <= expectedCount; i++) {
            String username = "user" + i;
            String password = "password" + i;
            dbHelper.insertData(username, password);
        }
        // Retrieve all users from the database and check count
        Cursor cursor = database.rawQuery("SELECT * FROM users", null);
        int actualCount = cursor.getCount();
        cursor.close();
        database.close();
        dbHelper.close();
        assertEquals(expectedCount, actualCount);
    }
}
