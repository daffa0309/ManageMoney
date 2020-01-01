package com.example.manageyourmoney;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static com.example.manageyourmoney.SqliteHelper.KEY_EMAIL;
import static com.example.manageyourmoney.SqliteHelper.KEY_ID;
import static com.example.manageyourmoney.SqliteHelper.KEY_PASSWORD;
import static com.example.manageyourmoney.SqliteHelper.TABLE_USERS;

public class User {
    public String id;
    public String name;
    public String email;
    public String password;
    public Integer saldo;
    private SqliteHelper sqliteHelper;
    public User(String id, String name, String email, String password,Integer saldo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.saldo = saldo;
    }
    public User(Context context){sqliteHelper=new SqliteHelper(context);}
    public int insertUser(User user) {
        SQLiteDatabase db = sqliteHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", user.name);
        values.put("email", user.email);
        values.put("password", user.password);
        values.put("saldo", user.saldo);
        long todo_id = db.insert(TABLE_USERS, null, values);
        db.close();
        return (int) todo_id;
    }
    public boolean isEmailExists(String em) {
        SQLiteDatabase db = sqliteHelper.getReadableDatabase();
        Cursor cursor = db.query("user",// Selecting Table
                new String[]{KEY_ID, KEY_EMAIL, KEY_PASSWORD},//Selecting columns want to query
                KEY_EMAIL + "=?",
                new String[]{em},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            return true;
        }

        return false;
    }








}

