package com.example.food_planner_iti.fav_meal.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.food_planner_iti.R;
import com.example.food_planner_iti.fav_meal.presenter.FavMealPresenter;
import com.example.food_planner_iti.local_database.DatabaseManger;
import com.example.food_planner_iti.local_database.Meal;
import com.example.food_planner_iti.local_database.MealPlan;
import com.example.food_planner_iti.meals.view.ClickListener;
import com.example.food_planner_iti.meals.view.MealAdapter;
import com.example.food_planner_iti.model.MealItem;
import com.example.food_planner_iti.network.NetworkManger;
import com.example.food_planner_iti.repository.MealRepositoryImple;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class FavouriteFragment extends Fragment implements FavFragmentInterface, ClickListener {
  MealAdapter adapter;
  FavMealPresenter favMealPresenter;
  RecyclerView recyclerView;
  Meal meal;

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
        favMealPresenter=new FavMealPresenter(new MealRepositoryImple(new NetworkManger(),new DatabaseManger(getActivity())),this);
        recyclerView=view.findViewById(R.id.Fav_recycle);
        favMealPresenter.getAllFavMeal().observe(getActivity(), new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                ArrayList<MealItem> mealItems=new ArrayList<>();
                for(int i=0;i< meals.size();i++){
                    mealItems.add(new MealItem(meals.get(i).getImageUrl(),meals.get(i).getId(),meals.get(i).getName()));
                }
                adapter=new MealAdapter(mealItems,getContext(),FavouriteFragment.this,FavouriteFragment.this);
                recyclerView.setAdapter(adapter);
            }
        });
    }


    @Override
    public void onClickInsert(Meal meal) {}

    @Override
    public void onClickDelete(Meal meal) {
      favMealPresenter.deleteFavMeal(meal);
     adapter.notifyDataSetChanged();
    }

    @Override
    public void onClickInsertMealPlan(CheckBox plan, Meal meal) {
        this.meal=meal;
        showRadioGroupDialog(plan);
    }

    @Override
    public void onClickDeleteMealPlan(Meal meal) {
        this.meal=meal;
        favMealPresenter.deletePlanMeal(meal,selectedOption);
    }
    String selectedOption;

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
                            favMealPresenter.insertPlanMeal(meal,selectedOption);

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