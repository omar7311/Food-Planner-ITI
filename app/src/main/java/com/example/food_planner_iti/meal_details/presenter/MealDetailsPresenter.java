package com.example.food_planner_iti.meal_details.presenter;

import com.example.food_planner_iti.local_database.DatabaseManger;
import com.example.food_planner_iti.local_database.Meal;
import com.example.food_planner_iti.local_database.MealPlan;
import com.example.food_planner_iti.meal_details.view.MealDetailFragmentInterface;
import com.example.food_planner_iti.network.NetworkManger;

public class MealDetailsPresenter implements MealDetailsPresenterInterface {
    MealDetailFragmentInterface mealDetailFragmentInterface;
    NetworkManger networkManger;
    DatabaseManger databaseManger;

    public MealDetailsPresenter(MealDetailFragmentInterface mealDetailFragmentInterface,DatabaseManger databaseManger) {
        this.mealDetailFragmentInterface = mealDetailFragmentInterface;
        networkManger = new NetworkManger();
        this.databaseManger=databaseManger;
    }
    public void insertFavMeal(Meal meal) {
        databaseManger.insertFavMeal(meal);
    }

    public void deleteFavMeal(Meal meal) {
        databaseManger.deleteFavMeal(meal);
    }
    public void insertPlanMeal(MealPlan meal) {
        databaseManger.insertPlanMeal(meal);
    }

    public void deletePlanMeal(MealPlan meal) {
        databaseManger.deletePlanMeal(meal);
    }
    public void sendMealId(String id) {
        networkManger.getMealDetailsById(this, id);
    }

    @Override
    public void getMealDetails(Meal meal) {
        mealDetailFragmentInterface.getMealDetails(meal);
    }

    @Override
    public void errorMessage(String error) {
        mealDetailFragmentInterface.errorMessage(error);
    }
}
