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
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    //네비
    private DrawerLayout mDrawerLayout;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false); // 기존 title 지우기
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기
        actionBar.setHomeAsUpIndicator(R.drawable.menu); //뒤로가기 버튼 이미지 지정


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                String title = menuItem.getTitle().toString();


                if(id == R.id.account){
                    //건강 정보 + 건강 정보 입력 페이지로 이동
                    Intent health_intent = new Intent(MainActivity.this, HealthInformation.class);
                    startActivity(health_intent);

                    //팝업 알림
                   // Toast.makeText(context, title + ": 계정 정보를 확인합니다.", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.setting){
                    //자신의 식단 정보 + 삭단 음식 입력 페이지로 이동
                    Intent health_intent = new Intent(MainActivity.this, EatingFood.class);
                    startActivity(health_intent);
                    //Toast.makeText(context, title + ": 설정 정보를 확인합니다.", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.logout){
                    //자신의 식단 추가 상황 + 식단 추가 입력 페이지로 이동
                    Intent health_intent = new Intent(MainActivity.this, FoodPlus.class);
                    startActivity(health_intent);
                    //Toast.makeText(context, title + ": 로그아웃 시도중", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ // 왼쪽 상단 버튼 눌렀을 때
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}