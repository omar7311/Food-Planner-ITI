package com.example.food_planner_iti.home.presenter;

import android.content.Context;

import com.example.food_planner_iti.home.view.HomeFragmentInterface;
import com.example.food_planner_iti.local_database.DatabaseManger;
import com.example.food_planner_iti.local_database.Meal;
import com.example.food_planner_iti.local_database.MealPlan;
import com.example.food_planner_iti.model.AreasName;
import com.example.food_planner_iti.model.CategoriesItem;
import com.example.food_planner_iti.model.MealDetails;
import com.example.food_planner_iti.model.SingleRandomMeal;
import com.example.food_planner_iti.network.NetworkManger;
import com.example.food_planner_iti.repository.MealRepository;

import java.util.ArrayList;

public class HomePresenter implements HomeNetworkCallBack{
    HomeFragmentInterface homeFragmentInterface;
    MealRepository mealRepository;


    public HomePresenter(HomeFragmentInterface homeFragmentInterface, MealRepository mealRepository) {
        this.homeFragmentInterface = homeFragmentInterface;
        this.mealRepository=mealRepository;
        mealRepository.getRandomMeal(this);
        mealRepository.getCategories(this);
        mealRepository.getCountries(this);
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

    @Override
    public void getRandomMeal(Meal meal) {

        homeFragmentInterface.getSingleRandomMeal(meal);
    }

    @Override
    public void getCategories(ArrayList<CategoriesItem> categoriesItemArrayList) {
        homeFragmentInterface.getAllCategoryItems(categoriesItemArrayList);
    }

    @Override
    public void getCountries(ArrayList<AreasName> areasNames) {
        homeFragmentInterface.getCountries(areasNames);
    }

    @Override
    public void noConnection() {
        homeFragmentInterface.noConnection();
    }


}
