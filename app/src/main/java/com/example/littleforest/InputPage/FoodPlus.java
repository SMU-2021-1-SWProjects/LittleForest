package com.example.littleforest.InputPage;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.littleforest.R;


//식단 추가 -> 핫 토픽으로 변경
public class FoodPlus extends AppCompatActivity {

        protected void onCreate(Bundle saveInstanceState)
        {
            super.onCreate(saveInstanceState);
            setContentView(R.layout.fragment_food_plus);


            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기

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