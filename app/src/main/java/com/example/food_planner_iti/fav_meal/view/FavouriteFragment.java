package com.example.food_planner_iti.fav_meal.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.food_planner_iti.R;
import com.example.food_planner_iti.fav_meal.presenter.FavMealPresenter;
import com.example.food_planner_iti.local_database.DatabaseManger;
import com.example.food_planner_iti.local_database.Meal;
import com.example.food_planner_iti.meals.view.ClickListener;
import com.example.food_planner_iti.meals.view.MealAdapter;
import com.example.food_planner_iti.model.MealItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class FavouriteFragment extends Fragment implements FavFragmentInterface, ClickListener {
  MealAdapter adapter;
  FavMealPresenter favMealPresenter;
  RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        favMealPresenter=new FavMealPresenter(new DatabaseManger(getContext(),this),this);
        recyclerView=view.findViewById(R.id.Fav_recycle);
    }

    @Override
    public void getAllFavMeal(ArrayList<Meal> meals) {
        ArrayList<MealItem> mealItems=new ArrayList<>();
        for(int i=0;i< meals.size();i++){
            mealItems.add(new MealItem(meals.get(i).getImageUrl(),meals.get(i).getId(),meals.get(i).getName()));
        }
        adapter=new MealAdapter(mealItems,getContext(),this,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClickInsert(Meal meal) {}

    @Override
    public void onClickDelete(Meal meal) {
     new Thread( ()-> favMealPresenter.deleteFavMeal(meal)).start();
     adapter.notifyDataSetChanged();
        FirebaseDatabase.getInstance().getReference("Meals")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("meal_fav").child(meal.getId()).setValue(null);
    }
}