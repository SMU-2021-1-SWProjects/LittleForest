package com.example.littleforest.InputPage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.littleforest.R;

import java.util.ArrayList;

public class TopicListAdapter  extends BaseAdapter {

    HotTopicItemService mContext = null;
    //inflater -> xml 레이아웃 객체화
    //private LayoutInflater inflater;
    private ArrayList<TopicBoard> topicData; //Item 목록을 담을 배열
    private int layout;
    LayoutInflater mLayoutInflater = null;
    public TopicListAdapter(HotTopicItemService context, ArrayList<TopicBoard> data) {
        mContext = context;
        this.topicData = data;
        mLayoutInflater = LayoutInflater.from(mContext);
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /*if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);
        }
        TopicBoard topicBoard = topicData.get(position);
*/

        TextView title = (TextView) convertView.findViewById(R.id.hotTopicViewTitle);
        title.setText(String.valueOf(topicData.get(position).getTitleBoard()));

        TextView context = (TextView) convertView.findViewById(R.id.hotTopicViewContext);
        context.setText(String.valueOf(topicData.get(position).getContentsBoard()));


        return convertView;
    }
}
