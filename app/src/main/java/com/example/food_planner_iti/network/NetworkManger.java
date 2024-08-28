package com.example.food_planner_iti.network;

import com.example.food_planner_iti.home.presenter.HomeNetworkCallBack;
import com.example.food_planner_iti.local_database.Meal;
import com.example.food_planner_iti.meal_details.presenter.MealDetailsNetworkCallBack;
import com.example.food_planner_iti.meals.presenter.MealNetworkCallBack;
import com.example.food_planner_iti.model.AllAreaNames;
import com.example.food_planner_iti.model.AllMealCategories;
import com.example.food_planner_iti.model.AreasName;
import com.example.food_planner_iti.model.CategoriesItem;
import com.example.food_planner_iti.model.FilterByArea;
import com.example.food_planner_iti.model.FilterByCategory;
import com.example.food_planner_iti.model.FilterByIngredient;
import com.example.food_planner_iti.model.FullMealDetailById;
import com.example.food_planner_iti.model.MealDetails;
import com.example.food_planner_iti.model.MealItem;
import com.example.food_planner_iti.model.SearchMealByName;
import com.example.food_planner_iti.model.SingleRandomMeal;
import com.example.food_planner_iti.search.presenter.SearchNetworkCallBack;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkManger implements RemoteDataSource {

    public void getRandomMeal(HomeNetworkCallBack homeNetworkCallBack) {
        RetrofitClient.getService().singleRandomMeal().enqueue(new Callback<SingleRandomMeal>() {
            @Override
            public void onResponse(Call<SingleRandomMeal> call, Response<SingleRandomMeal> response) {
                if (response.isSuccessful()) {
                    homeNetworkCallBack.getRandomMeal(getMeal(response.body().getMeals().get(0)));
                }
            }

            @Override
            public void onFailure(Call<SingleRandomMeal> call, Throwable t) {
                homeNetworkCallBack.noConnection();
            }
        });
    }

    public Meal getMeal(MealDetails mealDetails) {
        Meal meal = new Meal();
        meal.setId(mealDetails.getIdMeal());
        meal.setName(mealDetails.getStrMeal());
        meal.setCountry(mealDetails.getStrArea());
        meal.setSteps(mealDetails.getStrInstructions());
        meal.setImageUrl(mealDetails.getStrMealThumb());
        if (!mealDetails.getStrYoutube().isEmpty()) meal.setVideoUrl(mealDetails.getStrYoutube());
        ArrayList<String> ingredients = new ArrayList<>();
        ArrayList<String> ingredientsImage = new ArrayList<>();
        if (mealDetails.getStrIngredient1() != null && !mealDetails.getStrIngredient1().equals("")) {
            ingredients.add(mealDetails.getStrMeasure1()
                    + " " + mealDetails.getStrIngredient1());
            ingredientsImage.add(mealDetails.getStrIngredient1());
        }
        if (mealDetails.getStrIngredient2() != null && !mealDetails.getStrIngredient2().equals("")) {
            ingredients.add(mealDetails.getStrMeasure2()
                    + " " + mealDetails.getStrIngredient2());
            ingredientsImage.add(mealDetails.getStrIngredient2());
        }
        if (mealDetails.getStrIngredient3() != null && !mealDetails.getStrIngredient3().equals("")) {
            ingredients.add(mealDetails.getStrMeasure3()
                    + " " + mealDetails.getStrIngredient3());
            ingredientsImage.add(mealDetails.getStrIngredient3());

        }
        if (mealDetails.getStrIngredient4() != null && !mealDetails.getStrIngredient4().equals("")) {
            ingredients.add(mealDetails.getStrMeasure4()
                    + " " + mealDetails.getStrIngredient4());
            ingredientsImage.add(mealDetails.getStrIngredient4());

        }
        if (mealDetails.getStrIngredient5() != null && !mealDetails.getStrIngredient5().equals("")) {
            ingredients.add(mealDetails.getStrMeasure5()
                    + " " + mealDetails.getStrIngredient5());
            ingredientsImage.add(mealDetails.getStrIngredient5());

        }
        if (mealDetails.getStrIngredient6() != null && !mealDetails.getStrIngredient6().equals("")) {
            ingredients.add(mealDetails.getStrMeasure6()
                    + " " + mealDetails.getStrIngredient6());
            ingredientsImage.add(mealDetails.getStrIngredient6());

        }
        if (mealDetails.getStrIngredient7() != null && !mealDetails.getStrIngredient7().equals("")) {
            ingredients.add(mealDetails.getStrMeasure7()
                    + " " + mealDetails.getStrIngredient7());
            ingredientsImage.add(mealDetails.getStrIngredient7());

        }
        if (mealDetails.getStrIngredient8() != null && !mealDetails.getStrIngredient8().equals("")) {
            ingredients.add(mealDetails.getStrMeasure8()
                    + " " + mealDetails.getStrIngredient8());
            ingredientsImage.add(mealDetails.getStrIngredient8());

        }
        if (mealDetails.getStrIngredient9() != null && !mealDetails.getStrIngredient9().equals("")) {
            ingredients.add(mealDetails.getStrMeasure9()
                    + " " + mealDetails.getStrIngredient9());
            ingredientsImage.add(mealDetails.getStrIngredient9());

        }
        if (mealDetails.getStrIngredient10() != null && !mealDetails.getStrIngredient10().equals("")) {
            ingredients.add(mealDetails.getStrMeasure10()
                    + " " + mealDetails.getStrIngredient10());
            ingredientsImage.add(mealDetails.getStrIngredient10());

        }
        if (mealDetails.getStrIngredient11() != null && !mealDetails.getStrIngredient11().equals("")) {
            ingredients.add(mealDetails.getStrMeasure11()
                    + " " + mealDetails.getStrIngredient11());
            ingredientsImage.add(mealDetails.getStrIngredient11());

        }
        if (mealDetails.getStrIngredient12() != null && !mealDetails.getStrIngredient12().equals("")) {
            ingredients.add(mealDetails.getStrMeasure12()
                    + " " + mealDetails.getStrIngredient12());
            ingredientsImage.add(mealDetails.getStrIngredient12());

        }
        if (mealDetails.getStrIngredient13() != null && !mealDetails.getStrIngredient13().equals("")) {
            ingredients.add(mealDetails.getStrMeasure13()
                    + " " + mealDetails.getStrIngredient13());
            ingredientsImage.add(mealDetails.getStrIngredient13());

        }
        if (mealDetails.getStrIngredient14() != null && !mealDetails.getStrIngredient14().equals("")) {
            ingredients.add(mealDetails.getStrMeasure14()
                    + " " + mealDetails.getStrIngredient14());
            ingredientsImage.add(mealDetails.getStrIngredient14());

        }
        if (mealDetails.getStrIngredient15() != null && !mealDetails.getStrIngredient15().equals("")) {
            ingredients.add(mealDetails.getStrMeasure15()
                    + " " + mealDetails.getStrIngredient15());
            ingredientsImage.add(mealDetails.getStrIngredient15());

        }
        if (mealDetails.getStrIngredient16() != null && !mealDetails.getStrIngredient16().equals("")) {
            ingredients.add(mealDetails.getStrMeasure16()
                    + " " + mealDetails.getStrIngredient16());
            ingredientsImage.add((String) mealDetails.getStrIngredient16());

        }
        if (mealDetails.getStrIngredient17() != null && !mealDetails.getStrIngredient17().equals("")) {
            ingredients.add(mealDetails.getStrMeasure17()
                    + " " + mealDetails.getStrIngredient17());
            ingredientsImage.add((String) mealDetails.getStrIngredient17());

        }
        if (mealDetails.getStrIngredient18() != null && !mealDetails.getStrIngredient18().equals("")) {
            ingredients.add(mealDetails.getStrMeasure18()
                    + " " + mealDetails.getStrIngredient18());
            ingredientsImage.add((String) mealDetails.getStrIngredient18());

        }
        if (mealDetails.getStrIngredient19() != null && !mealDetails.getStrIngredient19().equals("")) {
            ingredients.add(mealDetails.getStrMeasure19()
                    + " " + mealDetails.getStrIngredient19());
            ingredientsImage.add((String) mealDetails.getStrIngredient19());

        }
        if (mealDetails.getStrIngredient20() != null && mealDetails.getStrIngredient20().equals("")) {
            ingredients.add(mealDetails.getStrMeasure20()
                    + " " + mealDetails.getStrIngredient20());
            ingredientsImage.add((String) mealDetails.getStrIngredient20());

        }
        meal.setIngredientsImage(ingredientsImage);
        meal.setIngredients(ingredients);
        return meal;
    }

    public void getCategories(HomeNetworkCallBack homeNetworkCallBack) {
        RetrofitClient.getService().allMealCategories().enqueue(new Callback<AllMealCategories>() {
            @Override
            public void onResponse(Call<AllMealCategories> call, Response<AllMealCategories> response) {
                if (response.isSuccessful()) {
                    homeNetworkCallBack.getCategories((ArrayList<CategoriesItem>) response.body().getCategories());
                }
            }

            @Override
            public void onFailure(Call<AllMealCategories> call, Throwable t) {
                homeNetworkCallBack.noConnection();
            }
        });
    }

    public void getCountries(HomeNetworkCallBack homeNetworkCallBack) {
        RetrofitClient.getService().allAreaNames().enqueue(new Callback<AllAreaNames>() {
            @Override
            public void onResponse(Call<AllAreaNames> call, Response<AllAreaNames> response) {
                if (response.isSuccessful())
                    homeNetworkCallBack.getCountries((ArrayList<AreasName>) response.body().getAreasNames());

            }

            @Override
            public void onFailure(Call<AllAreaNames> call, Throwable t) {
                homeNetworkCallBack.noConnection();
            }
        });
    }


    public void getMealsByCategory(MealNetworkCallBack mealNetworkCallBack, String strCategory) {
        RetrofitClient.getService().filterByCategory(strCategory).enqueue(new Callback<FilterByCategory>() {
            @Override
            public void onResponse(Call<FilterByCategory> call, Response<FilterByCategory> response) {
                if (response.isSuccessful() && response.body().getMealItems() != null)
                    mealNetworkCallBack.getMealsByCategories((ArrayList<MealItem>) response.body().getMealItems());

            }

            @Override
            public void onFailure(Call<FilterByCategory> call, Throwable t) {
                mealNetworkCallBack.noConnection();
            }
        });
    }

    public void getMealsByArea(MealNetworkCallBack mealNetworkCallBack ,String strArea) {
        RetrofitClient.getService().filterByArea(strArea).enqueue(new Callback<FilterByArea>() {
            @Override
            public void onResponse(Call<FilterByArea> call, Response<FilterByArea> response) {
                if (response.isSuccessful() && response.body().getMealItems() != null)
                    mealNetworkCallBack.getMealsByArea((ArrayList<MealItem>) response.body().getMealItems());
            }

            @Override
            public void onFailure(Call<FilterByArea> call, Throwable t) {
                mealNetworkCallBack.noConnection();
            }
        });
    }

    public void getMealDetailsById(MealDetailsNetworkCallBack mealDetailsNetworkCallBack, String id) {
        RetrofitClient.getService().fullMealDetailById(id).enqueue(new Callback<FullMealDetailById>() {
            @Override
            public void onResponse(Call<FullMealDetailById> call, Response<FullMealDetailById> response) {
                if (response.isSuccessful()) {
                    mealDetailsNetworkCallBack.getMealDetailsById(getMeal(response.body().getMeals().get(0)));
                }
            }

            @Override
            public void onFailure(Call<FullMealDetailById> call, Throwable t) {
                mealDetailsNetworkCallBack.noConnection();
            }
        });
    }

    public void searchByMealName(SearchNetworkCallBack searchNetworkCallBack, String name) {
        RetrofitClient.getService().searchMealByName(name).enqueue(new Callback<SearchMealByName>() {
            @Override
            public void onResponse(Call<SearchMealByName> call, Response<SearchMealByName> response) {
                if (response.isSuccessful() && response.body().getMeals() != null) {
                    ArrayList<Meal> meals = new ArrayList<>();
                    for (int i = 0; i < response.body().getMeals().size(); i++) {
                        meals.add(getMeal(response.body().getMeals().get(i)));
                    }
                   searchNetworkCallBack.searchByNameMeal(meals);
                }
            }

            @Override
            public void onFailure(Call<SearchMealByName> call, Throwable t) {
                searchNetworkCallBack.noConnection();
            }
        });
    }

    public void searchByIngredient(SearchNetworkCallBack searchNetworkCallBack, String name) {
        RetrofitClient.getService().filterByIngredient(name).enqueue(new Callback<FilterByIngredient>() {
            @Override
            public void onResponse(Call<FilterByIngredient> call, Response<FilterByIngredient> response) {
                if (response.isSuccessful() && response.body().getMealItems() != null) {
                   searchNetworkCallBack.searchByIngredient((ArrayList<MealItem>) response.body().getMealItems());
                }
            }

            @Override
            public void onFailure(Call<FilterByIngredient> call, Throwable t) {
               searchNetworkCallBack.noConnection();
            }
        });
    }


}
