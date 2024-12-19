package com.example.mad_mp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private final List<Recipe> recipeList;

    // Constructor
    public RecipeAdapter(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each recipe item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        // Get the current recipe object
        Recipe recipe = recipeList.get(position);

        // Bind data to the views
        holder.recipeTitle.setText(recipe.getTitle());
        holder.recipeDetails.setText(recipe.getDetails());
        holder.recipeIngredients.setText("Ingredients:\n" + recipe.getIngredients());
        holder.recipeInstructions.setText("Instructions:\n" + recipe.getInstructions());
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    // ViewHolder class
    static class RecipeViewHolder extends RecyclerView.ViewHolder {

        // Declare views
        TextView recipeTitle, recipeDetails, recipeIngredients, recipeInstructions;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize views
            recipeTitle = itemView.findViewById(R.id.recipeTitle);
            recipeDetails = itemView.findViewById(R.id.recipeDetails);
            recipeIngredients = itemView.findViewById(R.id.recipeIngredients);
            recipeInstructions = itemView.findViewById(R.id.recipeInstructions);
        }
    }
}