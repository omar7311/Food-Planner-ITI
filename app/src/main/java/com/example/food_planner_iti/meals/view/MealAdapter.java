package com.example.food_planner_iti.meals.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.food_planner_iti.R;
import com.example.food_planner_iti.fav_meal.view.FavouriteFragment;
import com.example.food_planner_iti.fav_meal.view.FavouriteFragmentDirections;
import com.example.food_planner_iti.local_database.Meal;
import com.example.food_planner_iti.local_database.MealPlan;
import com.example.food_planner_iti.model.FullMealDetailById;
import com.example.food_planner_iti.model.MealItem;
import com.example.food_planner_iti.network.NetworkManger;
import com.example.food_planner_iti.network.RetrofitClient;
import com.example.food_planner_iti.plan_meal.view.PlanFragment;
import com.example.food_planner_iti.plan_meal.view.PlanFragmentDirections;
import com.example.food_planner_iti.search.view.SearchFragment;
import com.example.food_planner_iti.search.view.SearchFragmentDirections;
import com.example.food_planner_iti.view.LoginActivity;
import com.example.food_planner_iti.view.MainActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealViewHolder> {
    ArrayList<MealItem> mealItems;
    Context context;
    Fragment fragment;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String key_fav;
    String key_plan;
    ClickListener clickListener;

    public MealAdapter(ArrayList<MealItem> mealItems, Context context, Fragment fragment, ClickListener clickListener) {
        this.mealItems = mealItems;
        this.context = context;
        this.fragment = fragment;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MealAdapter.MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_item, parent, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealAdapter.MealViewHolder holder, int position) {
        holder.mealName.setText(mealItems.get(position).getStrMeal());
        Glide.with(context).load(mealItems.get(position).getStrMealThumb()).into(holder.mealImage);
        sharedPreferences = fragment.getActivity().getPreferences(Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        key_fav = mealItems.get(position).getIdMeal() + "f";
        key_plan = mealItems.get(position).getIdMeal() + "p";
        holder.fav.setChecked(sharedPreferences.getBoolean(key_fav, false));
        holder.plan.setChecked(sharedPreferences.getBoolean(key_plan, false));
    }

    @Override
    public int getItemCount() {
        return mealItems.size();
    }


    public class MealViewHolder extends RecyclerView.ViewHolder {
        ImageView mealImage;
        TextView mealName;
        CheckBox fav, plan;

        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImage = itemView.findViewById(R.id.imageMeal);
            mealName = itemView.findViewById(R.id.nameMeal);
            fav = itemView.findViewById(R.id.addToFav);
            plan = itemView.findViewById(R.id.addToPlan);

            fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (!FirebaseAuth.getInstance().getCurrentUser().isAnonymous()) {
                        editor.putBoolean(mealItems.get(getLayoutPosition()).getIdMeal() + "f", isChecked);
                        editor.commit();
                        RetrofitClient.getService().fullMealDetailById(mealItems.get(getLayoutPosition()).getIdMeal())
                                .enqueue(new Callback<FullMealDetailById>() {
                                    @Override
                                    public void onResponse(Call<FullMealDetailById> call, Response<FullMealDetailById> response) {
                                        if (isChecked) {
                                            clickListener.onClickInsert(new NetworkManger().getMeal(response.body().getMeals().get(0)));
                                        } else {
                                            clickListener.onClickDelete(new NetworkManger().getMeal(response.body().getMeals().get(0)));
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<FullMealDetailById> call, Throwable t) {

                                    }
                                });
                    } else {
                        fav.setChecked(false);
                        new MaterialAlertDialogBuilder(context).setTitle("login first")
                                .setMessage("you are a guest currently, you should login first")
                                .setPositiveButton("log in", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        context.startActivity(new Intent(fragment.getActivity(), LoginActivity.class));
                                    }
                                }).setNegativeButton("cancel", null).show();
                    }
                }
            });
            plan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!FirebaseAuth.getInstance().getCurrentUser().isAnonymous()) {
                        editor.putBoolean(mealItems.get(getLayoutPosition()).getIdMeal() + "p", plan.isChecked());
                        editor.commit();
                        RetrofitClient.getService().fullMealDetailById(mealItems.get(getLayoutPosition()).getIdMeal())
                                .enqueue(new Callback<FullMealDetailById>() {
                                    @Override
                                    public void onResponse(Call<FullMealDetailById> call, Response<FullMealDetailById> response) {
                                        if (plan.isChecked()) {
                                            clickListener.onClickInsertMealPlan(plan, new NetworkManger().getMeal(response.body().getMeals().get(0)));
                                        } else {
                                            clickListener.onClickDeleteMealPlan(new NetworkManger().getMeal(response.body().getMeals().get(0)));
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<FullMealDetailById> call, Throwable t) {

                                    }
                                });
                    } else {
                        plan.setChecked(false);
                        new MaterialAlertDialogBuilder(context).setTitle("login first")
                                .setMessage("you are a guest currently, you should login first")
                                .setPositiveButton("log in", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        context.startActivity(new Intent(fragment.getActivity(), LoginActivity.class));
                                    }
                                }).setNegativeButton("cancel", null).show();
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (fragment instanceof MealsFragment) {
                        MealsFragmentDirections.ActionMealsFragmentToMealDetailsFragment action = MealsFragmentDirections
                                .actionMealsFragmentToMealDetailsFragment
                                        (mealItems.get(getLayoutPosition()).getIdMeal());
                        Navigation.findNavController(v).navigate(action);
                    }
                    if (fragment instanceof SearchFragment) {
                        SearchFragmentDirections.ActionSearchToMealDetailsFragment action =
                                SearchFragmentDirections.actionSearchToMealDetailsFragment
                                        (mealItems.get(getLayoutPosition()).getIdMeal());
                        Navigation.findNavController(v).navigate(action);
                    }
                    if (fragment instanceof FavouriteFragment) {
                        FavouriteFragmentDirections.ActionFavouriteToMealDetailsFragment action =
                                FavouriteFragmentDirections.actionFavouriteToMealDetailsFragment
                                        (mealItems.get(getLayoutPosition()).getIdMeal());
                        Navigation.findNavController(v).navigate(action);
                    }
                    if (fragment instanceof PlanFragment) {
                        PlanFragmentDirections.ActionPlanToMealDetailsFragment action =
                                PlanFragmentDirections.actionPlanToMealDetailsFragment
                                        (mealItems.get(getLayoutPosition()).getIdMeal());
                        Navigation.findNavController(v).navigate(action);
                    }
                }
            });
        }


    }
}

