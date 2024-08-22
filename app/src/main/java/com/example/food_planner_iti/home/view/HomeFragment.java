package com.example.food_planner_iti.home.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.food_planner_iti.R;
import com.example.food_planner_iti.home.presenter.HomePresenter;
import com.example.food_planner_iti.home.presenter.HomePresenterInterface;
import com.example.food_planner_iti.local_database.DatabaseManger;
import com.example.food_planner_iti.local_database.Meal;
import com.example.food_planner_iti.model.AreasName;
import com.example.food_planner_iti.model.CategoriesItem;
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
    String key_fav;
    String key_plan;
    Meal meal;
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
        nameRandomMeal=view.findViewById(R.id.nameRandomMeal);
        imageView=view.findViewById(R.id.imageRandMeal);
        categoryRecycle=view.findViewById(R.id.recycleCategories);
        countryRecycle=view.findViewById(R.id.recycleCountries);
        randomMeal=view.findViewById(R.id.randomMeal);
        fav=view.findViewById(R.id.addToFav);
        plan=view.findViewById(R.id.addToPlan);
        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               editor.putBoolean(key_fav,isChecked);
               editor.commit();
               if(isChecked){
                   new Thread( ()->homePresenter.insertFavMeal(meal) ).start();
                   FirebaseDatabase.getInstance().getReference("Meals")
                           .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                           .child("meal_fav").child(meal.getId()).setValue(meal);
               } else {
                   new Thread(()-> homePresenter.deleteFavMeal(meal)).start();
                   FirebaseDatabase.getInstance().getReference("Meals")
                           .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                           .child("meal_fav").child(meal.getId()).setValue(null);
               }
            }
        });
        plan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean(key_plan,isChecked);
                editor.commit();
            }
        });
        randomMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(actionHomeToMealDetailsFragment);
            }
        });
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
        Snackbar.make(this.getView(),error,Snackbar.LENGTH_LONG).show();
    }


}