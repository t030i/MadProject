package com.example.mad_mp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import java.util.ArrayList;
import java.util.List;


public class MyRecipes extends AppCompatActivity {

    EditText recipeTitle, recipeDetails, recipeIngredients, recipeInstructions;
    Button createRecipeButton, homeButton, recipesButton, feedbackButton;
    RecyclerView recipesRecyclerView;

    // Adapter and Recipe list
    RecipeAdapter adapter;
    List<Recipe> recipesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipes);

        // Initialize views
        recipeTitle = findViewById(R.id.recipeTitle);
        recipeDetails = findViewById(R.id.recipeDetails);
        recipeIngredients = findViewById(R.id.recipeIngredients);
        recipeInstructions = findViewById(R.id.recipeInstructions);
        createRecipeButton = findViewById(R.id.createRecipeButton);
        recipesRecyclerView = findViewById(R.id.recipesRecyclerView);

        // Bottom navigation buttons
        homeButton = findViewById(R.id.home);
        recipesButton = findViewById(R.id.recipes);
        feedbackButton = findViewById(R.id.feedback);


        // Initialize RecyclerView
        recipesList = new ArrayList<>();
        adapter = new RecipeAdapter(recipesList);
        recipesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        recipesRecyclerView.setAdapter(adapter);

        // Add a recipe to the list
        createRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = recipeTitle.getText().toString().trim();
                String details = recipeDetails.getText().toString().trim();
                String ingredients = recipeIngredients.getText().toString().trim();
                String instructions = recipeInstructions.getText().toString().trim();

                if (!title.isEmpty() && !details.isEmpty() && !ingredients.isEmpty() && !instructions.isEmpty()) {
                    // Add new recipe to the list
                    recipesList.add(new Recipe(title, details, ingredients, instructions));
                    adapter.notifyDataSetChanged();

                    // Clear input fields
                    recipeTitle.setText("");
                    recipeDetails.setText("");
                    recipeIngredients.setText("");
                    recipeInstructions.setText("");
                }
            }
        });

        // Bottom Navigation Logic
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyRecipes.this, Home.class));
            }
        });

        recipesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Current activity
            }
        });

        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyRecipes.this, feedback.class));
            }
        });



    }
}