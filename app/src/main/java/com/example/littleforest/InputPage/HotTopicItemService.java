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


    private ListView topicItemView;

    private ArrayList<TopicBoard> topicBoardData;

   // private TopicListAdapter topicListAdapter;

    private Context mContext;
    private LayoutInflater mLayoutInflater;


    protected void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.hot_topic);

        // 현재 view 가져오기
        mContext = getApplicationContext();
        mLayoutInflater = LayoutInflater.from(mContext);

        //주소할당
        topicItemView = (ListView)findViewById(R.id.hotTopicListView);

        //TopicListAdapter topicListAdapter = new TopicListAdapter(this, topicItemView);
        //topicItemView.setAdapter(topicListAdapter);


        //listView 클릭 후 이동
        topicItemView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Intent dataIntent = new Intent(getApplicationContext(), HotTopicView.class);

                dataIntent.putExtra("title", topicBoardData.get(position).getTitleBoard());
                dataIntent.putExtra("context", topicBoardData.get(position).getContentsBoard());
                startActivity(dataIntent);


            }
        });



}




}
