package com.example.weshopapplication;

// Author of Application: Sabin Constantin Lungu
// Purpose of Application: To create a SQLite database to store the data filled in the contact us form activity
// Date of Last Modification: 15/02/2020
// Any Bugs? No. Will be tested with JUnit


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

public class ContactFormDatabaseManipulator {
    private static final String DATABASE_NAME = "contacts.db";
    private static final String TABLE_NAME = "customer_complaints";
    private static final String INSERT_QUERY = "INSERT INTO " + TABLE_NAME
            + " (username, email, phone_number, problem) VALUES (?,?,?,?)";
    private static int DB_VERSION = 1; // Database version
    private static Context context;
    private static SQLiteDatabase database;
    private SQLiteStatement sqlStatement; // The SQL statement

    public ContactFormDatabaseManipulator(Context context) {
        ContactFormDatabaseManipulator.context = context;
        OpenHelper helper = new OpenHelper(ContactFormDatabaseManipulator.context);

        ContactFormDatabaseManipulator.database = helper.getWritableDatabase();

        this.sqlStatement = ContactFormDatabaseManipulator.database.compileStatement(INSERT_QUERY);
    }

    // Routine that inserts data into the table
    public long insertData(String username, String email, String phone_number, String problem) {
        this.sqlStatement.bindString(1, username);
        this.sqlStatement.bindString(2, email);
        this.sqlStatement.bindString(3, phone_number);
        this.sqlStatement.bindString(4, problem);

        return this.sqlStatement.executeInsert();
    }

    public void deleteAllData() {
        database.delete(TABLE_NAME, null, null); // Deletes the table if required
    }

    public ArrayList<String[]> selectAllData() {
        ArrayList<String[]> listOfComplaints = new ArrayList<>();

        Cursor cursor = database.query(TABLE_NAME, new String[]{"problem_id", "username", "email", "phone_number", "problem"}, null, null, null, null, "username ASC");

        int index = 0;

        if (cursor.moveToNext()) {
            do {
                String[] complaints_data = new String[]{cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)};

                listOfComplaints.add(complaints_data);
                index++;
            } while (cursor.moveToNext());
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close(); // Close cursor
        }

        cursor.close();

        return listOfComplaints;
    }

    public static class OpenHelper extends SQLiteOpenHelper {
        public OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DB_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            database.execSQL("CREATE TABLE " + TABLE_NAME + " (complaint_id INTEGER PRIMARY KEY, username TEXT, email TEXT, phone_number TEXT, problem TEXT)");
        }

        public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
            DB_VERSION = newVersion;

            database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(database);
        }
    }

}
