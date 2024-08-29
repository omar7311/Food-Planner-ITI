package com.example.food_planner_iti.local_database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class DatabaseManger implements LocalDataSource {
    Context context;


    public DatabaseManger(Context context) {
        this.context = context;
    }

    public LiveData<List<Meal>> getAllFavMeal(){
      return MealDatabase.getInstance(context).getProductDAO().getAllFavMeals();
    }
    public void deleteAllMeal(){
     new Thread( ()-> MealDatabase.getInstance(context).getProductDAO().deleteAllMeal()).start();
    }
    public LiveData<List<MealPlan>> getAllPlanMeal(){
       return MealDatabase.getInstance(context).getProductDAO().getAllMealsPlan();

    }
    public void deleteAllMealPlan(){
       new Thread( ()-> MealDatabase.getInstance(context).getProductDAO().deleteAllMealPlan()).start();
    }
    public  LiveData<List<MealPlan>>  getPlanMealByDate(String date){
       return MealDatabase.getInstance(context).getProductDAO().getMealsPlanByDate(date);

    }
    public void insertFavMeal(Meal meal){
       new Thread( ()->MealDatabase.getInstance(context).getProductDAO().insertMeal(meal)).start();
        FirebaseDatabase.getInstance().getReference("Meals")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("meal_fav").child(meal.getId()).setValue(meal);
    }
    public void deleteFavMeal(Meal meal){
      new Thread( ()-> MealDatabase.getInstance(context).getProductDAO().deleteMeal(meal)).start();
        FirebaseDatabase.getInstance().getReference("Meals")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("meal_fav").child(meal.getId()).setValue(null);
    }
    public void insertPlanMeal(Meal meal,String selection){
       new Thread( ()-> MealDatabase.getInstance(context).getProductDAO().insertMeal(getMealPlan(meal, selection))).start();
        FirebaseDatabase.getInstance().getReference("Meals")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("meal_plan").child(getMealPlan(meal,selection).getId()).setValue(getMealPlan(meal,selection));
    }
    public void deletePlanMeal(Meal meal ,String selection){
       new Thread( ()-> MealDatabase.getInstance(context).getProductDAO().deleteMeal(getMealPlan(meal, selection))).start();
        FirebaseDatabase.getInstance().getReference("Meals")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("meal_plan").child(getMealPlan(meal, selection).getId()).setValue(null);
    }
    public MealPlan getMealPlan(Meal meal,String date){
        MealPlan mealPlan=new MealPlan();
        mealPlan.setId(meal.getId());
        mealPlan.setDate(date);
        mealPlan.setCountry(meal.getCountry());
        mealPlan.setIngredients(meal.getIngredients());
        mealPlan.setIngredientsImage(meal.getIngredientsImage());
        mealPlan.setImageUrl(meal.getImageUrl());
        mealPlan.setName(meal.getName());
        mealPlan.setVideoUrl(meal.getVideoUrl());
        mealPlan.setSteps(meal.getSteps());
        return mealPlan;
    }
}
