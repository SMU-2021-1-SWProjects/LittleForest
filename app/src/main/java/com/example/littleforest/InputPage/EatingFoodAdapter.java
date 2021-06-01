package com.example.littleforest.InputPage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littleforest.R;

import java.util.ArrayList;

class DietAdapter extends RecyclerView.Adapter<DietAdapter.CustomViewHolder> {

    private ArrayList<Diet> data_diet;
    private Context context;

    public DietAdapter(ArrayList<Diet> data_diet, Context context){
        this.data_diet = data_diet;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_diet, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.diet_time.setText(String.valueOf(data_diet.get(position).getTime()));
        holder.diet_item.setText(String.valueOf(data_diet.get(position).getMenu()));
    }

    @Override
    public int getItemCount() {
        return (data_diet != null ? data_diet.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView diet_time;
        TextView diet_item;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            this.diet_time = itemView.findViewById(R.id.diet_time);
            this.diet_item = itemView.findViewById(R.id.diet_item);
        }
    }
}

class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.CustomViewHolder> {

    private ArrayList<Diet> data_menu;
    private Context context;

    public MenuAdapter(ArrayList<Diet> data_menu, Context context){
        this.data_menu = data_menu;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_menu, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.menu_item.setText(String.valueOf(data_menu.get(position).getMenu()));
    }

    @Override
    public int getItemCount() {
        return (data_menu != null ? data_menu.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView menu_item;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.menu_item = itemView.findViewById(R.id.menu_item);
        }
    }
}
