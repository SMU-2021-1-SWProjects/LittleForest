package com.example.littleforest.foodInfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.littleforest.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SearchFoodInfoActivity extends AppCompatActivity {
    private static final String TAG = "SearchFoodInfoActivity";
    private EditText editText_food;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference foodRef = db.collection("Food");

    private ArrayList<FoodInfo> foodInfo;
    private List<String> list;

    private SearchFoodInfoAdapter searchFoodInfoAdapter;

    private Context mContext;
    private TextView toolbarName;
    private LayoutInflater mLayoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchfoodinfo);


        //툴바
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기

        toolbarName = findViewById(R.id.txv_toolbar);
        toolbarName.setText("영양정보 검색");

//        //데이터 db 저장
//        this.load();

        // 현재 view 가져오기
        mContext = getApplicationContext();
        mLayoutInflater = LayoutInflater.from(mContext);
        View view = mLayoutInflater.inflate(R.layout.activity_searchfoodinfo, null);

        // 검색단어 가져오기
        editText_food = findViewById(R.id.editText_food);

        foodInfo = new ArrayList<FoodInfo>();
        list = new ArrayList<String>();
        
        // lisrview Adapter 연결
        ListView listView = findViewById(R.id.listView);
        searchFoodInfoAdapter = new SearchFoodInfoAdapter(this, foodInfo);

        listView.setAdapter(searchFoodInfoAdapter);

        // listview 클릭시
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Intent intent = new Intent(SearchFoodInfoActivity.this, FoodinfoActivity.class);

                intent.putExtra("name", foodInfo.get(position).getName());
                intent.putExtra("calorie", foodInfo.get(position).getCalorie());
                intent.putExtra("calUnit", foodInfo.get(position).getCalUnit());
                intent.putExtra("carbohydrate", foodInfo.get(position).getCarbohydrate());
                intent.putExtra("sugar", foodInfo.get(position).getSugar());
                intent.putExtra("protein", foodInfo.get(position).getProtein());
                intent.putExtra("cholesterol", foodInfo.get(position).getCholesterol());
                intent.putExtra("dietaryFiber", foodInfo.get(position).getDietaryFiber());
                intent.putExtra("na", foodInfo.get(position).getNa());
                intent.putExtra("k", foodInfo.get(position).getK());
                intent.putExtra("servingSize", foodInfo.get(position).getServingSize());
                intent.putExtra("servingUnit", foodInfo.get(position).getServingUnit());
                intent.putExtra("fat", foodInfo.get(position).getFat());
                startActivity(intent);
            }
        });
    }

    public void searchclick(View v)
    {
        foodInfo.clear();
        list.clear();

        String searchFood = editText_food.getText().toString();
        editText_food.setText(searchFood);

        foodRef.whereEqualTo("name", searchFood)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots){
                            FoodInfo food = documentSnapshot.toObject(FoodInfo.class);
                            food.setDocumentId(documentSnapshot.getId());

                            String name = food.getName();
                            int calorie = food.getCalorie();
                            String calUnit = food.getCalUnit();
                            int carbohydrate = food.getCarbohydrate();
                            int sugar = food.getSugar();
                            int protein = food.getProtein();
                            int cholesterol = food.getCholesterol();
                            int dietaryFiber = food.getDietaryFiber();
                            int na = food.getNa();
                            int k = food.getK();
                            int servingSize = food.getServingSize();
                            String servingUnit = food.getServingUnit();
                            int fat = food.getFat();

                            foodInfo.add(new FoodInfo(name, calorie, calUnit, carbohydrate,
                                    sugar, protein, cholesterol, dietaryFiber, na, k,
                                    servingSize, servingUnit, fat));
                            list.add(name);
                        }
                        searchFoodInfoAdapter.notifyDataSetChanged();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure");
                    }
                });
    }

    public void load(){
        String name = "닭갈비";
        int servingSize = 500;
        String servingUnit = "g";
        int calorie = 369;
        String calUnit = "kcal";
        int protein = 34;
        int fat = 9;
        int carbohydrate = 40;
        int sugar = 17;
        int dietaryFiber = 10;
        int k = 1243;
        int na = 1264;
        int cholesterol = 0;

        FoodInfo foodInfo = new FoodInfo(name, calorie, calUnit, carbohydrate,
                sugar, protein, cholesterol, dietaryFiber, na, k,
                servingSize, servingUnit, fat);

        foodRef.add(foodInfo);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ // 뒤로가기 버튼 눌렀을 때
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
