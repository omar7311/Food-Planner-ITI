package com.example.food_planner_iti.local_database;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.food_planner_iti.model.MealDetails;


@Database(entities = {MealDetails.class},version = 1)

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
