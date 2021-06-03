package com.example.littleforest;

import android.widget.EditText;

import java.util.Set;

public class User {

    public String name;
    public String stature;
    public String weight;
    public String blood;
    public String disease;


    public User(EditText userEmail, String userName, String userPsw) {

    }

    public User(String name, String stature, String weight, String blood, String disease) {
        this.name = name;
        this.stature = stature;
        this.weight = weight;
        this.blood = blood;
        this.disease = disease;
    }

    public String getUserName() {
        return name;
    }
    public void setUserName(String userName) {
        this.name = name;
    }

    public String getStature() {
        return stature;
    }
    public void setSature(String stature) {
        this.stature = stature;
    }

    public String getWeight() {
        return weight;
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBlood() {
        return blood;
    }
    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getDisease() {
        return disease;
    }
    public void setDisease(String disease) {
        this.disease = disease;
    }


    @Override
    public String toString() {
        return "User{" +
                "Name='" + name + '\'' +
                ", stature='" + stature + '\'' +
                ", weight='" + weight + '\'' +
                ", blood='" + blood + '\'' +
                ", disease='" + disease + '\'' +
                '}';
    }

}


