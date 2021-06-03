package com.example.littleforest.InputPage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.littleforest.R;

import java.util.ArrayList;

public class TopicListAdapter  extends BaseAdapter {
    //inflater -> xml 레이아웃 객체화
    //private LayoutInflater inflater;

    Context mContext;
    private ArrayList<TopicBoard> topicData; //Item 목록을 담을 배열


    public TopicListAdapter(ArrayList<TopicBoard> topicData, Context mContext) {

        this.topicData = topicData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return topicData.size();
    }

    @Override
    public TopicBoard getItem(int position) {
        return topicData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") View itemView = inflater.inflate(R.layout.hot_topic_item_view, parent,false);

        TextView title = (TextView) convertView.findViewById(R.id.hotTopicViewTitle);
        TextView context = (TextView) convertView.findViewById(R.id.hotTopicViewContext);


        title.setText(String.valueOf(topicData.get(position).getTitleBoard()));
        context.setText(String.valueOf(topicData.get(position).getContentsBoard()));


        return itemView;

    }
}
