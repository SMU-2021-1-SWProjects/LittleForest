package com.example.littleforest.InputPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.littleforest.R;

public class AddDietActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "AddDietActivity";

    //private ImageButton page_calendar;
    private TextView tv_date;
    private String date;

    private TextView add_breakfast;
    private TextView add_lunch;
    private TextView add_dinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diet);

        //---------- toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기

        //---------- findViewById
        tv_date = (TextView) findViewById(R.id.tv_date);

        add_breakfast = (TextView) findViewById(R.id.breakfast);
        add_lunch = (TextView) findViewById(R.id.lunch);
        add_dinner = (TextView) findViewById(R.id.dinner);

        //--------- 버튼~리스너 연결
        add_breakfast.setOnClickListener(this);
        add_lunch.setOnClickListener(this);
        add_dinner.setOnClickListener(this);

        //---------- 날짜 + 식단
        Intent dateIntent = getIntent();
        date = dateIntent.getStringExtra("date");
        tv_date.setText(date + " 식단");

        //---------- 식단 보기
        seeDiet();
    }

    @Override
    public void onClick(View view){
        Intent intent_addMenu = new Intent(AddDietActivity.this, AddMenuActivity.class);
        String time = "";

        switch (view.getId()){
            case R.id.breakfast :
                time = "아침";
                break;
            case R.id.lunch :
                time = "점심";
                break;
            case R.id.dinner :
                time = "저녁";
        }

        intent_addMenu.putExtra("date", date);
        intent_addMenu.putExtra("time", time);
        startActivity(intent_addMenu);
    }

    public void seeDiet(){

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
