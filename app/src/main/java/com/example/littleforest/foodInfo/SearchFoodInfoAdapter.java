package com.example.littleforest.foodInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.littleforest.R;

import java.util.ArrayList;

public class SearchFoodInfoAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    //ArrayList<SearchFoodInfo> searchFoodInfo;
    ArrayList<FoodInfo> foodInfo;

    public SearchFoodInfoAdapter(Context context, ArrayList<FoodInfo> data) {
        mContext = context;
        foodInfo = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return foodInfo.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public FoodInfo getItem(int position) {
        return foodInfo.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.listview_searchfoodinfo, null);

        TextView text_name = (TextView)view.findViewById(R.id.text_name);
        TextView calorie_num = (TextView)view.findViewById(R.id.calorie_num);
        TextView carbohydrate_num = (TextView)view.findViewById(R.id.carbohydrate_num);
        TextView protein_num = (TextView)view.findViewById(R.id.protein_num);
        TextView fat_num = (TextView)view.findViewById(R.id.Fat_num);

        text_name.setText(foodInfo.get(position).getName());
        calorie_num.setText(String.valueOf(foodInfo.get(position).getCalorie()));
        protein_num.setText(String.valueOf(foodInfo.get(position).getProtein()));
        fat_num.setText(String.valueOf(foodInfo.get(position).getFat()));
        carbohydrate_num.setText(String.valueOf(foodInfo.get(position).getCarbohydrate()));

        return view;
    }
}
