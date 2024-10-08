package com.example.food_planner_iti.network;

import com.example.food_planner_iti.model.AllAreaNames;
import com.example.food_planner_iti.model.AllCategoriesName;
import com.example.food_planner_iti.model.AllIngredient;
import com.example.food_planner_iti.model.AllMealCategories;
import com.example.food_planner_iti.model.FilterByArea;
import com.example.food_planner_iti.model.FilterByCategory;
import com.example.food_planner_iti.model.FilterByIngredient;
import com.example.food_planner_iti.model.FullMealDetailById;
import com.example.food_planner_iti.model.SearchMealByName;
import com.example.food_planner_iti.model.SingleRandomMeal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WebServices {
    @GET("search.php")
    Call<SearchMealByName> searchMealByName(@Query("s") String strMeal);

    @GET("lookup.php")
    Call<FullMealDetailById> fullMealDetailById(@Query("i") String idMeal);
    @GET("random.php")
    Call<SingleRandomMeal> singleRandomMeal();
    @GET("categories.php")
    Call<AllMealCategories> allMealCategories();
    @GET("list.php?c=list")
    Call<AllCategoriesName> allCategoriesName();
    @GET("list.php?a=list")
    Call<AllAreaNames> allAreaNames();
    @GET("list.php?i=list")
    Call<AllIngredient> allIngredient();
    @GET("filter.php")
    Call<FilterByIngredient> filterByIngredient(@Query("i") String strIngredient);
    @GET("filter.php")
    Call<FilterByArea> filterByArea(@Query("a") String strArea);
    @GET("filter.php")
    Call<FilterByCategory> filterByCategory(@Query("c") String strCategory);


}
