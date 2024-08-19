package com.example.food_planner_iti.meals.presenter;

import com.example.food_planner_iti.meals.view.MealsFragmentInterface;
import com.example.food_planner_iti.model.MealItem;
import com.example.food_planner_iti.network.NetworkManger;

import java.util.ArrayList;

public class MealPresenter implements MealPresenterInterface{
    NetworkManger networkManger;
    MealsFragmentInterface mealsFragmentInterface;

    public MealPresenter(MealsFragmentInterface mealsFragmentInterface) {
        this.mealsFragmentInterface = mealsFragmentInterface;
        networkManger=new NetworkManger();
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
