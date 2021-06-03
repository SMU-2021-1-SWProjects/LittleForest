package com.example.littleforest.InputPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.littleforest.R;
import com.example.littleforest.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class HotTopicWrite extends AppCompatActivity {

    EditText editTitle, editContents;


    private Button boardWriteBtn, boardCancleBtn;

    private DatabaseReference database;

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.hot_topic_write);


        //툴바
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기

        //주소 할당
        boardWriteBtn = findViewById(R.id.hotTopicWriteBtnIn);
        boardCancleBtn = findViewById(R.id.hotTopicCancleBtn);
        editTitle = findViewById(R.id.edithotTopicTitle);
        editContents = findViewById(R.id.edithotTopicContent);

        //firebase 정의
        database = FirebaseDatabase.getInstance().getReference();

        boardWriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //데이터 저장
                String getTitleBoard = editTitle.getText().toString();
                String getContentsBoard = editContents.getText().toString();

                //haspmap 만들기 (데이터 베이스에 삽입)
                HashMap boardresult = new HashMap<>();
                boardresult.put("title", getTitleBoard);
                boardresult.put("board", getContentsBoard);

                database.child("board").push().setValue(boardresult);
                Toast.makeText(getApplicationContext(), "글 등록이 완료 되었습니다.", Toast.LENGTH_SHORT).show();    // AVD 확인용

                //topic 리스트로 이동
                Intent TopicList_intent = new Intent(HotTopicWrite.this, HotTopicService.class);
                startActivity(TopicList_intent);

            }


        });


        boardCancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //topic 리스트로 이동
                Intent TopicList_intent = new Intent(HotTopicWrite.this, HotTopicService.class);
                startActivity(TopicList_intent);

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
