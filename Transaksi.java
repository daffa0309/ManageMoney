package com.example.manageyourmoney;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class Transaksi {
    public String keterangan;
    public Integer amount;
    private SqliteHelper sqliteHelper;
    public String type;
    Context contextApp;
    public Transaksi(){

    }
    public Transaksi(String keterangan,Integer amount){
        this.keterangan=keterangan;
        this.amount=amount;
    }
    public Transaksi(Context context) {
        sqliteHelper = new SqliteHelper(context);
        contextApp = context;
    }
    public int insertTable(Transaksi data){
        SQLiteDatabase db=sqliteHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("amount",data.amount);
        values.put("keterangan",data.keterangan);
        values.put("type",data.type);
        long transaksi_id=db.insert("data",null,values);
 db.close();
 return (int)transaksi_id;
    }
    public ArrayList<HashMap<String,String>>getList(){
        SQLiteDatabase db=sqliteHelper.getReadableDatabase();
        String querySelect="SELECT * FROM data order by id desc";
        ArrayList<HashMap<String,String>>trxList=new ArrayList<HashMap<String, String>>();
        Cursor cursor=db.rawQuery(querySelect,null);
        if (cursor.moveToFirst()){
            do {
                HashMap<String,String>trx=new HashMap<String, String>();
                trx.put("id", cursor.getString(cursor.getColumnIndex("id")));
                trx.put("keterangan", cursor.getString(cursor.getColumnIndex("keterangan")));
                trx.put("amount", cursor.getString(cursor.getColumnIndex("amount")));
                trx.put("type", cursor.getString(cursor.getColumnIndex("type")));
                trxList.add(trx);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return trxList;
    }
}
