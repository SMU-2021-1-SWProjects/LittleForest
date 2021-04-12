package com.example.littleforest.InputPage;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.littleforest.R;


//식단 음식 입력
public class EatingFood extends AppCompatActivity {

        protected void onCreate(Bundle saveInstanceState)
        {
            super.onCreate(saveInstanceState);
            setContentView(R.layout.fragment_eating_food);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기
            actionBar.setHomeAsUpIndicator(R.drawable.back_button);

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