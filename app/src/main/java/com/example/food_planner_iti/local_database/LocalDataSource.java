package com.example.food_planner_iti.local_database;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface LocalDataSource {
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
