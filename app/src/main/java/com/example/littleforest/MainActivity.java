  package com.example.littleforest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.littleforest.InputPage.EatingFood;
import com.example.littleforest.InputPage.FoodPlus;
import com.example.littleforest.InputPage.HealthInformation;
import com.google.android.material.navigation.NavigationView;
import com.lakue.lakuepopupactivity.PopupActivity;
import com.lakue.lakuepopupactivity.PopupGravity;
import com.lakue.lakuepopupactivity.PopupType;

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
import android.view.View;
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

    public String[] images = new String[] {
            //이미지 데이터 주소
            "https://postfiles.pstatic.net/MjAyMTA1MjZfMjk0/MDAxNjIyMDQxMTY3NTIx.PvOMdWfa5WwS2dGzZju6FDGPbM4EkVxi5ybRsW7kcNsg.xxda8XHkrKE4UgSJC1XOs9lzB6qfU17Pf4gSEORjozkg.PNG.skhy0715/DimageF.png?type=w966",
           "https://postfiles.pstatic.net/MjAyMTA1MjZfMTUg/MDAxNjIyMDQxMTY3NTE3.xUItyWdt9VMsdPlJuvvPkGKmxYV0HJturkLzVdSDtLMg.H5nsP_4UoS6EYRZdRVBJDtAU3n_gYZFGRm0fV6_iwZsg.PNG.skhy0715/DimageFT.png?type=w966",
           "https://postfiles.pstatic.net/MjAyMTA1MjZfMjgz/MDAxNjIyMDQxMTY3NTgx.LL33zXAU_Xfh9hGZmNe9JrFPjbL00AvJ_NgTjsNIaVog.IQgxhtO162uOQK_kkbh7RFsCvnnxKH70Lal85eiruJ4g.PNG.skhy0715/DimageT.png?type=w966",
           "https://postfiles.pstatic.net/MjAyMTA1MjZfMTA4/MDAxNjIyMDQxMTY3NTQ0.zWX0aa1gIiWECFB9d3kYyv9BHMN_FFig_XxxhKQbe1sg.SQGaAcTPjsabKsr_xFcUzun5ThujNcYwG5S38yk3hRog.PNG.skhy0715/DimageS.png?type=w966",
           "https://postfiles.pstatic.net/MjAyMTA1MjZfMTk3/MDAxNjIyMDQxMTY3NDc2.OHcDQQw_G286LJp8YI8J6Sgbq4A_a-2Qg5G_qzlWCA4g.FBwy8JMK3FPFWMMG0fQk1gI76pjpyyi72Ha4xxqL93Qg.PNG.skhy0715/DimageFF.png?type=w966"
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


        //navigation drawer
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

        //밑에있는 점 수를 이미지 수만큼 지정
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
