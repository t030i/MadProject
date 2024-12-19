package com.example.mad_mp;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import androidx.annotation.NonNull;


public class signup extends AppCompatActivity {

    EditText firstname, lastname, dateofbirth, username, email,password;
    Button signup,login, admin;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firstname=findViewById(R.id.firstname);
        lastname=findViewById(R.id.lastname);
        dateofbirth=findViewById(R.id.dateofbirth);
        username=findViewById(R.id.username);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);

        signup=findViewById(R.id.btn_signup);
        login=findViewById(R.id.btn_login);
        admin=findViewById(R.id.btn_admin);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance("https://asianfood-26b37-default-rtdb.firebaseio.com/");
                reference = database.getReference("users");

                String fn = firstname.getText().toString();
                String ln = lastname.getText().toString();
                String dob = dateofbirth.getText().toString();
                String user = username.getText().toString();
                String em = email.getText().toString();
                String pass = password.getText().toString();

                if (fn.equals("") || ln.equals("") || dob.equals("")
                        || user.equals("") || em.equals("") || pass.equals("")) {
                    Toast.makeText(signup.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    reference.child(user).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                // user already exists
                                Toast.makeText(signup.this, "User already exists", Toast.LENGTH_SHORT).show();
                            } else {
                                // user does not exist, create a new record
                                HelperClass helperClass = new HelperClass(fn, ln, dob, user, em, pass);
                                reference.child(user).setValue(helperClass);
                                // show signup successful message
                                Toast.makeText(signup.this, "Signup successful, please login", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(signup.this, login.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(signup.this, "Database error", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }

        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signup.this, login.class);
                startActivity(intent);
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signup.this, admin.class);
                startActivity(intent);
            }
        });

    }

}