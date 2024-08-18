package com.example.food_planner_iti.home.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.food_planner_iti.R;
import com.example.food_planner_iti.model.AreasName;
import android.content.Context;
import java.util.ArrayList;


public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {
    ArrayList<AreasName> areasNames;
    Context context;

    public CountryAdapter(ArrayList<AreasName> areasNames, Context context) {
        this.areasNames = areasNames;
        this.context = context;
    }

    @NonNull
    @Override
    public CountryAdapter.CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.country_item,parent,false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.CountryViewHolder holder, int position) {
        holder.countryName.setText(areasNames.get(position).getStrArea());
        Glide.with(context).load("https://flagsapi.com/"
                +getUrlForCountry(areasNames.get(position).getStrArea()).toUpperCase()+
                "/flat/64.png").into(holder.countryImage);
    }
    private String getUrlForCountry(String name) {
        switch (name) {
            case "American":
                return "us";
            case "British":
                return "gb";
            case "Canadian":
                return "ca";
            case "Chinese":
                return "cn";
            case "Croatian":
                return "hr";
            case "Dutch":
                return "nl";
            case "Egyptian":
                return "eg";
            case "Filipino":
                return "ph";
            case "French":
                return "fr";
            case "Greek":
                return "gr";
            case "Indian":
                return "in";
            case "Irish":
                return "ie";
            case "Italian":
                return "it";
            case "Jamaican":
                return "jm";
            case "Japanese":
                return "jp";
            case "Kenyan":
                return "ke";
            case "Malaysian":
                return "my";
            case "Mexican":
                return "mx";
            case "Moroccan":
                return "ma";
            case "Polish":
                return "pl";
            case "Portuguese":
                return "pt";
            case "Russian":
                return "ru";
            case "Spanish":
                return "es";
            case "Thai":
                return "th";
            case "Tunisian":
                return "tn";
            case "Turkish":
                return "tr";
            case "Vietnamese":
                return "vn";
            default:
                return "";
        }
    }
    @Override
    public int getItemCount() {
        return areasNames.size();
    }

    static class CountryViewHolder extends RecyclerView.ViewHolder {
        ImageView countryImage;
        TextView countryName;
        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
           countryImage= itemView.findViewById(R.id.imageCountry);
           countryName=itemView.findViewById(R.id.countryName);
        }
    }
}
