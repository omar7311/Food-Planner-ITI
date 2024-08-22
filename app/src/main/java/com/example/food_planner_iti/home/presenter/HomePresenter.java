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

import java.util.ArrayList;

public class HomePresenter implements HomePresenterInterface {
    HomeFragmentInterface homeFragmentInterface;
    NetworkManger networkManger;
    DatabaseManger databaseManger;


    public HomePresenter(HomeFragmentInterface homeFragmentInterface, DatabaseManger databaseManger) {
        this.homeFragmentInterface = homeFragmentInterface;
        networkManger = new NetworkManger();
        this.databaseManger = databaseManger;
        networkManger.getRandomMeal(this);
        networkManger.getCategories(this);
        networkManger.getCountries(this);
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

    @Override
    public void getSingleRandomMeal(Meal meal) {
        homeFragmentInterface.getSingleRandomMeal(meal);
    }

    @Override
    public void getAllCategoryItems(ArrayList<CategoriesItem> categoriesItemArrayList) {
        homeFragmentInterface.getAllCategoryItems(categoriesItemArrayList);
    }

    @Override
    public void getCountries(ArrayList<AreasName> areasNames) {
        homeFragmentInterface.getCountries(areasNames);
    }

    @Override
    public void errorMessage(String error) {
        homeFragmentInterface.errorMessage(error);
    }
}
