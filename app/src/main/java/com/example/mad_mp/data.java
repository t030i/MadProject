package com.example.mad_mp;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;


public class data extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> fn, ln, dob, user, em, pass;

    FirebaseDatabase database;
    DatabaseReference reference;

    UsersAdapter adapter;
    Button add,update,delete,exit;
    EditText firstname, lastname, dateofbirth, username, email,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        firstname=findViewById(R.id.firstname);
        lastname=findViewById(R.id.lastname);
        dateofbirth=findViewById(R.id.dateofbirth);
        username=findViewById(R.id.username);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        add=findViewById(R.id.addUserButton);
        update=findViewById(R.id.updateUserButton);
        delete=findViewById(R.id.deleteUserButton);
        exit=findViewById(R.id.exit);

        database = FirebaseDatabase.getInstance("https://asianfood-26b37-default-rtdb.firebaseio.com/");
        reference = database.getReference("users");

        fn= new ArrayList<>();
        ln= new ArrayList<>();
        dob= new ArrayList<>();
        user= new ArrayList<>();
        em= new ArrayList<>();
        pass= new ArrayList<>();

        recyclerView=findViewById(R.id.userRecyclerView);
        adapter=new UsersAdapter(this, fn,ln,dob,user,em,pass);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();



        // Update User
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });

        // Delete User
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser();
            }
        });

        // Exit Activity
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void displaydata() {

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    fn.clear();
                    ln.clear();
                    dob.clear();
                    user.clear();
                    em.clear();
                    pass.clear();

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String firstname = snapshot.child("firstname").getValue(String.class);
                        String lastname = snapshot.child("lastname").getValue(String.class);
                        String dateofbirth = snapshot.child("dateofbirth").getValue(String.class);
                        String username = snapshot.child("username").getValue(String.class);
                        String email = snapshot.child("email").getValue(String.class);
                        String password = snapshot.child("password").getValue(String.class);

                        if (firstname != null && lastname != null && dateofbirth != null &&
                                username != null && email != null && password != null) {
                            fn.add(firstname);
                            ln.add(lastname);
                            dob.add(dateofbirth);
                            user.add(username);
                            em.add(email);
                            pass.add(password);
                        }
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(data.this, "No entries exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(data.this, "Failed to retrieve data", Toast.LENGTH_SHORT).show();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(data.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    reference.child(user).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                // user already exists
                                Toast.makeText(data.this, "User already exists", Toast.LENGTH_SHORT).show();
                            } else {
                                // user does not exist, create a new record
                                HelperClass helperClass = new HelperClass(fn, ln, dob, user, em, pass);
                                reference.child(user).setValue(helperClass);
                                // show successful message
                                Toast.makeText(data.this, "User Added successfully!", Toast.LENGTH_SHORT).show();
                                clearInputFields();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(data.this, "Database error", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }

        });
    }

    private void updateUser() {
        String userKey = username.getText().toString();
        if (userKey.isEmpty()) {
            Toast.makeText(this, "Please enter username to update", Toast.LENGTH_SHORT).show();
            return;
        }
        reference.child(userKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    HelperClass updatedUser = new HelperClass(
                            firstname.getText().toString(), lastname.getText().toString(),
                            dateofbirth.getText().toString(), userKey, email.getText().toString(),
                            password.getText().toString()
                    );
                    reference.child(userKey).setValue(updatedUser);
                    Toast.makeText(data.this, "User Updated Successfully", Toast.LENGTH_SHORT).show();
                    clearInputFields();
                } else {
                    Toast.makeText(data.this, "User does not exist", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(data.this, "Error updating user", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteUser() {
        String userKey = username.getText().toString();
        if (userKey.isEmpty()) {
            Toast.makeText(this, "Please enter username to delete", Toast.LENGTH_SHORT).show();
            return;
        }
        reference.child(userKey).removeValue().addOnSuccessListener(aVoid -> {
            Toast.makeText(data.this, "User Deleted Successfully", Toast.LENGTH_SHORT).show();
            clearInputFields();
        }).addOnFailureListener(e -> {
            Toast.makeText(data.this, "Error deleting user", Toast.LENGTH_SHORT).show();
        });
    }


    // Method to clear input fields
        private void clearInputFields() {
            firstname.setText("");
            lastname.setText("");
            dateofbirth.setText("");
            username.setText("");
            email.setText("");
            password.setText("");
        }

    }
