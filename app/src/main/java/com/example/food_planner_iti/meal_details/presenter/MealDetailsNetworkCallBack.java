package com.example.food_planner_iti.meal_details.presenter;

import com.example.food_planner_iti.local_database.Meal;

public interface MealDetailsNetworkCallBack {
    void getMealDetailsById(Meal meal);
    void noConnection();

}
