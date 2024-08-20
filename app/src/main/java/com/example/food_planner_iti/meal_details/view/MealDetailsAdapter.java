package com.example.food_planner_iti.meal_details.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.food_planner_iti.R;

import java.util.ArrayList;

public class MealDetailsAdapter extends RecyclerView.Adapter<MealDetailsAdapter.MealsDetailsViewHolder> {
    ArrayList<CustomIngredient> ingredients;
    Context context;

    public MealDetailsAdapter(ArrayList<CustomIngredient> ingredients, Context context) {
        this.ingredients = ingredients;
        this.context = context;
    }

    @NonNull
    @Override
    public MealsDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false);
        return new MealsDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealsDetailsViewHolder holder, int position) {
       holder.textView.setText(ingredients.get(position).getIngredient());
        Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+ingredients.get(position).getIngredientImages()+".png")
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }
    class  MealsDetailsViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public MealsDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.categoryName);
            imageView=itemView.findViewById(R.id.imageCategory);
        }
    }
}
