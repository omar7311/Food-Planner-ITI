package com.example.food_planner_iti.meal_details.view;

import com.example.food_planner_iti.local_database.Meal;

public interface MealDetailFragmentInterface {
    void onBackPressed();

    void getMealDetails(Meal meal);
    void errorMessage(String error);
}
