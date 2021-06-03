package com.example.littleforest.SearchFood;

import com.google.firebase.firestore.Exclude;

public class FoodInfo {
    private String documentId;
    // private int foodId;
    private String name;
    private int calorie;
    private String calUnit; // 열량 단위
    private int carbohydrate; // 탄수화물
    private int sugar;
    private int protein;
    private int cholesterol;
    private int dietaryFiber; // 식이섬유
    private int na; // 나트륨
    private int k; // 칼륨

    private String goodfood;
    private String badfood;

    public FoodInfo() {
        //public no-arg constructor needed
    }

    public FoodInfo(String name, int calorie, String calUnit, int carbohydrate,
                    int sugar, int protein, int cholesterol, int dietaryFiber, int na, int k,
                    String goodfood, String badfood){

        this.name = name;
        this.calorie = calorie;
        this.calUnit = calUnit;
        this.carbohydrate = carbohydrate;
        this.sugar = sugar;
        this.protein = protein;
        this.cholesterol = cholesterol;
        this.dietaryFiber = dietaryFiber;
        this.na = na;
        this.k = k;

        this.goodfood = goodfood;
        this.badfood = badfood;
    }

    @Exclude
    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
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

    public String getCalUnit() {
        return calUnit;
    }

    public void setCalUnit(String calUnit) {
        this.calUnit = calUnit;
    }

    public int getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(int carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public int getSugar() {
        return sugar;
    }

    public void setSugar(int sugar) {
        this.sugar = sugar;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(int cholesterol) {
        this.cholesterol = cholesterol;
    }

    public int getDietaryFiber() {
        return dietaryFiber;
    }

    public void setDietaryFiber(int dietaryFiber) {
        this.dietaryFiber = dietaryFiber;
    }

    public int getNa() {
        return na;
    }

    public void setNa(int na) {
        this.na = na;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public String getGoodfood() {
        return goodfood;
    }

    public void setGoodfood(String goodfood) {
        this.goodfood = goodfood;
    }

    public String getBadfood() {
        return badfood;
    }

    public void setBadfood(String badfood) {
        this.badfood = badfood;
    }
}
