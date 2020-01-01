package com.example.manageyourmoney;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.manageyourmoney.R;
import com.example.manageyourmoney.Transaksi;
import com.example.manageyourmoney.User;

import com.example.manageyourmoney.R;
import com.example.manageyourmoney.SqliteHelper;
import com.example.manageyourmoney.Transaksi;
import com.example.manageyourmoney.User;
import com.example.manageyourmoney.list;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

public class Tambah_Transaksi extends AppCompatActivity implements View.OnClickListener {
    EditText amounttext;
    EditText keterangantxt;
    Spinner jenis;
    private DBManager dbManager;
   Button add;
  private  SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Masukan jumlah pemasukan/pengeluaran");
        setContentView(R.layout.pemasukan);
        jenis=(Spinner)findViewById(R.id.jenis);
        amounttext=(EditText)findViewById(R.id.amount);
        keterangantxt=(EditText)findViewById(R.id.ket);
        sqliteHelper=new SqliteHelper(this);
        add=(Button)findViewById(R.id.addpemasukan);
        dbManager=new DBManager(this);
        try{
            dbManager.open();
        }catch (SQLException e){
            e.printStackTrace();
        }
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addpemasukan:
                final String amount = amounttext.getText().toString();
                final String ket = keterangantxt.getText().toString();

        }
    }
}
