package com.example.food_planner_iti.local_database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

import java.util.ArrayList;

public class converters {
    @TypeConverter
    public String fromArrayListToString(ArrayList<String> arrayList){
        return new Gson().toJson(arrayList);
    }
    @TypeConverter
    public ArrayList<String> fromStringToArrayList(String arrayList){
        return new Gson().fromJson(arrayList,ArrayList.class);
    }
}
