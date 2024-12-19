package com.example.mad_mp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class feedback extends AppCompatActivity {

    EditText feedbacktext;
    Button send, home,recipes,feedbackpage;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        feedbackpage=findViewById(R.id.feedbackpage);
        send=findViewById(R.id.send);
        home=findViewById(R.id.home);
        recipes=findViewById(R.id.recipes);
        feedbacktext=findViewById(R.id.feedback);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance("https://asianfood-26b37-default-rtdb.firebaseio.com/");
                reference = database.getReference("feedback");

                String fb = feedbacktext.getText().toString();

                String key = reference.push().getKey(); // Generate a unique key

                HelperClass helperClass = new HelperClass(fb);

                assert key != null;
                reference.child(key).setValue(helperClass); // Save the feedback with the unique key

                Toast.makeText(feedback.this, "Thanks for your feedback!", Toast.LENGTH_SHORT).show();
            }
        });



        feedbackpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(feedback.this, feedback.class);
                startActivity(i);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(feedback.this, Home.class);
                startActivity(i);
            }
        });

        recipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(feedback.this, MyRecipes.class);
                startActivity(i);
            }
        });



    }
}