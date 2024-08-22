package com.example.food_planner_iti.local_database;

import android.content.Context;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.food_planner_iti.fav_meal.presenter.FavMealPresenterInterface;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManger {
    Context context;
    LifecycleOwner owner;

    public DatabaseManger(Context context,LifecycleOwner owner) {
        this.context = context;
        this.owner=owner;
    }

    public void getAllFavMeal(FavMealPresenterInterface favMealPresenterInterface){
       MealDatabase.getInstance(context).getProductDAO().getAllFavMeals().observe(owner, new Observer<List<Meal>>() {
           @Override
           public void onChanged(List<Meal> meals) {
               favMealPresenterInterface.getAllFavMeal((ArrayList<Meal>) meals);
           }
       });
    }
    public void getAllPlanMeal(){
        MealDatabase.getInstance(context).getProductDAO().getAllMealsPlan();

    }
    public void insertFavMeal(Meal meal){
        MealDatabase.getInstance(context).getProductDAO().insertMeal(meal);
    }
    public void deleteFavMeal(Meal meal){
        MealDatabase.getInstance(context).getProductDAO().deleteMeal(meal);
    }
    public void insertPlanMeal(MealPlan meal){
        MealDatabase.getInstance(context).getProductDAO().insertMeal(meal);
    }
    public void deletePlanMeal(MealPlan meal){
        MealDatabase.getInstance(context).getProductDAO().deleteMeal(meal);
    }
}
