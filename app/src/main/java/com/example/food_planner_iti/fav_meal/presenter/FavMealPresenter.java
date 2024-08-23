package com.example.food_planner_iti.fav_meal.presenter;

import com.example.food_planner_iti.fav_meal.view.FavFragmentInterface;
import com.example.food_planner_iti.local_database.DatabaseManger;
import com.example.food_planner_iti.local_database.Meal;
import com.example.food_planner_iti.local_database.MealPlan;

import java.util.ArrayList;

public class FavMealPresenter implements FavMealPresenterInterface{
    DatabaseManger databaseManger;
    FavFragmentInterface favFragmentInterface;

    public FavMealPresenter(DatabaseManger databaseManger, FavFragmentInterface favFragmentInterface) {
        this.databaseManger = databaseManger;
        this.favFragmentInterface = favFragmentInterface;
        databaseManger.getAllFavMeal(this::getAllFavMeal);
    }
    public void deleteFavMeal(Meal meal) {
        databaseManger.deleteFavMeal(meal);
    }

    @Override
    public void getAllFavMeal(ArrayList<Meal> meals) {
        favFragmentInterface.getAllFavMeal(meals);
    }
    public void insertPlanMeal(MealPlan meal) {
        databaseManger.insertPlanMeal(meal);
    }

    public void deletePlanMeal(MealPlan meal) {
        databaseManger.deletePlanMeal(meal);
    }
}
