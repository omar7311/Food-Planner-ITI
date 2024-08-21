package com.example.food_planner_iti.model;

public class MealItem{
	private String strMealThumb;
	private String idMeal;
	private String strMeal;

	public MealItem(String strMealThumb, String idMeal, String strMeal) {
		this.strMealThumb = strMealThumb;
		this.idMeal = idMeal;
		this.strMeal = strMeal;
	}

	public String getStrMealThumb(){
		return strMealThumb;
	}

	public String getIdMeal(){
		return idMeal;
	}

	public String getStrMeal(){
		return strMeal;
	}
}
