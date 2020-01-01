package com.example.manageyourmoney;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class SqliteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Manage.DB";
    public static final int DATABASE_VERSION = 10;
    public static final String TABLE_USERS = "user";
    public static final String TABLE_DATA = "data";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_KETERANGAN = "keterangan";
    public static final String KEY_AMOUNT = "amount";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_SALDO = "saldo";
    public static final String KEY_TYPE = "type";
    public static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_USERS
            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_NAME + " TEXT, "
            + KEY_EMAIL + " TEXT, "
            + KEY_PASSWORD + " TEXT,"
            + KEY_SALDO + " INTEGER "
            + " ) ";
    public static final String SQL_TABLE_DATA = " CREATE TABLE " + TABLE_DATA
            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_AMOUNT + " VARCHAR, "
            + KEY_TYPE + " VARCHAR,"
            + KEY_KETERANGAN + " TEXT "
            + " ) ";


    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(SQL_TABLE_USERS);
        db.execSQL(SQL_TABLE_DATA);
    }

    public int addUser(User user) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", user.name);
        values.put("email", user.email);
        values.put("password", user.password);
        values.put("saldo", user.saldo);
        long todo_id = db.insert(TABLE_USERS, null, values);
        db.close();
        return (int) todo_id;
    }

    public User Authenticate(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,
                new String[]{KEY_ID, KEY_NAME, KEY_EMAIL,KEY_PASSWORD,  KEY_SALDO},
                KEY_EMAIL + "=?",
                new String[]{user.email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            User user1 = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4));
            if (user.password.equalsIgnoreCase(user1.password)) {
                return user1;
            }
        }

        return null;
    }
    public int updateBalance(Integer balance) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put("amount", balance);
        String selection = "id=?";
        String[] selectionArg = {String.valueOf(1)};
        long id = db.update("user", cValues, selection, selectionArg);
        return (int) id;
    }



    public JSONObject getUser() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where id=?", null);
        JSONObject user = new JSONObject();
        if (cursor.moveToFirst()) {
            try {
                user.put("name", cursor.getString(cursor.getColumnIndex("name")));
                user.put("amount", cursor.getString(cursor.getColumnIndex("amount")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        cursor.close();
        db.close();
        return user;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.w("Update DB", "DB IS UPDATE TO " + db.getVersion());
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }


}
