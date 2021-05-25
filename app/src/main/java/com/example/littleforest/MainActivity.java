  package com.example.littleforest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.littleforest.InputPage.EatingFood;
import com.example.littleforest.InputPage.FoodPlus;
import com.example.littleforest.InputPage.HealthInformation;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import static android.R.id.home;

  public class MainActivity extends AppCompatActivity {

    //네비
    private DrawerLayout mDrawerLayout;
    private Context context = this;

    //이미지 드로어
    private ViewPager2 sliderViewPager;
    private LinearLayout layoutIndicator;

    private String[] images = new String[] {
            //이미지 데이터 주소
            "data:image/jpeg;base64,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",
            "https://postfiles.pstatic.net/MjAyMTA1MTdfMTEx/MDAxNjIxMjYyMjkyOTIy.jyfT9kZbSZuVjdM5fvqQq7nbqAzwLGgmI5BIdeUD-Uwg.jg83Zsz9EVc-qLsrWfjJygdYWYEWa2tHCDzyd-Rk9JUg.JPEG.skhy0715/KakaoTalk_20210501_164142410.jpg?type=w966",
            "https://postfiles.pstatic.net/MjAyMTA1MTdfMjc0/MDAxNjIxMjYyNDkyNTE5.XD_oZir_0p8DxEJ7bN5oYcSAjk164cTef9eoVNYrV1Ug.TFB98Zop6EbcDYEXOi43N8Y8vG5hTm08IfWmFW6ubsMg.JPEG.skhy0715/%EC%9D%BC%EB%9F%AC%EC%8A%A4%ED%8A%B8.jpg?type=w966",
            "https://cdn.pixabay.com/photo/2020/09/02/18/03/girl-5539094_1280.jpg",
            "https://cdn.pixabay.com/photo/2014/03/03/16/15/mosque-279015_1280.jpg"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false); // 기존 title 지우기
        actionBar.setDisplayHomeAsUpEnabled(true); // 메뉴(사이드바)버튼 만들기
        actionBar.setHomeAsUpIndicator(R.drawable.menu); //메뉴(사이드바) 버튼 이미지 지정



    //이미지 드로어
        sliderViewPager = findViewById(R.id.sliderViewPager);
        layoutIndicator = findViewById(R.id.layoutIndicators);

        sliderViewPager.setOffscreenPageLimit(1);
        sliderViewPager.setAdapter(new ImageSliderAdapter(this, images));

        sliderViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentIndicator(position);
            }
        });

        setupIndicators(images.length);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                //String title = menuItem.getTitle().toString();

                switch(id){
                    case(R.id.healthIn):
                        //건강 정보 + 건강 정보 입력 페이지로 이동
                        Intent health_intent = new Intent(MainActivity.this, HealthInformation.class);
                        startActivity(health_intent);
                        break;

                    case(R.id.foodSetting):
                        //자신의 식단 정보 + 삭단 음식 입력 페이지로 이동
                        Intent eatingFood_intent = new Intent(MainActivity.this, EatingFood.class);
                        startActivity(eatingFood_intent);
                        //Toast.makeText(context, title + ": 설정 정보를 확인합니다.", Toast.LENGTH_SHORT).show();
                        break;

                    case(R.id.hotTopic):
                        //자신의 식단 추가 상황 + 식단 추가 입력 페이지로 이동 --> 핫토픽으로 수정
                        Intent foodPlus_intent = new Intent(MainActivity.this, FoodPlus.class);
                        startActivity(foodPlus_intent);
                        break;



                }


                return true;
            }
        });
    }

      //Toolbar에 menu_main을 inflate
      public boolean onCreateOptionsMenu(Menu menu) {
          //return super.onCreateOptionsMenu(menu);
          MenuInflater menuInflater = getMenuInflater();
          menuInflater.inflate(R.menu.menu_main, menu);
          return true;
      }

    //toolbar 에 버튼 클릭되고 나오는 결과 설정 
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case home:
                { // 왼쪽 상단 버튼 눌렀을 때
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
                }
            
            case R.id.action_settings: 
                {
                Toast.makeText(getApplicationContext(), "환경설정 버튼 클릭됨", Toast.LENGTH_LONG).show();
                return true;
                 }
            
            case R.id.action_search: {
                Toast.makeText(getApplicationContext(), "검색 버튼 클릭됨", Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);
            }

        }
        return super.onOptionsItemSelected(item);
        
    }



    //이미지 드로어
    private void setupIndicators(int count) {
        ImageView[] indicators = new ImageView[count];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.setMargins(16, 8, 16, 8);

        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(this);
            indicators[i].setImageDrawable(ContextCompat.getDrawable(this,
                    R.drawable.bg_indicator_inactive));
            indicators[i].setLayoutParams(params);
            layoutIndicator.addView(indicators[i]);
        }
        setCurrentIndicator(0);
    }

    private void setCurrentIndicator(int position) {
        int childCount = layoutIndicator.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutIndicator.getChildAt(i);
            if (i == position) {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        this,
                        R.drawable.bg_indicator_active
                ));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        this,
                        R.drawable.bg_indicator_inactive
                ));
            }
        }
    }//이미지 드로어 여기까지

}
