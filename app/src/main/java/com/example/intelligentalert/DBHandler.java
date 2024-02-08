package com.example.intelligentalert;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "users";

    // below int is our database version
    private static final int DB_VERSION = 2;

    // below variable is for our table name.
    private static final String TABLE_NAME = "authentication";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our name column
    private static final String NAME_COL = "name";

    // below variable id for our age column.
    private static final String AGE_COL = "age";

    // below variable for our CNIC column.
    private static final String CNIC_COL = "cnic";

    // below variable is for our contact number column.
    private static final String CONTACT_COL = "contact";

    // below variable is for our address column.
    private static final String ADDRESS_COL = "address";

    // below variable is for our hashed password column.
    private static final String PASSWORD_HASH_COL = "password";

    // below variable is for our password salt column.
    private static final String PASSWORD_SALT_COL = "salt";

    // below variable is for our password salt column.
    private static final String USER_TYPE_COL = "type";

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + AGE_COL + " TEXT,"
                + CNIC_COL + " TEXT,"
                + CONTACT_COL + " TEXT,"
                + PASSWORD_HASH_COL + " TEXT,"
                + PASSWORD_SALT_COL + " TEXT,"
                + USER_TYPE_COL + " TEXT,"
                + ADDRESS_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new user to our sqlite database.
    public void addNewUser(String name, String age, String cnic, String contact, String passwordHash, String passwordSalt, String address,String type) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, name);
        values.put(AGE_COL, age);
        values.put(CNIC_COL, cnic);
        values.put(CONTACT_COL, contact);
        values.put(PASSWORD_HASH_COL, passwordHash);
        values.put(PASSWORD_SALT_COL, passwordSalt);
        values.put(USER_TYPE_COL, type);
        values.put(ADDRESS_COL, address);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
//        db.close();
    }

    // we have created a new method for reading all the users.
    public ArrayList<String> readUsers() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorUsers = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<String> userModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorUsers.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                userModalArrayList.add(cursorUsers.getString(3));
            } while (cursorUsers.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorUsers.close();
        return userModalArrayList;
    }

    public String readUsers(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String userRowsArrayList = new String();

        // Create a cursor to read data from the database.
        Cursor cursorUsers = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + CNIC_COL + " = ?", new String[]{username});

        // Move the cursor to the first position.
        if (cursorUsers.moveToFirst()) {
            do {
                // Construct a string representation of the entire row.
                StringBuilder rowBuilder = new StringBuilder();
                for (int i = 0; i < cursorUsers.getColumnCount(); i++) {
                    rowBuilder.append(cursorUsers.getString(i)).append(",");
                }
                // Remove the trailing comma and add the row to the ArrayList.
                userRowsArrayList=rowBuilder.toString().substring(0, rowBuilder.length() - 1);
            } while (cursorUsers.moveToNext());
        }

        // Close the cursor to release resources.
        cursorUsers.close();

        // Return the ArrayList containing complete rows.
        return userRowsArrayList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
