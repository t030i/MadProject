package com.example.mad_mp;

public class Recipe {
    private String title;
    private String details;
    private String ingredients;
    private String instructions;

    // Constructor accepting 4 parameters
    public Recipe(String title, String details, String ingredients, String instructions) {
        this.title = title;
        this.details = details;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getDetails() {
        return details;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getInstructions() {
        return instructions;
    }
}
