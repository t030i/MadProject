package com.example.mad_mp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class admin extends AppCompatActivity {

    EditText username, password;
    Button login, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        back=findViewById(R.id.back);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateUsername() | !validatePassword()) {
                    // Validation failed
                } else {
                    checkUser();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin.this, feedback.class);
                startActivity(intent);
            }
        });
    }

    public Boolean validateUsername() {
        String val = username.getText().toString();
        if (val.isEmpty()) {
            username.setError("Empty field!");
            return false;
        } else {
            username.setError(null);
            return true;
        }
    }

    public Boolean validatePassword() {
        String val = password.getText().toString();
        if (val.isEmpty()) {
            password.setError("Empty field!");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }

    public void checkUser() {
        String user = username.getText().toString().trim();
        String pass = password.getText().toString().trim();

        // Hardcoded valid username and password
        if (user.equals("t030i") && pass.equals("2002")) {

            Intent intent = new Intent(admin.this, data.class);
            startActivity(intent);
        } else {
            if (!user.equals("t030i")) {
                // Invalid username
                username.setError("User does not exist");
                username.requestFocus();
            } else {
                // Invalid password
                password.setError("Invalid Credentials");
                password.requestFocus();
            }
        }
    }
}


