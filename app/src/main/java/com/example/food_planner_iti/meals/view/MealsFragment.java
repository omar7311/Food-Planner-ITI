package com.example.food_planner_iti.meals.view;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.food_planner_iti.R;
import com.example.food_planner_iti.local_database.DatabaseManger;
import com.example.food_planner_iti.local_database.Meal;
import com.example.food_planner_iti.local_database.MealPlan;
import com.example.food_planner_iti.meals.presenter.MealPresenter;
import com.example.food_planner_iti.model.MealItem;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class MealsFragment extends Fragment implements MealsFragmentInterface , ClickListener {
    MealPresenter presenter;
    RecyclerView recyclerView;
    MealAdapter adapter;
    Meal meal;
    FrameLayout frameLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new MealPresenter(this,new DatabaseManger(getContext(),this));
        frameLayout=view.findViewById(R.id.layout);
        recyclerView=view.findViewById(R.id.recycle);
        String str = MealsFragmentArgs.fromBundle(getArguments()).getStrCategoryOrArea();
        int flag = MealsFragmentArgs.fromBundle(getArguments()).getFlag();
        if (flag == 0) {
            presenter.sendCategoryName(str);
        }
        if (flag == 1) {
            presenter.sendAreaName(str);
        }
        Log.d("tag", str);
    }

    @Override
    public void getMealsByArea(ArrayList<MealItem> mealItem) {
     adapter=new MealAdapter(mealItem,this.getContext(),this,this);
     recyclerView.setAdapter(adapter);
    }

    @Override
    public void getMealsByCategory(ArrayList<MealItem> mealItem) {
        adapter=new MealAdapter(mealItem,this.getContext(),this,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void errorMessage(String error) {
        showNoConnection();
    }
    private void showNoConnection(){
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
        View noInternetView = inflater.inflate(R.layout.no_connection, null);
        frameLayout.removeAllViews();
        frameLayout.addView(noInternetView);
    }
    @Override
    public void onClickInsert(Meal meal) {
       new Thread( ()-> presenter.insertFavMeal(meal) ).start();
        FirebaseDatabase.getInstance().getReference("Meals")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("meal_fav").child(meal.getId()).setValue(meal);
    }

    @Override
    public void onClickDelete(Meal meal) {
        new Thread(()->presenter.deleteFavMeal(meal)).start();
        FirebaseDatabase.getInstance().getReference("Meals")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("meal_fav").child(meal.getId()).setValue(null);
    }

    @Override
    public void onClickInsertMealPlan(CheckBox plan, Meal meal) {
     this.meal=meal;
     showRadioGroupDialog(plan);
    }

    @Override
    public void onClickDeleteMealPlan(Meal meal) {
    this.meal=meal;
        new Thread( ()->presenter.deletePlanMeal(getMealPlan(meal,selectedOption))).start();
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
                            new Thread( ()->presenter.insertPlanMeal(getMealPlan(meal,selectedOption))).start();
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