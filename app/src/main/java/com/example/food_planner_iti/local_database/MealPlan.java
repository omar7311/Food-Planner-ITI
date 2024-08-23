package com.example.food_planner_iti.local_database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "mealPlan_table")
public class MealPlan {
    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private String imageUrl;
    private String country;
    private ArrayList<String> ingredients;
    private ArrayList<String> ingredientsImage;

    private String steps;
    private String videoUrl;
    private String date;

    public ArrayList<String> getIngredientsImage() {
        return ingredientsImage;
    }

    public void setIngredientsImage(ArrayList<String> ingredientsImage) {
        this.ingredientsImage = ingredientsImage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCountry() {
        return country;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public String getSteps() {
        return steps;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}


