package com.example.food_planner_iti.search.presenter;

import com.example.food_planner_iti.local_database.Meal;
import com.example.food_planner_iti.model.MealItem;
import com.example.food_planner_iti.network.NetworkManger;
import com.example.food_planner_iti.repository.MealRepository;
import com.example.food_planner_iti.search.view.SearchFragmentInterface;

import java.util.ArrayList;

public class SearchPresenter implements SearchNetworkCallBack{
    SearchFragmentInterface searchFragmentInterface;
    MealRepository mealRepository;

    public SearchPresenter(SearchFragmentInterface searchFragmentInterface,MealRepository mealRepository) {
        this.searchFragmentInterface = searchFragmentInterface;
       this.mealRepository=mealRepository;
    }
    public void sendMealName(String name){
           mealRepository.searchByNameMeal(this,name);
    }
   public void sendIngredientName(String name){
         mealRepository.searchByIngredient(this,name);
    }
    @Override
    public void searchByNameMeal(ArrayList<Meal> meals) {
     searchFragmentInterface.getMealsByName(meals);
    }

    @Override
    public void searchByIngredient(ArrayList<MealItem> mealItems) {
      searchFragmentInterface.getMealsByIngredient(mealItems);
    }

    @Override
    public void noConnection() {
        searchFragmentInterface.noConnection();
    }
}
