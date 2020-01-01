package com.example.manageyourmoney;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Manage extends AppCompatActivity {
    Button btnlog,btnreg;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        initViews();
      btnlog.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent=new Intent(Manage.this,Login.class);
              startActivity(intent);
          }
      });
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Manage.this,Register.class);
                startActivity(intent);
            }
        });
    }
    private void initViews() {

        btnlog = (Button) findViewById(R.id.buttonlogin);
        btnreg=(Button)findViewById(R.id.buttonregister);

    }
}
