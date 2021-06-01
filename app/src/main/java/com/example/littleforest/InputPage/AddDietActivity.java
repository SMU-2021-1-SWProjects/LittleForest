package com.example.littleforest.InputPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littleforest.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddDietActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AddDietActivity";

    // 데이터베이스
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private RecyclerView rv_diet;
    private RecyclerView.Adapter adapter_diet;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Diet> data_diet;

    // 페이지 & 날짜 & 시간
    private TextView txv_toolbar;
    private String date;
    private TextView tv_date;

    // 식단 추가
    private TextView tv_breakfast;
    private TextView tv_lunch;
    private TextView tv_dinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_diet);

        //---------- toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기

        //---------- 페이지 이름 & 날짜/시간
        Intent dateIntent = getIntent();
        date = dateIntent.getStringExtra("date");

        txv_toolbar = (TextView) findViewById(R.id.txv_toolbar);
        txv_toolbar.setText(date + " 식단");

        //---------- findViewById
        //데이터베이스
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Diet");

        rv_diet = findViewById(R.id.rv_diet);
        rv_diet.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rv_diet.setLayoutManager(layoutManager);

        data_diet = new ArrayList<>();

        // 식단 추가
        tv_breakfast = (TextView) findViewById(R.id.tv_breakfast);
        tv_lunch = (TextView) findViewById(R.id.tv_lunch);
        tv_dinner = (TextView) findViewById(R.id.tv_dinner);

        //--------- 버튼~리스너 연결
        tv_breakfast.setOnClickListener(this);
        tv_lunch.setOnClickListener(this);
        tv_dinner.setOnClickListener(this);

        //---------- 식단 보기
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_diet.clear();

                for(DataSnapshot s : snapshot.getChildren()){
                    Diet diet = s.getValue(Diet.class);

                    if(diet.getDate().equals(date)){
                        data_diet.add(diet);
                    }
                }
                adapter_diet.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AddDietActivity.this, "Database Error", Toast.LENGTH_SHORT).show();
            }
        });

        adapter_diet = new DietAdapter(data_diet, this);

        rv_diet.setAdapter(adapter_diet);
        //diet_lunch.setAdapter(adapter_diet);
        //diet_dinner.setAdapter(adapter_diet);
    }

    /**
     * 각 식단을 클릭하면 메뉴 추가 페이지로 넘어가는 함수
     * @param //view
     */
    @Override
    public void onClick(View view){
        Intent intent_addMenu = new Intent(AddDietActivity.this, AddMenuActivity.class);
        String time = "";

        switch (view.getId()){
            case R.id.tv_breakfast :
                time = "아침";
                break;
            case R.id.tv_lunch :
                time = "점심";
                break;
            case R.id.tv_dinner :
                time = "저녁";
        }
        intent_addMenu.putExtra("date", date);
        intent_addMenu.putExtra("time", time);
        startActivity(intent_addMenu);
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
