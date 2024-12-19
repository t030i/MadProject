package com.example.mad_mp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.annotation.SuppressLint;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.SharedPreferences;


public class Home extends AppCompatActivity {

    TextView welcome;
    Button home, recipes,feedback;
    RecyclerView recipeRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        // Initialize Buttons
        home = findViewById(R.id.home);
        recipes = findViewById(R.id.recipes);
        feedback = findViewById(R.id.feedback);
        welcome=findViewById(R.id.welcome);


        // Buttons Click Listeners
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Home.class);
                startActivity(intent);
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, feedback.class);
                startActivity(intent);
            }
        });

        recipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, MyRecipes.class);
                startActivity(intent);
            }
        });

        // Initialize RecyclerView
        recipeRecyclerView = findViewById(R.id.recipeRecyclerView);
        recipeRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        // Add detailed recipes
        List<Recipe> recipesList = new ArrayList<>();

        //chinese recipes
        recipesList.add(new Recipe(
                "Kung Pao Chicken",
                "Spicy stir-fried chicken with peanuts and vegetables.",
                "• 2 boneless chicken breasts (diced)\n" +
                        "• 1/2 cup unsalted roasted peanuts\n" +
                        "• 1 red bell pepper (chopped)\n" +
                        "• 2 tablespoons soy sauce\n" +
                        "• 1 tablespoon hoisin sauce\n" +
                        "• 2 cloves garlic (minced)\n" +
                        "• 1-inch ginger (grated)\n" +
                        "• 1 green onion (sliced)",
                "1. Heat oil in a wok over medium-high heat.\n" +
                        "2. Add chicken and cook until browned. Remove and set aside.\n" +
                        "3. Stir-fry garlic, ginger, and bell peppers for 2 minutes.\n" +
                        "4. Add chicken back to the wok with soy sauce, hoisin sauce, and peanuts.\n" +
                        "5. Garnish with green onions and serve hot."
        ));

        recipesList.add(new Recipe(
                "Fried Rice",
                "Classic fried rice with vegetables and eggs.",
                "• 2 cups cooked rice\n" +
                        "• 1/2 cup peas and carrots\n" +
                        "• 2 eggs (beaten)\n" +
                        "• 2 tablespoons soy sauce\n" +
                        "• 1 tablespoon sesame oil\n" +
                        "• 1 green onion (chopped)\n" +
                        "• 2 cloves garlic (minced)",
                "1. Scramble eggs and set aside.\n" +
                        "2. Stir-fry garlic, peas, and carrots.\n" +
                        "3. Add rice, soy sauce, and sesame oil, mix well.\n" +
                        "4. Toss in scrambled eggs and green onions. Serve hot."
        ));


        //japanese recipes
        recipesList.add(new Recipe(
                "Sushi Rolls (Makizushi)",
                "Traditional rolled sushi filled with fresh ingredients.",
                "• 2 cups sushi rice (cooked and seasoned with rice vinegar)\n" +
                        "• 5 sheets nori (seaweed)\n" +
                        "• 1 cucumber (julienned)\n" +
                        "• 1 avocado (sliced)\n" +
                        "• 200g fresh raw salmon or tuna (optional)\n" +
                        "• Soy sauce for dipping\n" +
                        "• Pickled ginger and wasabi (optional)",
                "1. Place a sheet of nori on a bamboo rolling mat, shiny side down.\n" +
                        "2. Spread a thin layer of sushi rice over the nori.\n" +
                        "3. Add fillings like cucumber, avocado, and fish in a line across the rice.\n" +
                        "4. Roll tightly using the mat and cut into bite-sized pieces.\n" +
                        "5. Serve with soy sauce, wasabi, and pickled ginger."
        ));
        recipesList.add(new Recipe(
                "Ramen",
                "A comforting bowl of Japanese noodle soup with various toppings.",
                "• 200g fresh ramen noodles\n" +
                        "• 4 cups chicken or pork broth\n" +
                        "• 2 tablespoons soy sauce\n" +
                        "• 1 tablespoon miso paste (optional)\n" +
                        "• 2 boiled eggs (halved)\n" +
                        "• 100g sliced pork belly (chashu)\n" +
                        "• 1 sheet nori (cut into halves)\n" +
                        "• 1 green onion (chopped)",
                "1. Heat the broth and add soy sauce and miso paste (if using).\n" +
                        "2. Cook ramen noodles according to package instructions.\n" +
                        "3. Divide noodles into bowls and pour hot broth over them.\n" +
                        "4. Top with boiled eggs, chashu, nori, and green onions.\n" +
                        "5. Serve immediately."
        ));


        //korean recipes
        recipesList.add(new Recipe(
                "Kimchi Fried Rice",
                "Flavorful fried rice with kimchi and a fried egg on top.",
                "• 2 cups cooked rice\n" +
                        "• 1 cup kimchi (chopped)\n" +
                        "• 2 tablespoons kimchi juice\n" +
                        "• 2 teaspoons gochujang (Korean chili paste)\n" +
                        "• 1 tablespoon sesame oil\n" +
                        "• 2 eggs (fried)\n" +
                        "• 1 green onion (chopped)\n" +
                        "• 2 tablespoons vegetable oil",
                "1. Heat vegetable oil in a pan over medium-high heat.\n" +
                        "2. Add kimchi and stir-fry for 2 minutes.\n" +
                        "3. Add rice, kimchi juice, and gochujang. Stir well.\n" +
                        "4. Drizzle sesame oil and garnish with green onions.\n" +
                        "5. Top with a fried egg and serve hot."
        ));
        recipesList.add(new Recipe(
                "Bulgogi (Korean BBQ Beef)",
                "Sweet and savory grilled beef slices marinated in a Korean BBQ sauce.",
                "• 500g beef sirloin (thinly sliced)\n" +
                        "• 1/4 cup soy sauce\n" +
                        "• 2 tablespoons brown sugar\n" +
                        "• 1 tablespoon sesame oil\n" +
                        "• 2 cloves garlic (minced)\n" +
                        "• 1/4 teaspoon black pepper\n" +
                        "• 1/2 onion (sliced)\n" +
                        "• 1 green onion (chopped)",
                "1. Combine soy sauce, sugar, sesame oil, garlic, and black pepper in a bowl.\n" +
                        "2. Marinate the beef slices with the sauce for at least 30 minutes.\n" +
                        "3. Heat a grill or skillet and cook the beef until fully cooked.\n" +
                        "4. Garnish with green onions and serve with steamed rice."
        ));



        RecipeAdapter adapter = new RecipeAdapter(recipesList);
        recipeRecyclerView.setAdapter(adapter);

    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String wlc = prefs.getString("firstname", "");


        Intent intent = getIntent();
        if (intent.hasExtra("firstname")) {
            wlc = intent.getStringExtra("firstname");


            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("firstname", wlc);
            editor.apply();
        }

        welcome.setText("Welcome "+wlc+"!");

    }
}