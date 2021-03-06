package com.example.littleforest.foodInfo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.littleforest.R;

import java.util.ArrayList;

public class FoodinfoActivity extends AppCompatActivity {
    private static final String TAG = "FoodinfoActivity";

    private ArrayList<FoodInfo> foodInfo;

    private FoodInfoAdapter foodInfoAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acrivity_foodinfo);

        // 검색단어 가져오기
        Intent intent = getIntent();

        final String name = intent.getStringExtra("name");
        final int calorie = intent.getIntExtra("calorie", 1);
        final String calUnit = intent.getStringExtra("calUnit");
        final int carbohydrate = intent.getIntExtra("carbohydrate", 1);
        final int sugar = intent.getIntExtra("sugar", 1);
        final int protein = intent.getIntExtra("protein", 1);
        final int cholesterol = intent.getIntExtra("cholesterol", 1);
        final int dietaryFiber = intent.getIntExtra("dietaryFiber", 1);
        final int na = intent.getIntExtra("na", 1);
        final int k = intent.getIntExtra("k", 1);

        final int servingSize = intent.getIntExtra("servingSize", 1);
        final String servingUnit = intent.getStringExtra("servingUnit");
        final int fat = intent.getIntExtra("fat", 1);

        this.InitializeMovieData(name, calorie, calUnit, carbohydrate, sugar, protein,
                cholesterol, dietaryFiber, na, k, servingSize, servingUnit, fat);


        // lisrview Adapter 연결
        ListView listView = findViewById(R.id.test_listView);
        foodInfoAdapter = new FoodInfoAdapter(this, foodInfo);

        listView.setAdapter(foodInfoAdapter);
    }

    public void InitializeMovieData(String name, int calorie, String calUnit, int carbohydrate,
                                    int sugar, int protein, int cholesterol, int dietaryFiber, int na, int k,
                                    int servingSize, String servingUnit, int fat)
    {
        foodInfo = new ArrayList<FoodInfo>();
        foodInfo.add(new FoodInfo(name, calorie, calUnit, carbohydrate, sugar, protein,
                cholesterol, dietaryFiber, na, k, servingSize, servingUnit, fat));
    }
}
