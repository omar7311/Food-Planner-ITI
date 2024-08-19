package com.example.food_planner_iti.meals.view;

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
import com.example.food_planner_iti.model.MealItem;

import java.util.ArrayList;


public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealViewHolder> {
    ArrayList<MealItem> mealItems;
    Context context;

    public MealAdapter(ArrayList<MealItem> mealItems,Context context) {
        this.mealItems = mealItems;
        this.context=context;
    }

    @NonNull
    @Override
    public MealAdapter.MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_item,parent,false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealAdapter.MealViewHolder holder, int position) {
      holder.mealName.setText(mealItems.get(position).getStrMeal());
      Glide.with(context).load(mealItems.get(position).getStrMealThumb()).into(holder.mealImage);
    }

    @Override
    public int getItemCount() {
        return mealItems.size();
    }

    public class MealViewHolder extends RecyclerView.ViewHolder {
        ImageView mealImage;
        TextView mealName;
        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImage=itemView.findViewById(R.id.imageMeal);
            mealName=itemView.findViewById(R.id.nameMeal);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    
                }
            });
        }
    }
}
