package com.example.food_planner_iti.meals.presenter;

import com.example.food_planner_iti.model.MealItem;

import java.util.ArrayList;

public interface MealPresenterInterface {
    void getMealsByArea(ArrayList<MealItem> mealItem);
    void getMealsByCategory(ArrayList<MealItem> mealItem);
    public void errorMessage(String error) ;

}
