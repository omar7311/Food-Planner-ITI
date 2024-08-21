package com.example.food_planner_iti.search.view;

import com.example.food_planner_iti.local_database.Meal;
import com.example.food_planner_iti.model.MealItem;

import java.util.ArrayList;

public interface SearchFragmentInterface {
    void getMealsByName(ArrayList<Meal> meals);
    void getMealsByIngredient(ArrayList<MealItem> mealItems);
    void errorMessage(String error);
}
