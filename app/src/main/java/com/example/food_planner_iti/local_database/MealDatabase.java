package com.example.food_planner_iti.local_database;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.food_planner_iti.model.MealDetails;


@Database(entities = {Meal.class},version = 1)
@TypeConverters(converters.class)
public abstract class MealDatabase extends RoomDatabase {
    private static MealDatabase instance = null;
    public abstract MealDao getProductDAO();
    public static synchronized MealDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            MealDatabase.class, "meal_db").build();
        }
        return instance; }
}
