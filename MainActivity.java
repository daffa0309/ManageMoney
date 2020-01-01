package com.example.manageyourmoney;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    public Integer saldo;
    public String name;
    FragmentManager fm;
    FragmentTransaction ft;
    Button btnsave, btnlogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_fragment);
        final SqliteHelper sqliteHelper = new SqliteHelper(this);
        JSONObject dataUser = sqliteHelper.getUser();
        btnsave = (Button) findViewById(R.id.btnSave);
        btnlogout = (Button) findViewById(R.id.btnlogout);


    }


    @Override
    public void onBackPressed() {
        Toast.makeText(MainActivity.this, "you need to press the logout button", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}