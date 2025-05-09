package com.example.food_planner_iti.network;

import com.example.food_planner_iti.home.presenter.HomeNetworkCallBack;
import com.example.food_planner_iti.meal_details.presenter.MealDetailsNetworkCallBack;
import com.example.food_planner_iti.meals.presenter.MealNetworkCallBack;
import com.example.food_planner_iti.search.presenter.SearchNetworkCallBack;

public interface RemoteDataSource {
     void getRandomMeal(HomeNetworkCallBack homeNetworkCallBack);
     void getCategories(HomeNetworkCallBack homeNetworkCallBack);
     void getCountries(HomeNetworkCallBack homeNetworkCallBack);
     void getMealsByCategory(MealNetworkCallBack mealNetworkCallBack, String strCategory);
     void getMealsByArea(MealNetworkCallBack mealNetworkCallBack,String strArea);
     void getMealDetailsById(MealDetailsNetworkCallBack mealDetailsNetworkCallBack, String id);
     void searchByMealName(SearchNetworkCallBack searchNetworkCallBack, String name);
     void searchByIngredient(SearchNetworkCallBack searchNetworkCallBack,String name);
}
