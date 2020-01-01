package com.example.manageyourmoney;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    EditText editTextEmail;
    EditText editTextPassword;



    Button buttonLogin;

    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        sqliteHelper = new SqliteHelper(this);
        initViews();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (validate()) {

                        String Email = editTextEmail.getText().toString();
                        String Password = editTextPassword.getText().toString();

                        User currentUser = sqliteHelper.Authenticate(new User(null, null, Email, Password, null));

                        if (currentUser != null) {
                            Toast.makeText(Login.this, "Successfully Logged in!", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                            finish();

                        } else {

                            Toast.makeText(Login.this, "Failed to log in , please try again", Toast.LENGTH_SHORT).show();

                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }


    private void initViews() {
        editTextEmail = (EditText) findViewById(R.id.emaillogin);
        editTextPassword = (EditText) findViewById(R.id.passlogin);
        buttonLogin = (Button) findViewById(R.id.submit);

    }



    public boolean validate() {
        boolean valid = false;

        String Email = editTextEmail.getText().toString();
        String Password = editTextPassword.getText().toString();

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            Toast.makeText(Login.this,"Gunakan Format email yang valid",Toast.LENGTH_LONG).show();
        } else {
            valid = true;

        }

        if (Password.isEmpty()) {
            valid = false;
            Toast.makeText(Login.this,"Password tidak boleh kosong",Toast.LENGTH_LONG).show();
        } else {
            if (Password.length() > 5) {
                valid = true;
            } else {
                valid = false;
                Toast.makeText(Login.this,"Password terlalu pendek",Toast.LENGTH_LONG).show();
            }
        }
        return valid;
    }
}
