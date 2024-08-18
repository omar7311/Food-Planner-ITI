package com.example.food_planner_iti.home.view;

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
import com.example.food_planner_iti.model.CategoriesItem;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
ArrayList<CategoriesItem> categoriesItems;
Context context;

    public CategoryAdapter(Context context,ArrayList<CategoriesItem> categoriesItems) {
        this.context=context;
        this.categoriesItems = categoriesItems;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
     holder.categoryName.setText(categoriesItems.get(position).getStrCategory());
     Glide.with(context).load(categoriesItems.get(position).getStrCategoryThumb()).into(holder.categoryImage);
    }

    @Override
    public int getItemCount() {
        return categoriesItems.size();
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder{
        ImageView categoryImage;
        TextView categoryName;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImage=itemView.findViewById(R.id.imageCategory);
            categoryName=itemView.findViewById(R.id.categoryName);
        }
    }
}
