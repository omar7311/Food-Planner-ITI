package com.example.food_planner_iti.plan_meal.presenter;

import androidx.lifecycle.LiveData;

import com.example.food_planner_iti.local_database.DatabaseManger;
import com.example.food_planner_iti.local_database.Meal;
import com.example.food_planner_iti.local_database.MealPlan;
import com.example.food_planner_iti.plan_meal.view.PlanFragmentInterface;
import com.example.food_planner_iti.repository.MealRepository;

import java.util.ArrayList;
import java.util.List;

public class PlanMealPresenter {
    PlanFragmentInterface planFragmentInterface;
    MealRepository mealRepository;

    public PlanMealPresenter(MealRepository mealRepository, PlanFragmentInterface planFragmentInterface) {
        this.mealRepository = mealRepository;
        this.planFragmentInterface = planFragmentInterface;
    }

    public LiveData<List<MealPlan>> sendDateMeal(String date) {
       return mealRepository.getPlanMealByDate(date);
    }
    public LiveData<List<MealPlan>> requestMealsPlan(){
       return mealRepository.getAllPlanMeal();
    }

    public void insertFavMeal(Meal meal) {
        mealRepository.insertFavMeal(meal);
    }

    public void deleteFavMeal(Meal meal) {
        mealRepository.deleteFavMeal(meal);
    }

    public void deletePlanMeal(Meal meal,String selection) {
       mealRepository.deletePlanMeal(meal,selection);
    }

}
