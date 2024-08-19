package com.example.food_planner_iti.meals.view;

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

import com.example.food_planner_iti.R;
import com.example.food_planner_iti.meals.presenter.MealPresenter;
import com.example.food_planner_iti.model.MealItem;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class MealsFragment extends Fragment implements MealsFragmentInterface {
    MealPresenter presenter;
    RecyclerView recyclerView;
    MealAdapter adapter;

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
        presenter = new MealPresenter(this);
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
     adapter=new MealAdapter(mealItem,this.getContext());
     recyclerView.setAdapter(adapter);
    }

    @Override
    public void getMealsByCategory(ArrayList<MealItem> mealItem) {
        adapter=new MealAdapter(mealItem,this.getContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void errorMessage(String error) {
        Snackbar.make(this.getView(),error,Snackbar.LENGTH_SHORT).show();
    }
}