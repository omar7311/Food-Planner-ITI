package com.example.food_planner_iti.meal_details.presenter;

import com.example.food_planner_iti.local_database.DatabaseManger;
import com.example.food_planner_iti.local_database.Meal;
import com.example.food_planner_iti.local_database.MealPlan;
import com.example.food_planner_iti.meal_details.view.MealDetailFragmentInterface;
import com.example.food_planner_iti.network.NetworkManger;
import com.example.food_planner_iti.repository.MealRepository;

public class MealDetailsPresenter implements MealDetailsNetworkCallBack {
    MealDetailFragmentInterface mealDetailFragmentInterface;
   MealRepository mealRepository;

    public MealDetailsPresenter(MealDetailFragmentInterface mealDetailFragmentInterface ,MealRepository mealRepository) {
        this.mealDetailFragmentInterface = mealDetailFragmentInterface;
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
    public void sendMealId(String id) {
        mealRepository.getMealDetailsById(this, id);
    }

    @Override
    public void getMealDetailsById(Meal meal) {
        mealDetailFragmentInterface.getMealDetails(meal);
    }

    @Override
    public void noConnection() {
        mealDetailFragmentInterface.noConnection();
    }
}
