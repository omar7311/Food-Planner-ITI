package com.example.food_planner_iti.plan_meal.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
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
import com.example.food_planner_iti.network.NetworkManger;
import com.example.food_planner_iti.plan_meal.presenter.PlanMealPresenter;
import com.example.food_planner_iti.repository.MealRepositoryImple;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

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
        presenter = new PlanMealPresenter(new MealRepositoryImple(new NetworkManger(),new DatabaseManger(getContext())), this);
        recyclerView = view.findViewById(R.id.recycle);
        chipGroup = view.findViewById(R.id.chipGroup);

        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);

            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (chip.isChecked()) {
                        if (chip.getText().toString().equals("SAT")) {
                            presenter.sendDateMeal("Saturday").observe(getActivity(), new Observer<List<MealPlan>>() {
                                @Override
                                public void onChanged(List<MealPlan> mealPlans) {
                                    ArrayList<MealItem> mealItems = new ArrayList<>();
                                    for (int i = 0; i < mealPlans.size(); i++) {
                                        mealItems.add(new MealItem(mealPlans.get(i).getImageUrl(), mealPlans.get(i).getId(), mealPlans.get(i).getName()));
                                    }
                                    adapter = new MealAdapter(mealItems, getContext(), PlanFragment.this, PlanFragment.this);
                                    recyclerView.setAdapter(adapter);
                                }
                            });
                            selectedOption = "Saturday";
                        } else if (chip.getText().toString().equals("SUN")) {
                            presenter.sendDateMeal("Sunday").observe(getActivity(), new Observer<List<MealPlan>>() {
                                @Override
                                public void onChanged(List<MealPlan> mealPlans) {
                                    ArrayList<MealItem> mealItems = new ArrayList<>();
                                    for (int i = 0; i < mealPlans.size(); i++) {
                                        mealItems.add(new MealItem(mealPlans.get(i).getImageUrl(), mealPlans.get(i).getId(), mealPlans.get(i).getName()));
                                    }
                                    adapter = new MealAdapter(mealItems, getContext(), PlanFragment.this, PlanFragment.this);
                                    recyclerView.setAdapter(adapter);
                                }
                            });
                            selectedOption = "Sunday";
                        } else if (chip.getText().toString().equals("MON")) {
                            presenter.sendDateMeal("Monday").observe(getActivity(), new Observer<List<MealPlan>>() {
                                @Override
                                public void onChanged(List<MealPlan> mealPlans) {
                                    ArrayList<MealItem> mealItems = new ArrayList<>();
                                    for (int i = 0; i < mealPlans.size(); i++) {
                                        mealItems.add(new MealItem(mealPlans.get(i).getImageUrl(), mealPlans.get(i).getId(), mealPlans.get(i).getName()));
                                    }
                                    adapter = new MealAdapter(mealItems, getContext(), PlanFragment.this, PlanFragment.this);
                                    recyclerView.setAdapter(adapter);
                                }
                            });
                            selectedOption = "Monday";
                        } else if (chip.getText().toString().equals("TUE")) {
                            presenter.sendDateMeal("Tuesday").observe(getActivity(), new Observer<List<MealPlan>>() {
                                @Override
                                public void onChanged(List<MealPlan> mealPlans) {
                                    ArrayList<MealItem> mealItems = new ArrayList<>();
                                    for (int i = 0; i < mealPlans.size(); i++) {
                                        mealItems.add(new MealItem(mealPlans.get(i).getImageUrl(), mealPlans.get(i).getId(), mealPlans.get(i).getName()));
                                    }
                                    adapter = new MealAdapter(mealItems, getContext(), PlanFragment.this, PlanFragment.this);
                                    recyclerView.setAdapter(adapter);
                                }
                            });
                            selectedOption = "Tuesday";
                        } else if (chip.getText().toString().equals("WED")) {
                            presenter.sendDateMeal("Wednesday").observe(getActivity(), new Observer<List<MealPlan>>() {
                                @Override
                                public void onChanged(List<MealPlan> mealPlans) {
                                    ArrayList<MealItem> mealItems = new ArrayList<>();
                                    for (int i = 0; i < mealPlans.size(); i++) {
                                        mealItems.add(new MealItem(mealPlans.get(i).getImageUrl(), mealPlans.get(i).getId(), mealPlans.get(i).getName()));
                                    }
                                    adapter = new MealAdapter(mealItems, getContext(), PlanFragment.this, PlanFragment.this);
                                    recyclerView.setAdapter(adapter);
                                }
                            });
                            selectedOption = "Wednesday";
                        } else if (chip.getText().toString().equals("THU")) {
                            presenter.sendDateMeal("Thursday").observe(getActivity(), new Observer<List<MealPlan>>() {
                                @Override
                                public void onChanged(List<MealPlan> mealPlans) {
                                    ArrayList<MealItem> mealItems = new ArrayList<>();
                                    for (int i = 0; i < mealPlans.size(); i++) {
                                        mealItems.add(new MealItem(mealPlans.get(i).getImageUrl(), mealPlans.get(i).getId(), mealPlans.get(i).getName()));
                                    }
                                    adapter = new MealAdapter(mealItems, getContext(), PlanFragment.this, PlanFragment.this);
                                    recyclerView.setAdapter(adapter);
                                }
                            });
                            selectedOption = "Thursday";
                        } else if (chip.getText().toString().equals("FRI")) {
                            presenter.sendDateMeal("Friday").observe(getActivity(), new Observer<List<MealPlan>>() {
                                @Override
                                public void onChanged(List<MealPlan> mealPlans) {
                                    ArrayList<MealItem> mealItems = new ArrayList<>();
                                    for (int i = 0; i < mealPlans.size(); i++) {
                                        mealItems.add(new MealItem(mealPlans.get(i).getImageUrl(), mealPlans.get(i).getId(), mealPlans.get(i).getName()));
                                    }
                                    adapter = new MealAdapter(mealItems, getContext(), PlanFragment.this, PlanFragment.this);
                                    recyclerView.setAdapter(adapter);
                                }
                            });
                            selectedOption = "Friday";
                        } else if (chip.getText().toString().equals("ALL")) {
                            presenter.requestMealsPlan().observe(getActivity(), new Observer<List<MealPlan>>() {
                                @Override
                                public void onChanged(List<MealPlan> mealPlans) {
                                    ArrayList<MealItem> mealItems = new ArrayList<>();
                                    for (int i = 0; i < mealPlans.size(); i++) {
                                        mealItems.add(new MealItem(mealPlans.get(i).getImageUrl(), mealPlans.get(i).getId(), mealPlans.get(i).getName()));
                                    }
                                    adapter = new MealAdapter(mealItems, getContext(), PlanFragment.this, PlanFragment.this);
                                    recyclerView.setAdapter(adapter);
                                }
                            });

                        } else  presenter.requestMealsPlan().observe(getActivity(), new Observer<List<MealPlan>>() {
                            @Override
                            public void onChanged(List<MealPlan> mealPlans) {
                                ArrayList<MealItem> mealItems = new ArrayList<>();
                                for (int i = 0; i < mealPlans.size(); i++) {
                                    mealItems.add(new MealItem(mealPlans.get(i).getImageUrl(), mealPlans.get(i).getId(), mealPlans.get(i).getName()));
                                }
                                adapter = new MealAdapter(mealItems, getContext(), PlanFragment.this, PlanFragment.this);
                                recyclerView.setAdapter(adapter);
                            }
                        });

                    }
                }
            });
        }
    }



    @Override
    public void onClickInsert(Meal meal) {
         presenter.insertFavMeal(meal);
    }

    @Override
    public void onClickDelete(Meal meal) {
         presenter.deleteFavMeal(meal);
    }

    @Override
    public void onClickInsertMealPlan(CheckBox plan, Meal meal) {
    }

    String selectedOption;

    @Override
    public void onClickDeleteMealPlan(Meal meal) {
        this.meal=meal;
         presenter.deletePlanMeal(meal, selectedOption);

    }
}