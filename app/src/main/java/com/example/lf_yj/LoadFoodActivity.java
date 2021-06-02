package com.example.lf_yj;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lf_yj.foodInfo.FoodInfo;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class LoadFoodActivity extends AppCompatActivity {
    private static final String TAG = "LoadFoodActivity";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference foodRef = db.collection("Food");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String name = "소고기";
        int calorie = 240;
        String calUnit = "kcal";
        int carbohydrate = 0;
        int sugar = 0;
        int protein = 0;
        int cholesterol = 74;
        int dietaryFiber = 0;
        int na = 299;
        int k = 27;
        String goodfood = "들깻잎";
        String badfood = "고구마";

        FoodInfo foodInfo = new FoodInfo(name, calorie, calUnit, carbohydrate,
                sugar, protein, cholesterol, dietaryFiber, na, k,
                goodfood, badfood);

        foodRef.add(foodInfo);

    }
}
