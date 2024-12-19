package com.example.mad_mp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import android.content.Intent;
import android.view.View;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class login extends AppCompatActivity {

    EditText username, password;
    Button login, signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        signup=findViewById(R.id.signup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateUsername() | !validatePassword()){

                }else {
                    checkUser();
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, signup.class);
                startActivity(intent);
            }
        });
    }
    public Boolean validateUsername() {
        String val = username.getText().toString();
        if (val.isEmpty()) {
            username.setError("Empty filed!");
            return false;
        } else {
            username.setError(null);
            return true;
        }
    }

    public Boolean validatePassword(){
        String val = password.getText().toString();
        if (val.isEmpty()) {
            password.setError("Empty filed!");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }

    public void checkUser() {
        String user = username.getText().toString().trim();
        String pass = password.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase
                .getInstance("https://asianfood-26b37-default-rtdb.firebaseio.com/")
                .getReference("users");

        // Query the database for the username
        Query checkUserDatabase = reference.orderByChild("username").equalTo(user);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Loop through the snapshot to match password
                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        String passFromDB = userSnapshot.child("password").getValue(String.class);

                        if (passFromDB != null && passFromDB.equals(pass)) {
                            // Retrieve user data
                            String fnFromDB = userSnapshot.child("firstname").getValue(String.class);

                            // Navigate to Home screen with user data
                            Intent intent = new Intent(login.this, Home.class);
                            intent.putExtra("firstname", fnFromDB);
                            startActivity(intent);
                            return; // Stop loop after successful login
                        } else {
                            // Incorrect password
                            password.setError("Invalid Credentials");
                            password.requestFocus();
                        }
                    }
                } else {
                    // Username not found
                    username.setError("User does not exist");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle possible errors
                System.out.println("Error: " + error.getMessage());
            }
        });
    }

}



