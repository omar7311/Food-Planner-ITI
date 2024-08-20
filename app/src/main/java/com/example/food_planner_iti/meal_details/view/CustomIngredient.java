package com.example.food_planner_iti.meal_details.view;

public class CustomIngredient {
    private String ingredient;
    private String ingredientImages;

    public CustomIngredient(String ingredient, String ingredientImages) {
        this.ingredient = ingredient;
        this.ingredientImages = ingredientImages;
    }

    public String getIngredient() {
        return ingredient;
    }

    public String getIngredientImages() {
        return ingredientImages;
    }
}
