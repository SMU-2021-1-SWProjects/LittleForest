package com.example.littleforest.InputPage;

import java.util.ArrayList;

class Diet {
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

    //---------- set 함수
    public void setDate(String date){
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

    //---------- toString 함수
    public String toString(){
        String string_menu = "";

        for(int i = 0 ; i < this.menu.size() ; i++){
            if(i == 0){
                string_menu += this.menu.get(i);
            }else{
                string_menu += "\n" + this.menu.get(i);
            }
        }

        return string_menu;
    }
}

class EatingFoodSearch {
    private String eatingfood_search;

    public void setEatingfood_search(String eatingfood_search){
        this.eatingfood_search = eatingfood_search;
    }

    public String getEatingfood_search(){
        return eatingfood_search;
    }

}
