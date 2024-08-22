package com.example.food_planner_iti.meals.view;

import com.example.food_planner_iti.local_database.Meal;

public interface ClickListener {
    void onClickInsert(Meal meal);
    void onClickDelete(Meal meal);

}
