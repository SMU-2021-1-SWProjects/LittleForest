package com.example.littleforest.InputPage;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

@IgnoreExtraProperties
public class Diet {
    public String date;
    public String time;
    public ArrayList<String> menu;

    public Diet(){
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Diet(String date, String time, ArrayList<String> menu){
        this.date = date;
        this.time = time;
        this.menu = menu;
    }

    //---------- set 함수수
   public void setDate(String date) {
       this.date = date;
   }

    public void setTime(String time){
        this.time = time;
    }

    public void setMenu(ArrayList<String> menu){
        this.menu = menu;
    }

    //--------- get 함수
    public String getDate(){
        return date;
    }

    public String getTime(){
        return time;
    }

    public ArrayList<String> getMenu(){
        return menu;
    }
}
