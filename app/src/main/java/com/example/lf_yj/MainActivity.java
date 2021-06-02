package com.example.lf_yj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lf_yj.foodInfo.SearchFoodInfoActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loadFood(View v){
        Intent intent = new Intent(MainActivity.this, LoadFoodActivity.class);
        startActivity(intent);
    }

    public void searchFoodInfo(View v){
        Intent intent = new Intent(MainActivity.this, SearchFoodInfoActivity.class);
        startActivity(intent);
    }
}