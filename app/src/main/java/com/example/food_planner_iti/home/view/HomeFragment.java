package com.example.food_planner_iti.home.view;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.food_planner_iti.R;
import com.example.food_planner_iti.home.presenter.HomePresenter;
import com.example.food_planner_iti.local_database.DatabaseManger;
import com.example.food_planner_iti.local_database.Meal;
import com.example.food_planner_iti.model.AreasName;
import com.example.food_planner_iti.model.CategoriesItem;
import com.example.food_planner_iti.network.NetworkManger;
import com.example.food_planner_iti.repository.MealRepositoryImple;
import com.example.food_planner_iti.view.LoginActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;

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
    CheckBox fav, plan;
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
        homePresenter = new HomePresenter(this, new MealRepositoryImple(new NetworkManger(), new DatabaseManger(getActivity())));
        scrollLayout = view.findViewById(R.id.scrollView);
        nameRandomMeal = view.findViewById(R.id.nameRandomMeal);
        imageView = view.findViewById(R.id.imageRandMeal);
        categoryRecycle = view.findViewById(R.id.recycleCategories);
        countryRecycle = view.findViewById(R.id.recycleCountries);
        randomMeal = view.findViewById(R.id.randomMeal);
        fav = view.findViewById(R.id.addToFav);
        plan = view.findViewById(R.id.addToPlan);
        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!FirebaseAuth.getInstance().getCurrentUser().isAnonymous()) {
                    editor.putBoolean(key_fav, fav.isChecked());
                    editor.commit();
                    if (fav.isChecked()) {
                        homePresenter.insertFavMeal(meal);
                    } else {
                        homePresenter.deleteFavMeal(meal);
                    }
                } else {
                    fav.setChecked(false);
                    new MaterialAlertDialogBuilder(getContext()).setTitle("login first")
                            .setMessage("you are a guest currently, you should login first")
                            .setPositiveButton("log in", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(getActivity(), LoginActivity.class));
                                }
                            }).setNegativeButton("cancel", null).show();
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
                        homePresenter.deletePlanMeal(meal, selectedOption);
                    }
                } else {
                    plan.setChecked(false);
                    new MaterialAlertDialogBuilder(getContext()).setTitle("login first")
                            .setMessage("you are a guest currently, you should login first")
                            .setPositiveButton("log in", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(getActivity(), LoginActivity.class));
                                }
                            }).setNegativeButton("cancel", null).show();
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
                            homePresenter.insertPlanMeal(meal, selectedOption);

                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        plan.setChecked(false);
                        editor.putBoolean(key_plan, plan.isChecked());
                        editor.commit();
                    }
                })
                .show();
    }

    @Override
    public void getSingleRandomMeal(Meal meal) {
        this.meal = meal;
        nameRandomMeal.setText(meal.getName());
        if (meal.getImageUrl() != null)
            Glide.with(this).load(meal.getImageUrl()).into(imageView);
        actionHomeToMealDetailsFragment = HomeFragmentDirections.actionHomeToMealDetailsFragment(meal.getId());
        key_fav = meal.getId() + "f";
        fav.setChecked(sharedPreferences.getBoolean(key_fav, false));
        key_plan = meal.getId() + "p";
        plan.setChecked(sharedPreferences.getBoolean(key_plan, false));
    }

    @Override
    public void getAllCategoryItems(ArrayList<CategoriesItem> categoriesItemArrayList) {
        adapter = new CategoryAdapter(this.getContext(), categoriesItemArrayList);
        categoryRecycle.setAdapter(adapter);
        new LinearSnapHelper().attachToRecyclerView(categoryRecycle);
    }

    @Override
    public void getCountries(ArrayList<AreasName> areasNames) {
        adapter2 = new CountryAdapter(areasNames, this.getContext());
        countryRecycle.setAdapter(adapter2);
        new LinearSnapHelper().attachToRecyclerView(countryRecycle);
    }

    @Override
    public void noConnection() {
        showNoConnection();
    }

    private void showNoConnection() {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
        View noInternetView = inflater.inflate(R.layout.no_connection, null);
        scrollLayout.removeAllViews();
        scrollLayout.addView(noInternetView);
    }

}