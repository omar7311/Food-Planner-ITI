package com.example.food_planner_iti.search.presenter;

import com.example.food_planner_iti.local_database.Meal;
import com.example.food_planner_iti.model.MealItem;
import com.example.food_planner_iti.network.NetworkManger;
import com.example.food_planner_iti.search.view.SearchFragmentInterface;

import java.util.ArrayList;

public class SearchPresenter implements SearchPresenterInterface{
    SearchFragmentInterface searchFragmentInterface;
    NetworkManger networkManger;

    public SearchPresenter(SearchFragmentInterface searchFragmentInterface) {
        this.searchFragmentInterface = searchFragmentInterface;
        networkManger=new NetworkManger();
    }
    public void sendMealName(String name){
           networkManger.searchByMealName(this,name);
    }
   public void sendIngredientName(String name){
          networkManger.searchByIngredient(this,name);
    }
    @Override
    public void getMealsByName(ArrayList<Meal> meals) {
     searchFragmentInterface.getMealsByName(meals);
    }

    @Override
    public void getMealsByIngredient(ArrayList<MealItem> mealItems) {
      searchFragmentInterface.getMealsByIngredient(mealItems);
    }

    @Override
    public void errorMessage(String error) {
        searchFragmentInterface.errorMessage(error);
    }
}
