package com.example.manageyourmoney;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
    EditText editTextNama;
    EditText editTextUmur;
    EditText editTextEmail;
    EditText editTextPassword;
    EditText editTextRepass;
    Button btnregister;
    User user;
    public String id;
    public String name;
    public String email;
    public String password;
    public Integer amount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        final User user = new User(this);
        initViews();
        btnregister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    if (validate()) {
                        String Nama = editTextNama.getText().toString();
                        String Email = editTextEmail.getText().toString();
                        String Password = editTextPassword.getText().toString();
                        int saldo = 0;
                        if (!user.isEmailExists(Email)) {

                            user.insertUser(new User(null, Nama, Email, Password, saldo));
                            Toast.makeText(Register.this, "User created successfully! Please Login ", Toast.LENGTH_LONG).show();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(Register.this, Login.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }, Toast.LENGTH_LONG);
                        } else {

                            Toast.makeText(Register.this, "User already exists with same email ", Toast.LENGTH_LONG).show();
                        }


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initViews() {
        editTextEmail = (EditText) findViewById(R.id.emailregister);
        editTextPassword = (EditText) findViewById(R.id.passregister);
        editTextNama = (EditText) findViewById(R.id.namaregister);

        btnregister = (Button) findViewById(R.id.btnregister);
        editTextRepass = (EditText) findViewById(R.id.repassregister);
    }

    public boolean validate() {
        boolean valid = false;
        String Nama = editTextNama.getText().toString();
        String Email = editTextEmail.getText().toString();
        String Password = editTextPassword.getText().toString();
        if (Nama.isEmpty()) {
            valid = false;
            Toast.makeText(Register.this, "Masukan Data dengan lengkap", Toast.LENGTH_LONG);
        } else {
            if (Nama.length() > 3) {
                valid = true;
            } else {
                valid = false;
                Toast.makeText(Register.this, "Name is to short!", Toast.LENGTH_LONG).show();
            }
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
                valid = false;
                Toast.makeText(Register.this, "Masukkan email format yang benar", Toast.LENGTH_LONG).show();
            } else {
                valid = true;
            }

            if (Password.isEmpty()) {
                valid = false;
                Toast.makeText(Register.this, "Please enter valid password!", Toast.LENGTH_LONG).show();
            } else {
                if (Password.length() > 5) {
                    valid = true;
                }else {
                    valid = false;
                    Toast.makeText(Register.this,"Password terlalu pendek",Toast.LENGTH_LONG).show();
                }

            }
        }
            return valid;
        }
    }
