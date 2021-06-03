package com.example.littleforest.foodInfo;

public class SearchFoodInfo {
    private String name;
    private int calorie;
    private int protein;
    private int fat;

    public SearchFoodInfo() {
        //public no-arg constructor needed
    }

    public SearchFoodInfo(String name, int calorie, int protein){
        this.name = name;
        this.calorie = calorie;
        this.protein = protein;
        this.fat = fat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }
}
