package com.example.food_planner_iti.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class RetrofitClient {
    private static final String BASE_URL="https://themealdb.com/api/json/v1/1/";
  private static Retrofit retrofit;
   public static WebServices getService(){
       if(retrofit==null){
           retrofit=new Retrofit.Builder()
                   .baseUrl(BASE_URL)
                   .addConverterFactory(GsonConverterFactory.create())
                   .build();
       }
       return retrofit.create(WebServices.class);
   }
}
