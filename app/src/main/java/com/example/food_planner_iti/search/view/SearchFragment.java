package com.example.food_planner_iti.search.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.food_planner_iti.R;
import com.example.food_planner_iti.local_database.DatabaseManger;
import com.example.food_planner_iti.local_database.Meal;
import com.example.food_planner_iti.local_database.MealPlan;
import com.example.food_planner_iti.meals.presenter.MealPresenter;
import com.example.food_planner_iti.meals.view.ClickListener;
import com.example.food_planner_iti.meals.view.MealAdapter;
import com.example.food_planner_iti.meals.view.MealsFragmentInterface;
import com.example.food_planner_iti.model.MealItem;
import com.example.food_planner_iti.search.presenter.SearchPresenter;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.search.SearchBar;
import com.google.android.material.search.SearchView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class SearchFragment extends Fragment implements SearchFragmentInterface, MealsFragmentInterface, ClickListener {

    SearchPresenter presenter;
    SearchBar searchBar;
    SearchView searchView;
    RecyclerView recyclerView;
    MealAdapter adapter;
    MealPresenter mealPresenter;
    String flag;
     Meal meal;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new SearchPresenter(this);
        mealPresenter=new MealPresenter(this,new DatabaseManger(getContext(),this));
        searchBar = view.findViewById(R.id.search_bar);
        searchView = view.findViewById(R.id.searchView);
        searchView.inflateMenu(R.menu.search_menu);
        recyclerView=view.findViewById(R.id.recycleSearch);
        flag = getString(R.string.search_by_name);
        searchView
                .getEditText()
                .setOnEditorActionListener(
                        (v, actionId, event) -> {
                            if(!searchView.getText().toString().isEmpty()) {
                                if (flag == getString(R.string.search_by_name)) {
                                    presenter.sendMealName(searchView.getText().toString());
                                } else if (flag == getString(R.string.search_by_ingredient)) {
                                    presenter.sendIngredientName(searchView.getText().toString());
                                } else if (flag == getString(R.string.search_by_category)) {
                                    mealPresenter.sendCategoryName(searchView.getText().toString());
                                } else if (flag == getString(R.string.search_by_country)) {
                                    mealPresenter.sendAreaName(searchView.getText().toString());
                                }
                                searchBar.setHint(searchView.getText());
                                searchView.setText("");
                                searchView.hide();
                            }
                            return false;
                        });
        searchView.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                searchView.setHint(item.getTitle());
                searchBar.setHint(item.getTitle());
                flag =item.getTitle().toString();
                  return false;
            }
        });

    }

    @Override
    public void getMealsByName(ArrayList<Meal> meals) {
        ArrayList<MealItem> mealItems=new ArrayList<>();
        for(int i=0;i<meals.size();i++){
            mealItems.add(new MealItem(meals.get(i).getImageUrl(),meals.get(i).getId()
                    ,meals.get(i).getName()));
        }
        adapter=new MealAdapter(mealItems,this.getContext(),this,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getMealsByIngredient(ArrayList<MealItem> mealItems) {
        adapter=new MealAdapter(mealItems,this.getContext(),this,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getMealsByArea(ArrayList<MealItem> mealItem) {
        adapter=new MealAdapter(mealItem,this.getContext(),this,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getMealsByCategory(ArrayList<MealItem> mealItem) {
        adapter=new MealAdapter(mealItem,this.getContext(),this,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void errorMessage(String error) {
        Snackbar.make(this.getView(),error,Snackbar.LENGTH_SHORT).show();
    }



    @Override
    public void onClickInsert(Meal meal) {
        new Thread(()->mealPresenter.insertFavMeal(meal)).start();
        FirebaseDatabase.getInstance().getReference("Meals")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("meal_fav").child(meal.getId()).setValue(meal);
    }

    @Override
    public void onClickDelete(Meal meal) {
        new Thread(()->mealPresenter.deleteFavMeal(meal)).start();
        FirebaseDatabase.getInstance().getReference("Meals")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("meal_fav").child(meal.getId()).setValue(null);
    }

    @Override
    public void onClickInsertMealPlan(CheckBox plan ,Meal meal) {
        this.meal=meal;
        showRadioGroupDialog(plan);
    }

    @Override
    public void onClickDeleteMealPlan(Meal meal) {
        this.meal=meal;
        new Thread( ()->mealPresenter.deletePlanMeal(getMealPlan(meal,selectedOption))).start();
        FirebaseDatabase.getInstance().getReference("Meals")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("meal_plan").child(getMealPlan(meal,selectedOption).getId()).setValue(null);
    }
    String selectedOption;
    public MealPlan getMealPlan(Meal meal,String date){
        MealPlan mealPlan=new MealPlan();
        mealPlan.setId(meal.getId());
        mealPlan.setDate(date);
        mealPlan.setCountry(meal.getCountry());
        mealPlan.setIngredients(meal.getIngredients());
        mealPlan.setIngredientsImage(meal.getIngredientsImage());
        mealPlan.setImageUrl(meal.getImageUrl());
        mealPlan.setName(meal.getName());
        mealPlan.setVideoUrl(meal.getVideoUrl());
        mealPlan.setSteps(meal.getSteps());
        return mealPlan;
    }
    private void showRadioGroupDialog(CheckBox plan) {
        // Inflate the custom layout
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.list_of_week, null);

        // Initialize the radio group from the custom layout
        RadioGroup radioGroup = dialogView.findViewById(R.id.radio_group);

        // Create the AlertDialog using MaterialAlertDialogBuilder
        new MaterialAlertDialogBuilder(this.getContext())
                .setTitle("Choose a day")
                .setCancelable(false)
                .setView(dialogView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Find the selected radio button by its ID
                        int selectedId = radioGroup.getCheckedRadioButtonId();
                        RadioButton selectedRadioButton = dialogView.findViewById(selectedId);

                        if (selectedRadioButton != null) {
                            selectedOption = selectedRadioButton.getText().toString();
                            new Thread( ()->mealPresenter.insertPlanMeal(getMealPlan(meal,selectedOption))).start();
                            FirebaseDatabase.getInstance().getReference("Meals")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .child("meal_plan").child(getMealPlan(meal,selectedOption).getId()).setValue(getMealPlan(meal,selectedOption));
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                         plan.setChecked(false);
                        SharedPreferences.Editor editor=getActivity().getPreferences(Context.MODE_PRIVATE).edit();
                        editor.putBoolean(meal.getId()+"p",plan.isChecked());
                        editor.commit();
                    }
                })
                .show();
    }

}