package com.example.yo7a.healthwatcher;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Yo7A on 4/17/2017.
 */

class user {
    private String username;
    private String name;
    private String password;



    public String getUsername() {
        return username;
    }



    public String getname() {
        return name;
    }

    public String getPass() {
        return password;
    }



    public void setUsername(String usrname) {

        username = usrname;
    }



    public void setname(String nam) {

        name = nam;
    }

    public void setPass(String pass) {

        password = pass;
    }






}

public class UserDB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userDB.db";

    //columns of the user Info table
    private static final String TABLE = "users";
    private static final String USERNAME = "username";
    private static final String NAME = "name";
    private static final String PASSWORD = "password";
    private static final String AGE = "age";
    private static final String HEIGHT = "height";
    private static final String WEIGHT = "weight";
    private static final String GENDER = "gender";
    private static final String EMAIL = "email";

    SQLiteDatabase db;

    public UserDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //checks if the password is correct
    public String checkPass(String user) {
        db = this.getReadableDatabase();
        String query = "select username, password from users";
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "Not found";

        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);

                if (a.equals(user)) {
                    b = cursor.getString(1);
                    break;
                }

            } while (cursor.moveToNext());
        }
        cursor.close();
        return b;
    }

    //checks if the username exist if yes it will return 0 otherwise it will return 1
    public int checkUser(String user) {
        db = this.getReadableDatabase();
        String query = "select username from users";
        Cursor cursor = db.rawQuery(query, null);
        String a;
        int x = 1;

        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);

                if (a.equals(user)) {
                    x = 0;
                    break;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return x;
    }







    public void addUser(user u) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME, u.getUsername());
        values.put(NAME, u.getname());
        values.put(PASSWORD, u.getPass());

        db.insert(TABLE, null, values);
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "create table users ( username text primary key not null, " +
                " name text not null, password text not null);";
        db.execSQL(createTable);
        this.db = db;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS users";
        db.execSQL(query);
        this.db = db;
    }
}
