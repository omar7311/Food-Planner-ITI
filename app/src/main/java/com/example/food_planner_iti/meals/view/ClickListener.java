package com.example.food_planner_iti.meals.view;

import android.widget.CheckBox;

import com.example.food_planner_iti.local_database.Meal;
import com.example.food_planner_iti.local_database.MealPlan;

public interface ClickListener {
    void onClickInsert(Meal meal);
    void onClickDelete(Meal meal);
    void onClickInsertMealPlan(CheckBox plan, Meal meal);
    void onClickDeleteMealPlan(Meal meal);

}
