package com.example.manageyourmoney;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONObject;

import java.sql.SQLException;

public class DBManager {
    private SqliteHelper sqliteHelper;
    private Context context;
    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        sqliteHelper = new SqliteHelper (context);
        database = sqliteHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        sqliteHelper.close();
    }

    public void tambah(String amount, String keterangan,String tipe,Integer saldo) {
        String amount=
        String tip="pengeluaran";
        balance = user.getInt("amount");
        Integer balance=100;
        if (tipe.equals(tip)){
            amount-balance
        }
        ContentValues contentValue = new ContentValues();
        contentValue.put(SqliteHelper.KEY_AMOUNT,amount );
        contentValue.put(sqliteHelper.KEY_KETERANGAN, keterangan);
        database.insert(sqliteHelper.TABLE_USERS, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[]{SqliteHelper.KEY_ID, sqliteHelper.KEY_AMOUNT, sqliteHelper.KEY_KETERANGAN};
        Cursor cursor = database.query(sqliteHelper.TABLE_DATA, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

/*    public int update(long _id, String kelas, String nama) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SqliteHelper.KELAS, kelas);
        contentValues.put(DatabaseHelper.NAMA, nama);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }*/
}
