package com.example.food_planner_iti.meals.presenter;

import com.example.food_planner_iti.local_database.DatabaseManger;
import com.example.food_planner_iti.local_database.Meal;
import com.example.food_planner_iti.local_database.MealPlan;
import com.example.food_planner_iti.meals.view.MealsFragmentInterface;
import com.example.food_planner_iti.model.MealItem;
import com.example.food_planner_iti.network.NetworkManger;
import com.example.food_planner_iti.repository.MealRepository;

import java.util.ArrayList;

public class MealPresenter implements MealNetworkCallBack{
    MealsFragmentInterface mealsFragmentInterface;
     MealRepository mealRepository;
    public MealPresenter(MealsFragmentInterface mealsFragmentInterface,MealRepository mealRepository) {
        this.mealsFragmentInterface = mealsFragmentInterface;
        this.mealRepository=mealRepository;
    }
    public void insertFavMeal(Meal meal) {
        mealRepository.insertFavMeal(meal);
    }

    public void deleteFavMeal(Meal meal) {
        mealRepository.deleteFavMeal(meal);
    }
    public void insertPlanMeal(Meal meal,String selection) {
        mealRepository.insertPlanMeal(meal,selection);
    }

    public void deletePlanMeal(Meal meal,String selection) {
        mealRepository.deletePlanMeal(meal,selection);
    }
   public void sendCategoryName(String categoryName){
        mealRepository.getMealsByCategories(this,categoryName);
    }
    public void sendAreaName(String areaName){
         mealRepository.getMealsByArea(this,areaName);
    }

    @Override
    public void getMealsByArea(ArrayList<MealItem> mealItem) {
      mealsFragmentInterface.getMealsByArea(mealItem);
    }

    @Override
    public void getMealsByCategories(ArrayList<MealItem> mealItem) {
         mealsFragmentInterface.getMealsByCategory(mealItem);
    }

    @Override
    public void noConnection() {
        mealsFragmentInterface.noConnection();
    }
}
