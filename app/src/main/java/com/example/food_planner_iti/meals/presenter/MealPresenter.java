package com.example.food_planner_iti.meals.presenter;

import com.example.food_planner_iti.local_database.DatabaseManger;
import com.example.food_planner_iti.local_database.Meal;
import com.example.food_planner_iti.meals.view.MealsFragmentInterface;
import com.example.food_planner_iti.model.MealItem;
import com.example.food_planner_iti.network.NetworkManger;

import java.util.ArrayList;

public class MealPresenter implements MealPresenterInterface{
    NetworkManger networkManger;
    MealsFragmentInterface mealsFragmentInterface;
    DatabaseManger databaseManger;

    public MealPresenter(MealsFragmentInterface mealsFragmentInterface,DatabaseManger databaseManger) {
        this.mealsFragmentInterface = mealsFragmentInterface;
        networkManger=new NetworkManger();
        this.databaseManger=databaseManger;
    }
    public void insertFavMeal(Meal meal) {
        databaseManger.insertFavMeal(meal);
    }

    public void deleteFavMeal(Meal meal) {
        databaseManger.deleteFavMeal(meal);
    }
   public void sendCategoryName(String categoryName){
        networkManger.getMealsByCategory(this,categoryName);
    }
    public void sendAreaName(String areaName){
         networkManger.getMealsByArea(this,areaName);
    }

    @Override
    public void getMealsByArea(ArrayList<MealItem> mealItem) {
      mealsFragmentInterface.getMealsByArea(mealItem);
    }

    @Override
    public void getMealsByCategory(ArrayList<MealItem> mealItem) {
         mealsFragmentInterface.getMealsByCategory(mealItem);
    }

    @Override
    public void errorMessage(String error) {
        mealsFragmentInterface.errorMessage(error);
    }
}
