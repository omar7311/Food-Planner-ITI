package com.example.food_planner_iti.meal_details.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.food_planner_iti.R;
import com.example.food_planner_iti.local_database.DatabaseManger;
import com.example.food_planner_iti.local_database.Meal;
import com.example.food_planner_iti.meal_details.presenter.MealDetailsPresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class MealDetailsFragment extends Fragment implements MealDetailFragmentInterface {
    ImageView mealImage;
    TextView mealName, country, description;
    WebView webView;
    ArrayList<CustomIngredient> customIngredients;
    RecyclerView recyclerView;
    MealDetailsAdapter adapter;
    MealDetailsPresenter presenter;
    CheckBox fav, plan;
    SharedPreferences sharedPreferences;
    String key_fav;
    String key_plan;
    Meal meal;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meal_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mealImage = view.findViewById(R.id.mealImage);
        mealName = view.findViewById(R.id.mealName);
        country = view.findViewById(R.id.country);
        fav = view.findViewById(R.id.addToFav);
        plan = view.findViewById(R.id.addToPlan);
        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        presenter = new MealDetailsPresenter(this,new DatabaseManger(getContext(),this));
        fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean(key_fav, isChecked);
                editor.commit();
                if(isChecked){
                    new Thread( ()-> presenter.insertFavMeal(meal)).start();
                    FirebaseDatabase.getInstance().getReference("Meals")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .child("meal_fav").child(meal.getId()).setValue(meal);
                }
                else {
                    new Thread( ()-> presenter.deleteFavMeal(meal)).start();
                    FirebaseDatabase.getInstance().getReference("Meals")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .child("meal_fav").child(meal.getId()).setValue(null);
                }
            }
        });
        plan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean(key_plan, isChecked);
                editor.commit();
            }
        });
        description = view.findViewById(R.id.description);
        webView = view.findViewById(R.id.webView);
        recyclerView = view.findViewById(R.id.recycleIngredient);
        String id = MealDetailsFragmentArgs.fromBundle(getArguments()).getMealDetails();
        presenter.sendMealId(id);
    }

    private void showMealDetails(Meal meal) {
        key_fav = meal.getId() + "f";
        key_plan = meal.getId() + "p";

        Glide.with(this.getContext()).load(meal.getImageUrl()).into(mealImage);
        mealName.setText(meal.getName());
        description.setText(meal.getSteps());
        country.setText(meal.getCountry());
        fav.setChecked(sharedPreferences.getBoolean(key_fav, false));
        plan.setChecked(sharedPreferences.getBoolean(key_plan, false));

        customIngredients = new ArrayList<>();
        for (int i = 0; i < meal.getIngredients().size() - 1; i++) {
            customIngredients.add(new CustomIngredient(meal.getIngredients().get(i), meal.getIngredientsImage().get(i)));
        }
        adapter = new MealDetailsAdapter(customIngredients, this.getContext());
        recyclerView.setAdapter(adapter);
        new LinearSnapHelper().attachToRecyclerView(recyclerView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        if (meal.getVideoUrl() != null) {
            String[] videoUrl = meal.getVideoUrl().split("=");
            String videoId = videoUrl[1]; // YouTube video ID
            String iframeHtml = "<html><body>" +
                    "<iframe width=\"100%\" height=\"100%\" " +
                    "src=\"https://www.youtube.com/embed/" + videoId + "\" " +
                    "frameborder=\"0\" allowfullscreen></iframe>" +
                    "</body></html>";
            webView.loadData(iframeHtml, "text/html", "utf-8");

            // Make sure links open within the WebView
            webView.setWebViewClient(new WebViewClient());
        }
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        }
    }

    @Override
    public void getMealDetails(Meal meal) {
        this.meal=meal;
        showMealDetails(meal);
    }

    @Override
    public void errorMessage(String error) {
        Toast.makeText(this.getContext(), error, Toast.LENGTH_SHORT).show();
    }
}