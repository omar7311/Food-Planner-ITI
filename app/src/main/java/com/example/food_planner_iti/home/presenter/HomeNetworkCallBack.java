package com.example.food_planner_iti.home.presenter;

import com.example.food_planner_iti.local_database.Meal;
import com.example.food_planner_iti.model.AreasName;
import com.example.food_planner_iti.model.CategoriesItem;

import java.util.ArrayList;

public interface HomeNetworkCallBack {
    void getRandomMeal(Meal meal);
    void getCategories(ArrayList<CategoriesItem> categoriesItems);
    void getCountries(ArrayList<AreasName> areasNames);
    void noConnection();

}
