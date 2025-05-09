package com.example.food_planner_iti.meals.presenter;

import com.example.food_planner_iti.model.MealItem;

import java.util.ArrayList;

public interface MealNetworkCallBack {
    void getMealsByCategories(ArrayList<MealItem> mealItems);
    void getMealsByArea(ArrayList<MealItem> mealItems);
    void noConnection();

}
