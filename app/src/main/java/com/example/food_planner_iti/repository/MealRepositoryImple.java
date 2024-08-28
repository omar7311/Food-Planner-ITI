package com.example.food_planner_iti.repository;

import androidx.lifecycle.LiveData;

import com.example.food_planner_iti.home.presenter.HomeNetworkCallBack;
import com.example.food_planner_iti.local_database.LocalDataSource;
import com.example.food_planner_iti.local_database.Meal;
import com.example.food_planner_iti.local_database.MealPlan;
import com.example.food_planner_iti.meal_details.presenter.MealDetailsNetworkCallBack;
import com.example.food_planner_iti.meals.presenter.MealNetworkCallBack;
import com.example.food_planner_iti.network.RemoteDataSource;
import com.example.food_planner_iti.search.presenter.SearchNetworkCallBack;

import java.util.List;

public class MealRepositoryImple implements MealRepository{
    RemoteDataSource remoteDataSource;
    LocalDataSource localDataSource;
   ;

    public MealRepositoryImple(RemoteDataSource remoteDataSource, LocalDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }


    @Override
    public void getRandomMeal(HomeNetworkCallBack homeNetworkCallBack) {
        remoteDataSource.getRandomMeal(homeNetworkCallBack);
    }

    @Override
    public void getCategories(HomeNetworkCallBack homeNetworkCallBack) {
      remoteDataSource.getCategories(homeNetworkCallBack);
    }

    @Override
    public void getCountries(HomeNetworkCallBack homeNetworkCallBack) {
          remoteDataSource.getCountries(homeNetworkCallBack);
    }

    @Override
    public void getMealsByCategories(MealNetworkCallBack mealNetworkCallBack,String category) {
          remoteDataSource.getMealsByCategory(mealNetworkCallBack,category);
    }

    @Override
    public void getMealsByArea(MealNetworkCallBack mealNetworkCallBack,String area) {
          remoteDataSource.getMealsByArea(mealNetworkCallBack, area);
    }

    @Override
    public void getMealDetailsById(MealDetailsNetworkCallBack mealDetailsNetworkCallBack,String id) {
       remoteDataSource.getMealDetailsById(mealDetailsNetworkCallBack,id);
    }

    @Override
    public void searchByNameMeal(SearchNetworkCallBack searchNetworkCallBack,String name) {
        remoteDataSource.searchByMealName(searchNetworkCallBack,name);
    }

    @Override
    public void searchByIngredient(SearchNetworkCallBack searchNetworkCallBack ,String ingredient) {
       remoteDataSource.searchByIngredient(searchNetworkCallBack,ingredient);
    }

    @Override
    public LiveData<List<Meal>> getAllFavMeal() {
        return localDataSource.getAllFavMeal();
    }

    @Override
    public void deleteAllMeal() {
        localDataSource.deleteAllMeal();
    }

    @Override
    public LiveData<List<MealPlan>> getAllPlanMeal() {
        return localDataSource.getAllPlanMeal();
    }

    @Override
    public void deleteAllMealPlan() {
         localDataSource.deleteAllMealPlan();
    }

    @Override
    public LiveData<List<MealPlan>> getPlanMealByDate(String date) {
        return localDataSource.getPlanMealByDate(date);
    }

    @Override
    public void insertFavMeal(Meal meal) {
     localDataSource.insertFavMeal(meal);
    }

    @Override
    public void deleteFavMeal(Meal meal) {
         localDataSource.deleteFavMeal(meal);
    }

    @Override
    public void insertPlanMeal(Meal meal, String selection) {
      localDataSource.insertPlanMeal(meal,selection);
    }

    @Override
    public void deletePlanMeal(Meal meal, String selection) {
            localDataSource.deletePlanMeal(meal,selection);
    }


}
