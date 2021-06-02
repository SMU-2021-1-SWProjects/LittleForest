package com.example.littleforest.InputPage;

import android.widget.EditText;

import java.util.ArrayList;

public class TopicBoard {


    public String title;
    public String contents;


    public TopicBoard(){

    }

    public TopicBoard(String getTitleBoard, String getContentsBoard){
        this.title = title;
        this.contents = contents;
    }


    public String getTitleBoard() {
        return title;
    }
    public void setTitleBoard(String title) {
        this.title = title;
    }

    public String getContentsBoard() {
        return contents;
    }
    public void setContentsBoard(String contents) {
        this.contents = contents;
    }


    @Override
    public String toString() {
        return "HotTopicBoard{" +
                "title='" + title + '\'' +
                ", content='" + contents + '\'' +
                '}';
    }

}
