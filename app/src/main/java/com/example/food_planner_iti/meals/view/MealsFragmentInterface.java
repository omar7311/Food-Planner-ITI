package com.example.food_planner_iti.meals.view;

import com.example.food_planner_iti.model.MealItem;

import java.util.ArrayList;

public interface MealsFragmentInterface {

    void getMealsByArea(ArrayList<MealItem> mealItem);
    void getMealsByCategory(ArrayList<MealItem> mealItem);
     void noConnection() ;

}
