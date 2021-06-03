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
        TextView goodfood = (TextView)view.findViewById(R.id.textView_goodfood);
        TextView badfood = (TextView)view.findViewById(R.id.textView_badfood);

        name.setText((foodInfo.get(position).getName()));
        calorie.setText("칼로리: " + (String.valueOf(foodInfo.get(position).getCalorie())) + (foodInfo.get(position).getCalUnit()));
        carbohydrate.setText("탄수화물: " + (String.valueOf(foodInfo.get(position).getCarbohydrate())));
        sugar.setText("당: " + String.valueOf(foodInfo.get(position).getSugar()));
        protein.setText("단백질: " + String.valueOf(foodInfo.get(position).getProtein()));
        cholesterol.setText("콜레스테롤: " + String.valueOf(foodInfo.get(position).getCholesterol()));
        dietaryFiber.setText("식이섬유: " + String.valueOf(foodInfo.get(position).getDietaryFiber()));
        na.setText("나트륨: " + String.valueOf(foodInfo.get(position).getNa()));
        k.setText("칼륨: " + String.valueOf(foodInfo.get(position).getK()));
        goodfood.setText("상성 좋은 음식: " + foodInfo.get(position).getGoodfood());
        badfood.setText("상성에 좋지 않은 음식: " + foodInfo.get(position).getBadfood());

        return view;
    }
}
