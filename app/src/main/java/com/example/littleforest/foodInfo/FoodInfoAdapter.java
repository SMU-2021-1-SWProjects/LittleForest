package com.example.littleforest.foodInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.littleforest.R;

import java.util.ArrayList;

public class FoodInfoAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<FoodInfo> foodInfo;

    public FoodInfoAdapter(Context context, ArrayList<FoodInfo> data) {
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
        View view = mLayoutInflater.inflate(R.layout.listview_foodinfo, null);

        TextView name = (TextView)view.findViewById(R.id.textView_name);
        TextView calorie = (TextView)view.findViewById(R.id.textView_calorie);
        //TextView calUnit = (TextView)view.findViewById(R.id.textView_calUnit);
        TextView carbohydrate = (TextView)view.findViewById(R.id.textView_carbohydrate);
        TextView sugar = (TextView)view.findViewById(R.id.textView_sugar);
        TextView protein = (TextView)view.findViewById(R.id.textView_protein);
        TextView cholesterol = (TextView)view.findViewById(R.id.textView_cholesterol);
        TextView dietaryFiber = (TextView)view.findViewById(R.id.textView_dietaryFiber);
        TextView na = (TextView)view.findViewById(R.id.textView_na);
        TextView k = (TextView)view.findViewById(R.id.textView_k);
        TextView servingSize = (TextView)view.findViewById(R.id.textView_servingSize);
        TextView fat = (TextView)view.findViewById(R.id.textView_fat);

        name.setText((foodInfo.get(position).getName()));
        calorie.setText("칼로리: " + (String.valueOf(foodInfo.get(position).getCalorie())) + (foodInfo.get(position).getCalUnit()));
        carbohydrate.setText("탄수화물: " + (String.valueOf(foodInfo.get(position).getCarbohydrate())) + "g");
        sugar.setText("당: " + String.valueOf(foodInfo.get(position).getSugar()) + "g");
        protein.setText("단백질: " + String.valueOf(foodInfo.get(position).getProtein() + "g"));
        cholesterol.setText("콜레스테롤: " + String.valueOf(foodInfo.get(position).getCholesterol()) + "g");
        dietaryFiber.setText("식이섬유: " + String.valueOf(foodInfo.get(position).getDietaryFiber()) + "g");
        na.setText("나트륨: " + String.valueOf(foodInfo.get(position).getNa()) + "mg");
        k.setText("칼륨: " + String.valueOf(foodInfo.get(position).getK()) + "mg");
        servingSize.setText("1회 제공량: " +(foodInfo.get(position).getServingSize())+ (foodInfo.get(position).getServingUnit()));
        fat.setText("지방: " + foodInfo.get(position).getFat()+ "mg");
        return view;
    }
}
