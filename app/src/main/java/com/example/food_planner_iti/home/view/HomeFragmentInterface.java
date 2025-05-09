package com.example.food_planner_iti.home.view;

import com.example.food_planner_iti.local_database.Meal;
import com.example.food_planner_iti.model.AreasName;
import com.example.food_planner_iti.model.CategoriesItem;

import java.util.ArrayList;

public interface HomeFragmentInterface {
    void getSingleRandomMeal(Meal meal);
     void getAllCategoryItems(ArrayList<CategoriesItem> categoriesItemArrayList) ;

     void getCountries(ArrayList<AreasName> areasNames) ;

     void noConnection() ;
}
