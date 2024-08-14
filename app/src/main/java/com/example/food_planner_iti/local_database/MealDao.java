package com.example.food_planner_iti.local_database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.food_planner_iti.model.MealDetails;


import java.util.List;

@Dao
public interface MealDao {
    @Query("SELECT * FROM meal_table")
     LiveData<List<MealDetails>> getAllFavMeals();
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMeal(MealDetails mealDetails);
    @Delete
    void deleteMeal(MealDetails mealDetails);
}
