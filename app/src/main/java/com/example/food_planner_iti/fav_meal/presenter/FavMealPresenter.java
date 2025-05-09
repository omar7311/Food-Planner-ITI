package com.example.food_planner_iti.fav_meal.presenter;

import androidx.lifecycle.LiveData;

import com.example.food_planner_iti.fav_meal.view.FavFragmentInterface;
import com.example.food_planner_iti.local_database.Meal;
import com.example.food_planner_iti.repository.MealRepository;

import java.util.List;

public class FavMealPresenter {
    FavFragmentInterface favFragmentInterface;
    MealRepository mealRepository;

    public FavMealPresenter(MealRepository mealRepository, FavFragmentInterface favFragmentInterface) {
        this.favFragmentInterface = favFragmentInterface;
        this.mealRepository=mealRepository;
    }
    public void deleteFavMeal(Meal meal) {
        mealRepository.deleteFavMeal(meal);
    }

    public LiveData<List<Meal>> getAllFavMeal() {
      return mealRepository.getAllFavMeal();
    }
    public void insertPlanMeal(Meal meal,String selection) {
        mealRepository.insertPlanMeal(meal,selection);
    }

    public void deletePlanMeal(Meal meal,String selection) {
        mealRepository.deletePlanMeal(meal,selection);
    }

}
