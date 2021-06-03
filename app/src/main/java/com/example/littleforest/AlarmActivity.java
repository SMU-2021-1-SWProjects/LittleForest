package com.example.littleforest;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {


    AlarmManager alarmManager;
    int hour, minute;
    TimePicker timePicker;

    TextView currentTime;

    Button register, unregister;

    private TextView toolbarName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm);

        //툴바
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기

        toolbarName = findViewById(R.id.txv_toolbar);
        toolbarName.setText("알람");

        //알람 매니저 초기화
        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        //타임피커
        timePicker=findViewById(R.id.alarm_timepicker);

        //버튼 주소 등록
        register = findViewById(R.id.register);
        unregister = findViewById(R.id.unregister);

        //time edit text란 주소 등록
        currentTime = findViewById(R.id.TimetextView);



        //현재 시간 가져오기
        long nowTime = System.currentTimeMillis();
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh : mm : ss");


        //한국 시간대로 변경
        //TimeZone korTime = TimeZone.getTimeZone("Asia/Seoul");
        //timeFormat.setTimeZone(korTime);

        //string 형태로 convert
        String getTime = timeFormat.format(nowTime);
        currentTime.setText(getTime);
        Log.d("현재시간" , getTime);



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //receiver 로 보내는 intent 생성
                Intent intent = new Intent(AlarmActivity.this, AlarmReceiver.class);
                PendingIntent pIntent = PendingIntent.getBroadcast(AlarmActivity.this, 0, intent, 0 );

                //시간 가져옴, 이미 넘어갔으면 내일로
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    hour = timePicker.getHour();
                    minute = timePicker.getMinute();
                }

                //달력 생성 + 시간 설정
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);

                // 알람 설정

                //RTC_WAKEUP -> 앱 꺼져도 설정 유지
                //getTimeInMills -> calender 시간을 millisecond 로 return
                // interval_day -> 하루 간격
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),  AlarmManager.INTERVAL_DAY, pIntent);


                Toast.makeText(AlarmActivity.this, "알람이" + hour + "시" +minute +"에 예정되었습니다.", Toast.LENGTH_SHORT).show();



                }
        });

        unregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AlarmActivity.this, AlarmReceiver.class);
                PendingIntent pIntent = PendingIntent.getBroadcast(AlarmActivity.this, 0, intent, 0);

                //알람 취소
                alarmManager.cancel(pIntent);

            }
        });




    }





    //뒤로가기
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

