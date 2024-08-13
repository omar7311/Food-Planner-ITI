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
    @GET("search.php?s={strMeal}")
    SearchMealByName searchMealByName(@Path("strMeal") String strMeal);

    @GET("lookup.php?i={idMeal}")
    FullMealDetailById fullMealDetailById(@Path("idMeal") String idMeal);
    @GET("random.php")
    SingleRandomMeal singleRandomMeal();
    @GET("categories.php")
    AllMealCategories allMealCategories();
    @GET("list.php?c=list")
    AllCategoriesName allCategoriesName();
    @GET("list.php?a=list")
    AllAreaNames allAreaNames();
    @GET("list.php?i=list")
    AllIngredient allIngredient();
    @GET("filter.php?i={strIngredient}")
    FilterByIngredient filterByIngredient(@Path("strIngredient") String strIngredient);
    @GET("filter.php?a={strArea}")
    FilterByArea filterByArea(@Path("strArea") String strArea);
    @GET("filter.php?c={strCategory}")
    FilterByCategory filterByCategory(@Path("strCategory") String strCategory);

//    @GET("posts")
//    Call<List<ResponsePostsItem>> getPosts();
//    @GET("posts/{id}")
//    Call<ResponsePostsItem> getPost(@Path("id") int id);
//
//    @GET("comments")
//    Call<List<ResponseComments>> getComments(@Query("postId") int postId);
}
