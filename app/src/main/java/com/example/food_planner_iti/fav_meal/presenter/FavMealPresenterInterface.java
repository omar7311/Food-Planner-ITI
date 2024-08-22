package com.example.food_planner_iti.fav_meal.presenter;

import com.example.food_planner_iti.local_database.Meal;

import java.util.ArrayList;

public interface FavMealPresenterInterface {
    void getAllFavMeal(ArrayList<Meal> meals);
}
