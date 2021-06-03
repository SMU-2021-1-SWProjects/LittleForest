package com.example.littleforest.InputPage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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
import java.util.ArrayList;
import java.util.List;

public class HotTopicItemService  extends AppCompatActivity {

    //list on clicked view
    ArrayList<TopicBoard>topicItemArrayList;
    ListView topicListView;

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.hot_topic);


        //topicItemList에 datalist에 있는 내용을 넣어야함
        topicListView = (ListView)findViewById(R.id.hotTopicListView);

        //데이터베이스 + 해당 주소 데이터 불러옴
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("board");


        //ListView 를 TopicListAdapter 에 연결
        TopicListAdapter tadapter = new TopicListAdapter(topicItemArrayList, this);
        topicListView.setAdapter(tadapter);




        //listView 클릭 후 이동
        topicListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {

                //리스트 뷰의 포지션
                //int listViewPostion = topicListView.getCheckedItemPosition();
                TopicBoard tb = (TopicBoard) parent.getAdapter().getItem(position);

                String title = tb.getTitleBoard();
                String context = tb.getContentsBoard();

                Intent dataIntent = new Intent(HotTopicItemService.this, HotTopicView.class);
                dataIntent.putExtra("title", title);
                dataIntent.putExtra("context", context);
                startActivity(dataIntent);

            }
        });

    }
    }





