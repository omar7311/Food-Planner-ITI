package com.example.food_planner_iti.home.presenter;

import com.example.food_planner_iti.local_database.Meal;
import com.example.food_planner_iti.model.AreasName;
import com.example.food_planner_iti.model.CategoriesItem;
import com.example.food_planner_iti.model.MealDetails;
import com.example.food_planner_iti.model.SingleRandomMeal;

import java.util.ArrayList;

public interface HomePresenterInterface {
    void getSingleRandomMeal(Meal meal);
    void getAllCategoryItems(ArrayList<CategoriesItem> categoriesItemArrayList);
    void getCountries(ArrayList<AreasName> areasNames);
    void errorMessage(String error);
}
