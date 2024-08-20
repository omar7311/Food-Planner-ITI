package com.example.food_planner_iti.meal_details.presenter;

import com.example.food_planner_iti.local_database.Meal;
import com.example.food_planner_iti.meal_details.view.MealDetailFragmentInterface;
import com.example.food_planner_iti.network.NetworkManger;

public class MealDetailsPresenter implements MealDetailsPresenterInterface {
    MealDetailFragmentInterface mealDetailFragmentInterface;
    NetworkManger networkManger;

    public MealDetailsPresenter(MealDetailFragmentInterface mealDetailFragmentInterface) {
        this.mealDetailFragmentInterface = mealDetailFragmentInterface;
        networkManger = new NetworkManger();
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
