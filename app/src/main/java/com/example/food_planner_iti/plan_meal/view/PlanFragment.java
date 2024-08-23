package com.example.food_planner_iti.plan_meal.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.food_planner_iti.R;
import com.example.food_planner_iti.local_database.DatabaseManger;
import com.example.food_planner_iti.local_database.Meal;
import com.example.food_planner_iti.local_database.MealPlan;
import com.example.food_planner_iti.meals.view.ClickListener;
import com.example.food_planner_iti.meals.view.MealAdapter;
import com.example.food_planner_iti.model.MealItem;
import com.example.food_planner_iti.plan_meal.presenter.PlanMealPresenter;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PlanFragment extends Fragment implements PlanFragmentInterface, ClickListener {
    PlanMealPresenter presenter;
    RecyclerView recyclerView;
    ChipGroup chipGroup;
    MealAdapter adapter;
    Meal meal;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new PlanMealPresenter(new DatabaseManger(getContext(), this), this::getAllMealsPlan);
        recyclerView = view.findViewById(R.id.recycle);
        chipGroup = view.findViewById(R.id.chipGroup);

        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);

            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (chip.isChecked()) {
                        if (chip.getText().toString().equals("SAT")) {
                            presenter.sendDateMeal("Saturday");
                            selectedOption = "Saturday";
                        } else if (chip.getText().toString().equals("SUN")) {
                            presenter.sendDateMeal("Sunday");
                            selectedOption = "Sunday";
                        } else if (chip.getText().toString().equals("MON")) {
                            presenter.sendDateMeal("Monday");
                            selectedOption = "Monday";
                        } else if (chip.getText().toString().equals("TUE")) {
                            presenter.sendDateMeal("Tuesday");
                            selectedOption = "Tuesday";
                        } else if (chip.getText().toString().equals("WED")) {
                            presenter.sendDateMeal("Wednesday");
                            selectedOption = "Wednesday";
                        } else if (chip.getText().toString().equals("THU")) {
                            presenter.sendDateMeal("Thursday");
                            selectedOption = "Thursday";
                        } else if (chip.getText().toString().equals("FRI")) {
                            presenter.sendDateMeal("Friday");
                            selectedOption = "Friday";
                        } else if (chip.getText().toString().equals("ALL")) {
                            presenter.requestMealsPlan();

                        } else  presenter.requestMealsPlan();

                    }
                }
            });
        }
    }

    public MealPlan getMealPlan(Meal meal, String date) {
        MealPlan mealPlan = new MealPlan();
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

    @Override
    public void getAllMealsPlan(ArrayList<MealPlan> mealPlans) {
        ArrayList<MealItem> mealItems = new ArrayList<>();
        for (int i = 0; i < mealPlans.size(); i++) {
            mealItems.add(new MealItem(mealPlans.get(i).getImageUrl(), mealPlans.get(i).getId(), mealPlans.get(i).getName()));
        }
        adapter = new MealAdapter(mealItems, getContext(), this, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClickInsert(Meal meal) {
        new Thread(() -> presenter.insertFavMeal(meal)).start();
        FirebaseDatabase.getInstance().getReference("Meals")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("meal_fav").child(meal.getId()).setValue(meal);
    }

    @Override
    public void onClickDelete(Meal meal) {
        new Thread(() -> presenter.deleteFavMeal(meal)).start();
        FirebaseDatabase.getInstance().getReference("Meals")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("meal_fav").child(meal.getId()).setValue(null);
    }

    @Override
    public void onClickInsertMealPlan(CheckBox plan, Meal meal) {
    }

    String selectedOption;

    @Override
    public void onClickDeleteMealPlan(Meal meal) {
        this.meal=meal;
        new Thread(() -> presenter.deletePlanMeal(getMealPlan(meal, selectedOption))).start();
        FirebaseDatabase.getInstance().getReference("Meals")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("meal_plan").child(getMealPlan(meal, selectedOption).getId()).setValue(null);
    }
}