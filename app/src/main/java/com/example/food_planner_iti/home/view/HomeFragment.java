package com.example.food_planner_iti.home.view;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.food_planner_iti.R;
import com.example.food_planner_iti.home.presenter.HomePresenter;
import com.example.food_planner_iti.home.presenter.HomePresenterInterface;
import com.example.food_planner_iti.local_database.DatabaseManger;
import com.example.food_planner_iti.local_database.Meal;
import com.example.food_planner_iti.local_database.MealPlan;
import com.example.food_planner_iti.model.AreasName;
import com.example.food_planner_iti.model.CategoriesItem;
import com.example.food_planner_iti.view.MainActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements HomeFragmentInterface {
    TextView nameRandomMeal;
    ImageView imageView;
    HomePresenter homePresenter;
    CategoryAdapter adapter;
    RecyclerView categoryRecycle;
    CountryAdapter adapter2;
    RecyclerView countryRecycle;
    CardView randomMeal;
    CheckBox fav,plan;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String key_fav;
    String key_plan;
    Meal meal;
    ScrollView scrollLayout;
    HomeFragmentDirections.ActionHomeToMealDetailsFragment actionHomeToMealDetailsFragment;
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
        homePresenter=new HomePresenter(this,new DatabaseManger(getContext(),this));
        scrollLayout=view.findViewById(R.id.scrollView);
        nameRandomMeal=view.findViewById(R.id.nameRandomMeal);
        imageView=view.findViewById(R.id.imageRandMeal);
        categoryRecycle=view.findViewById(R.id.recycleCategories);
        countryRecycle=view.findViewById(R.id.recycleCountries);
        randomMeal=view.findViewById(R.id.randomMeal);
        fav=view.findViewById(R.id.addToFav);
        plan=view.findViewById(R.id.addToPlan);
        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!FirebaseAuth.getInstance().getCurrentUser().isAnonymous()) {
                    editor.putBoolean(key_fav, fav.isChecked());
                    editor.commit();
                    if (fav.isChecked()) {
                        new Thread(() -> homePresenter.insertFavMeal(meal)).start();
                        FirebaseDatabase.getInstance().getReference("Meals")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .child("meal_fav").child(meal.getId()).setValue(meal);
                    } else {
                        new Thread(() -> homePresenter.deleteFavMeal(meal)).start();
                        FirebaseDatabase.getInstance().getReference("Meals")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .child("meal_fav").child(meal.getId()).setValue(null);
                    }
                }else {
                    fav.setChecked(false);
                    Toast.makeText(HomeFragment.this.getContext(), "you are a guest", Toast.LENGTH_SHORT).show();
                }
            }
        });
        plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!FirebaseAuth.getInstance().getCurrentUser().isAnonymous()) {
                    editor.putBoolean(key_plan, plan.isChecked());
                    editor.commit();
                    if (plan.isChecked()) {
                        showRadioGroupDialog();
                    } else {
                        new Thread(() -> homePresenter.deletePlanMeal(getMealPlan(meal, selectedOption))).start();
                        FirebaseDatabase.getInstance().getReference("Meals")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .child("meal_plan").child(getMealPlan(meal, selectedOption).getId()).setValue(null);
                    }
                }else {
                    plan.setChecked(false);
                    Toast.makeText(HomeFragment.this.getContext(), "you are a guest", Toast.LENGTH_SHORT).show();
                }
            }
        });
        randomMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(actionHomeToMealDetailsFragment);
            }
        });
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
    private void showRadioGroupDialog() {
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
                            new Thread( ()->homePresenter.insertPlanMeal(getMealPlan(meal,selectedOption))).start();
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
                        editor.putBoolean(key_plan,plan.isChecked());
                        editor.commit();
                    }
                })
                .show();
    }
    @Override
    public void getSingleRandomMeal(Meal meal) {
        this.meal=meal;
        nameRandomMeal.setText(meal.getName());
        if(meal.getImageUrl()!=null)
         Glide.with(this).load(meal.getImageUrl()).into(imageView);
         actionHomeToMealDetailsFragment =HomeFragmentDirections.actionHomeToMealDetailsFragment(meal.getId());
         key_fav= meal.getId()+"f";
      fav.setChecked(sharedPreferences.getBoolean(key_fav,false));
        key_plan= meal.getId()+"p";
        plan.setChecked(sharedPreferences.getBoolean(key_plan,false));
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
      showNoConnection();
    }
private void showNoConnection(){
    LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
    View noInternetView = inflater.inflate(R.layout.no_connection, null);
    scrollLayout.removeAllViews();
    scrollLayout.addView(noInternetView);
}

}