package com.example.food_planner_iti.plan_meal.presenter;

import com.example.food_planner_iti.local_database.DatabaseManger;
import com.example.food_planner_iti.local_database.Meal;
import com.example.food_planner_iti.local_database.MealPlan;
import com.example.food_planner_iti.plan_meal.view.PlanFragmentInterface;

import java.util.ArrayList;

public class PlanMealPresenter implements PlanMealPresenterInterface {
    DatabaseManger databaseManger;
    PlanFragmentInterface planFragmentInterface;

    public PlanMealPresenter(DatabaseManger databaseManger, PlanFragmentInterface planFragmentInterface) {
        this.databaseManger = databaseManger;
        this.planFragmentInterface = planFragmentInterface;
    }

    public void sendDateMeal(String date) {
        databaseManger.getPlanMealByDate(this, date);
    }
    public void requestMealsPlan(){ databaseManger.getAllPlanMeal(this::getAllMealsPlan);}
    @Override
    public void getAllMealsPlan(ArrayList<MealPlan> mealPlans) {
        planFragmentInterface.getAllMealsPlan(mealPlans);
    }
    public void insertFavMeal(Meal meal) {
        databaseManger.insertFavMeal(meal);
    }

    public void deleteFavMeal(Meal meal) {
        databaseManger.deleteFavMeal(meal);
    }

    public void deletePlanMeal(MealPlan meal) {
        databaseManger.deletePlanMeal(meal);
    }

}
