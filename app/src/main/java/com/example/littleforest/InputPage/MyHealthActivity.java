package com.example.littleforest.InputPage;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.littleforest.R;
import com.example.littleforest.SearchFood.FoodInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MyHealthActivity extends AppCompatActivity {

    private static final String TAG = "MyHealthActivity";

    // 데이터베이스
    private FirebaseDatabase database = FirebaseDatabase.getInstance();;
    private DatabaseReference databaseReference = database.getReference("Diet");

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference foodRef = db.collection("Food");

    //-----
    private ArrayList<Diet> my_dietList;
    private ArrayList<FoodInfo> my_foodInfoList;

    private ArrayList<String> foodName_List;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_health);

        my_dietList = new ArrayList<>();
        my_foodInfoList = new ArrayList<>();
    }

    public void getDietData(){
        my_dietList.clear();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot s : snapshot.getChildren()){
                    Diet diet = s.getValue(Diet.class);
                    my_dietList.add(diet);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MyHealthActivity.this, "Database Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getFoodInfoData(){
        my_foodInfoList.clear();

    }
}
