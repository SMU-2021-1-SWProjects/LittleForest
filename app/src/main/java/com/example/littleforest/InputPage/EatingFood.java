package com.example.littleforest.InputPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.littleforest.MainActivity;
import com.example.littleforest.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


//식단 음식 입력
public class EatingFood extends AppCompatActivity {


    public static DatabaseReference myDatabase = FirebaseDatabase.getInstance().getReference("Diet");

    //private static final String TAG = "MainActivity";

    private CalendarView calendarView;




    protected void onCreate(Bundle saveInstanceState)
        {
            super.onCreate(saveInstanceState);
            setContentView(R.layout.activity_eatingfood);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기



            //JEC
            //---------- findViewById
            calendarView = (CalendarView) findViewById(R.id.calendar);

            //---------- 페이지 전환 : 어떤 날을 클릭 -> 그 날의 식단 페이지
            calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                    String date = year + "/" + (month + 1) + "/" + dayOfMonth;

                    Intent intent_addDiet = new Intent(EatingFood.this, AddDietActivity.class);
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