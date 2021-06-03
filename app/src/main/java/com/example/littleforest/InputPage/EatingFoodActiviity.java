package com.example.littleforest.InputPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.littleforest.R;


//식단 음식 입력
public class EatingFoodActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    // 페이지
    private TextView txv_toolbar;
    private CalendarView calendarView;

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.eating_food);

        //---------- toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기

        //---------- 페이지 이름
        txv_toolbar = (TextView) findViewById(R.id.txv_toolbar);
        txv_toolbar.setText("식단 입력");

        //---------- findViewById
        calendarView = (CalendarView) findViewById(R.id.calendar);

        //---------- 페이지 전환 : 어떤 날을 클릭 -> 그 날의 식단 페이지
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = year + "/" + (month + 1) + "/" + dayOfMonth;

                Intent intent_addDiet = new Intent(EatingFoodActivity.this, AddDietActivity.class);
                intent_addDiet.putExtra("date", date);
                startActivity(intent_addDiet);
            }
        });
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