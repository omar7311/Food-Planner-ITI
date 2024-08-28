package com.example.food_planner_iti.repository;

import androidx.lifecycle.LiveData;

import com.example.food_planner_iti.home.presenter.HomeNetworkCallBack;
import com.example.food_planner_iti.local_database.Meal;
import com.example.food_planner_iti.local_database.MealPlan;
import com.example.food_planner_iti.meal_details.presenter.MealDetailsNetworkCallBack;
import com.example.food_planner_iti.meals.presenter.MealNetworkCallBack;
import com.example.food_planner_iti.model.MealItem;
import com.example.food_planner_iti.search.presenter.SearchNetworkCallBack;

import java.util.ArrayList;
import java.util.List;

public interface MealRepository {
    void getRandomMeal(HomeNetworkCallBack homeNetworkCallBack);
    void getCategories(HomeNetworkCallBack homeNetworkCallBack);
    void getCountries(HomeNetworkCallBack homeNetworkCallBack);
    void getMealsByCategories(MealNetworkCallBack mealNetworkCallBack, String category);
    void getMealsByArea(MealNetworkCallBack mealNetworkCallBack ,String area);
    void getMealDetailsById(MealDetailsNetworkCallBack mealDetailsNetworkCallBack , String id);
    void searchByNameMeal(SearchNetworkCallBack searchNetworkCallBack, String name);
    void searchByIngredient(SearchNetworkCallBack searchNetworkCallBack , String ingredient);
    LiveData<List<Meal>> getAllFavMeal();
    void deleteAllMeal();
    LiveData<List<MealPlan>> getAllPlanMeal();
    void deleteAllMealPlan();
    LiveData<List<MealPlan>>  getPlanMealByDate(String date);
    void insertFavMeal(Meal meal);
    void deleteFavMeal(Meal meal);
    void insertPlanMeal(Meal meal,String selection);
    void deletePlanMeal(Meal meal ,String selection);
}
