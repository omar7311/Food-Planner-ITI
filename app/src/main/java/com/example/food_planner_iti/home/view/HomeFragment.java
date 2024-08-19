package com.example.food_planner_iti.home.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.food_planner_iti.R;
import com.example.food_planner_iti.home.presenter.HomePresenter;
import com.example.food_planner_iti.home.presenter.HomePresenterInterface;
import com.example.food_planner_iti.local_database.Meal;
import com.example.food_planner_iti.model.AreasName;
import com.example.food_planner_iti.model.CategoriesItem;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements HomeFragmentInterface {
    TextView nameRandomMeal;
    ImageView imageView;
    HomePresenter homePresenter;
    CategoryAdapter adapter;
    RecyclerView categoryRecycle;
    CountryAdapter adapter2;
    RecyclerView countryRecycle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homePresenter=new HomePresenter(this);
        nameRandomMeal=view.findViewById(R.id.nameRandomMeal);
        imageView=view.findViewById(R.id.imageRandMeal);
        categoryRecycle=view.findViewById(R.id.recycleCategories);
        countryRecycle=view.findViewById(R.id.recycleCountries);
    }

    @Override
    public void getSingleRandomMeal(Meal meal) {
        nameRandomMeal.setText(meal.getName());
        if(meal.getImageUrl()!=null)
         Glide.with(this).load(meal.getImageUrl()).into(imageView);
    }

    @Override
    public void getAllCategoryItems(ArrayList<CategoriesItem> categoriesItemArrayList) {
        adapter=new CategoryAdapter(this.getContext(),categoriesItemArrayList);
        categoryRecycle.setAdapter(adapter);
       new LinearSnapHelper().attachToRecyclerView(categoryRecycle);
    }

    @Override
    public void getCountries(ArrayList<AreasName> areasNames) {
        adapter2=new CountryAdapter(areasNames,this.getContext());
        countryRecycle.setAdapter(adapter2);
        new LinearSnapHelper().attachToRecyclerView(countryRecycle);
    }

    @Override
    public void errorMessage(String error) {
        Snackbar.make(this.getView(),error,Snackbar.LENGTH_LONG).show();
    }


}