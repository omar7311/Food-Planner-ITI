package com.example.food_planner_iti.home.presenter;

import com.example.food_planner_iti.home.view.HomeFragmentInterface;
import com.example.food_planner_iti.local_database.Meal;
import com.example.food_planner_iti.model.AreasName;
import com.example.food_planner_iti.model.CategoriesItem;
import com.example.food_planner_iti.model.MealDetails;
import com.example.food_planner_iti.model.SingleRandomMeal;
import com.example.food_planner_iti.network.NetworkManger;

import java.util.ArrayList;

public class HomePresenter implements HomePresenterInterface{
    HomeFragmentInterface homeFragmentInterface;
    NetworkManger networkManger;

    public HomePresenter(HomeFragmentInterface homeFragmentInterface) {
        this.homeFragmentInterface = homeFragmentInterface;
        networkManger=new NetworkManger();
        networkManger.getRandomMeal(this);
        networkManger.getCategories(this);
        networkManger.getCountries(this);
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
