package com.example.food_planner_iti.plan_meal.presenter;

import com.example.food_planner_iti.local_database.MealPlan;

import java.util.ArrayList;

public interface PlanMealPresenterInterface {
    void getAllMealsPlan(ArrayList<MealPlan> mealPlans);
}
