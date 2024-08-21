package com.example.food_planner_iti.search.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.food_planner_iti.R;
import com.example.food_planner_iti.local_database.Meal;
import com.example.food_planner_iti.meals.presenter.MealPresenter;
import com.example.food_planner_iti.meals.view.MealAdapter;
import com.example.food_planner_iti.meals.view.MealsFragmentInterface;
import com.example.food_planner_iti.model.MealItem;
import com.example.food_planner_iti.search.presenter.SearchPresenter;
import com.google.android.material.search.SearchBar;
import com.google.android.material.search.SearchView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class SearchFragment extends Fragment implements SearchFragmentInterface, MealsFragmentInterface {

    SearchPresenter presenter;
    SearchBar searchBar;
    SearchView searchView;
    RecyclerView recyclerView;
    MealAdapter adapter;
    MealPresenter mealPresenter;
    String flag;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new SearchPresenter(this);
        mealPresenter=new MealPresenter(this);
        searchBar = view.findViewById(R.id.search_bar);
        searchView = view.findViewById(R.id.searchView);
        searchView.inflateMenu(R.menu.search_menu);
        recyclerView=view.findViewById(R.id.recycleSearch);
        flag = getString(R.string.search_by_name);
        searchView
                .getEditText()
                .setOnEditorActionListener(
                        (v, actionId, event) -> {
                            if(!searchView.getText().toString().isEmpty()) {
                                if (flag == getString(R.string.search_by_name)) {
                                    presenter.sendMealName(searchView.getText().toString());
                                } else if (flag == getString(R.string.search_by_ingredient)) {
                                    presenter.sendIngredientName(searchView.getText().toString());
                                } else if (flag == getString(R.string.search_by_category)) {
                                    mealPresenter.sendCategoryName(searchView.getText().toString());
                                } else if (flag == getString(R.string.search_by_country)) {
                                    mealPresenter.sendAreaName(searchView.getText().toString());
                                }
                                searchBar.setHint(searchView.getText());
                                searchView.setText("");
                                searchView.hide();
                            }
                            return false;
                        });
        searchView.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                searchView.setHint(item.getTitle());
                searchBar.setHint(item.getTitle());
                flag =item.getTitle().toString();
                  return false;
            }
        });

    }

    @Override
    public void getMealsByName(ArrayList<Meal> meals) {
        ArrayList<MealItem> mealItems=new ArrayList<>();
        for(int i=0;i<meals.size();i++){
            mealItems.add(new MealItem(meals.get(i).getImageUrl(),meals.get(i).getId()
                    ,meals.get(i).getName()));
        }
        adapter=new MealAdapter(mealItems,this.getContext(),this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getMealsByIngredient(ArrayList<MealItem> mealItems) {
        adapter=new MealAdapter(mealItems,this.getContext(),this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getMealsByArea(ArrayList<MealItem> mealItem) {
        adapter=new MealAdapter(mealItem,this.getContext(),this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getMealsByCategory(ArrayList<MealItem> mealItem) {
        adapter=new MealAdapter(mealItem,this.getContext(),this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void errorMessage(String error) {
        Snackbar.make(this.getView(),error,Snackbar.LENGTH_SHORT).show();
    }
}