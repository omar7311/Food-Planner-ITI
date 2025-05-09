package com.example.food_planner_iti.local_database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.DeleteTable;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.food_planner_iti.model.MealDetails;


import java.util.List;

@Dao
public interface MealDao {
    @Query("SELECT * FROM meal_table")
     LiveData<List<Meal>> getAllFavMeals();
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMeal(Meal meal);
    @Delete
    void deleteMeal(Meal meal);
    @Query("SELECT * FROM mealPlan_table")
    LiveData<List<MealPlan>> getAllMealsPlan();
    @Query("SELECT * FROM mealPlan_table WHERE date=:date")
    LiveData<List<MealPlan>> getMealsPlanByDate(String date);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMeal(MealPlan mealPlan);
    @Delete
    void deleteMeal(MealPlan mealPlan);
    @Query("DELETE FROM meal_table")
    void deleteAllMeal();
    @Query("DELETE FROM mealPlan_table")
    void deleteAllMealPlan();
}
