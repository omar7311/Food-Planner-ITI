package com.example.food_planner_iti.search.presenter;

import com.example.food_planner_iti.local_database.Meal;
import com.example.food_planner_iti.model.MealItem;

import java.util.ArrayList;

public interface SearchNetworkCallBack {
    void searchByNameMeal(ArrayList<Meal> meals);
    void searchByIngredient(ArrayList<MealItem> mealItems);
    void noConnection();
}
