package com.example.littleforest.InputPage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.littleforest.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.net.CookieHandler;
import java.util.ArrayList;
import java.util.List;


//식단 추가 -> 핫 토픽으로 변경
public class HotTopicService extends AppCompatActivity {


    private ListView topicListView, topicItemView;
    private Button writetopic;

    //접근 금지 (list view 불러오기)
    ArrayAdapter adapter;
    List dataList = new ArrayList<>();
    static boolean called = false;


    protected void onCreate(Bundle saveInstanceState)
        {
            super.onCreate(saveInstanceState);
            setContentView(R.layout.hot_topic);

            //파이어 데이터 베이스 불러오기 확인
            if (!called)
            {
            //오프라인 상태여도 로컬에 저장된 캐시로 유지 가능
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
                called = true;
            }


            //툴바
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기

            //주소할당
            writetopic = findViewById(R.id.hotTopicWriteBtn);
            topicListView = (ListView)findViewById(R.id.hotTopicListView);


            adapter = new ArrayAdapter<String>(this, R.layout.hot_topic_list_item, dataList);
            topicListView.setAdapter(adapter);


            //데이터베이스 + 해당 주소 데이터 불러옴
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = database.getReference("board");

            //title 데이터 불러옴
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot fileSnapshot : dataSnapshot.getChildren()) {

                        //하위 value 가져옴
                        String str = fileSnapshot.child("title").getValue(String.class);
                        Log.i("HotTopicService: 불러온 게시글 제목  ", str);
                        dataList.add(str);


                    }
                    adapter.notifyDataSetChanged();
                }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.w("HotTopicService:","데이터베이스 오류 발생");
                }
            });


            writetopic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent boardWrite_intent = new Intent(HotTopicService.this, HotTopicWrite.class);
                    startActivity(boardWrite_intent);

                }
            });



            //listView 클릭 후 이동
            topicListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView parent, View view, int position, long id) {

                    //리스트 뷰의 포지션
                    //int listViewPostion = topicListView.getCheckedItemPosition();
                    TopicBoard tb = (TopicBoard) parent.getAdapter().getItem(position);

                    String title = tb.getTitleBoard();
                    String context = tb.getContentsBoard();

                    Intent dataIntent = new Intent(HotTopicService.this, HotTopicView.class);
                    dataIntent.putExtra("title", title);
                    dataIntent.putExtra("context", context);
                    startActivity(dataIntent);

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