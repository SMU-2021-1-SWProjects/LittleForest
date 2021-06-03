package com.example.littleforest.SearchFood;

public class SearchFoodInfo {
    private String name;
    private int calorie;
    private int protein;

    public SearchFoodInfo() {
        //public no-arg constructor needed
    }

    public SearchFoodInfo(String name, int calorie, int protein){
        this.name = name;
        this.calorie = calorie;
        this.protein = protein;
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
}
